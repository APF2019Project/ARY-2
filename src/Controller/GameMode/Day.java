package Controller.GameMode;

import Controller.Game;
import Model.Card.*;
import Model.Collection;
import Model.Account.Player;
import Model.Map.Map;
import Exception.NotPlantException;
import Exception.noCardSelected;

import java.util.ArrayList;
import java.util.Random;

public class Day implements GameMode{
    private Plant selected;
    private ArrayList<Card> selectedCards;
    private Player player;
    private Map map;
    ArrayList<Zombie> enemies = new ArrayList<>(); // badan bayad dar canstructor load shavad
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plantsInMap = new ArrayList<>();
    Random random = new Random();
    private int turn;
    private int turnsAfterLastWave;

    public Day(){
        turn = 0;
        map = generateMap();
        this.selectedCards = Game.accounts[0].getCollection().selectedCards;
        turnsAfterLastWave = 4;
    }
    public void showHand() throws NotPlantException{
            for (int i = 0; i < selectedCards.size(); i++) {
                Plant plant = (Plant)selectedCards.get(i);
                System.out.println(selectedCards.get(i).getName() + " "
                        +plant.getSun() + " " + plant.getTimeToReset());
            }
    }
    public void select(String name) throws NotPlantException{
        int index = Collection.findCard(name, selectedCards);
        Plant plant = (Plant) selectedCards.get(index);
        if (player.getSun() >= plant.getSun() && plant.getTimeToReset() == 0)
            selected = plant;
    }
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException{
        if(selected != null) {
            if (map.board[column][row].plant.size() == 0) {
                try {
                    Plant tmp = (Plant)selected.clone();
                    tmp.setRow(row);
                    tmp.setColumn(column);
                    map.board[column][row].plant.add(tmp);
                    plantsInMap.add(tmp);
                    Game.accounts[0].getPlayer().setSun(Game.accounts[0].getPlayer().getSun() - selected.getSun());
                }catch (CloneNotSupportedException e){}
            }
        } else {
            System.out.println("no card selected");
            throw new noCardSelected();
        }
    }
    private void healthDecrease(){
        for(Bullet bullet1 : bullets){
            int r = bullet1.getRow();
            int c = bullet1.getColumn();
            if(map.board[c][r].zombies.size()>0){
                Zombie randomZ = map.board[c][r].zombies.get(random.nextInt(map.board[c][r].zombies.size()));
                if(randomZ.getHealth() > bullet1.getPlant().getDamage()) {
                    randomZ.setHealth(randomZ.getHealth() - bullet1.getPlant().getDamage());
                }else {map.board[c][r].zombies.remove(randomZ);}
                bullets.remove(bullet1);
            }
        }
    }
    private void shoot(){
        for(int i=0 ; i<Map.colNumber; i++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i][j].plant.size() > 0) {
                    Plant plant1 = map.board[i][j].plant.get(0);
                    for (Weapon weapon : plant1.weapons){
                        if(weapon.turns.get(weapon.getTurn())){
                            if(shootCondition(i, j)){
                                bullets.add(weapon.bulletMaker());
                            }
                        }
                    }
                }
            }
        }
    }
    public void showLawn(){
        for (Plant plant1 : plantsInMap){
            System.out.println(plant1.getName()+"\thealth: "+plant1.getHealth()+
                    "\tcoordinate: ("+plant1.getColumn()+","+plant1.getRow()+")");
        }
        for (Bullet bullet: bullets){
            System.out.println(bullet.getRow()+"\t"+bullet.getColumn()+" row and col of bullet");
        }
        for(Zombie zombie : zombies){
            System.out.println(zombie.getRow()+"\t"+zombie.getColumn()+"\t"+zombie.getHealth()+" zombieee");
        }
    }
    private boolean shootCondition(int column, int row){
        for(int i=row ; i<Map.rowNumber ; i++) {
            if (map.board[column][i].zombies.size() > 0) {
                return true;
            }
        }
        return false;
    }
    private void zombieGenerate(){
        try {
            Zombie zombie = (Zombie) enemies.get(random.nextInt(enemies.size())).clone();
            zombie.setRow(Map.rowNumber - 1);
            zombie.setColumn(random.nextInt(Map.colNumber));
            zombies.add(zombie);
        } catch (CloneNotSupportedException e) {

        }
    }
    private void waveGenerate(){
        int numOfZombies = random.nextInt(7);
        numOfZombies += 4;
        for(int i=0 ; i<numOfZombies ; i++){
            zombieGenerate();
        }
    }
    private void bulletMove(Bullet bullet1){
        bullet1.setRow(bullet1.getRow() + 1);
    }

    @Override
    public void endTurn(){
        turn += 1;
        turnsAfterLastWave += 1;
        for(Plant p : plantsInMap){
            for(Weapon w : p.weapons){
                if(w.getTurn() >= w.getCycle()){
                    w.setTurn(0);
                    w.turnsGenerate();
                }else {w.setTurn(w.getTurn() + 1);}
            }
        }
        for(Bullet bullet1 : bullets){
            bulletMove(bullet1);
        }
        for(Zombie zombie1 : zombies){
            map.board[zombie1.getColumn()][zombie1.getRow()].zombies.remove(zombie1);
            zombie1.setRow(zombie1.getRow() - 1);
            map.board[zombie1.getColumn()][zombie1.getRow()].zombies.add(zombie1);
        }
        healthDecrease();
        shoot();
        if(turnsAfterLastWave == 7){
            waveGenerate();
        }
    }

    @Override
    public Map generateMap() {
        return Map.generateDay();
    }

    @Override
    public void handleWin() {

    }
}
