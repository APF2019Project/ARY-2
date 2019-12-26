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

    public Day(ArrayList<Card> selectedCards){
        turn = 0;
        map = generateMap();
        this.selectedCards = selectedCards;
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
//        if (player.getSun() >= plant.getSun() && plant.getTimeToReset() == 0)
            selected = plant;
            //badan bayad kamel beshe
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
    public void healthDecrease(){
        //dar har turn check mikonad agar tir be zombie khord az janash gozashtan
        //turn badan ok mishavaddd
    }
    public void shoot(){
        for(int i=0 ; i<Map.colNumber; i++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i][j].plant.size() > 0) {
                    Plant plant1 = map.board[i][j].plant.get(0);
                    if(plant1.getTimeToReset() <= plant1.getLastshoot() && shootCondition(i, j)){
                        for (Weapon weapon : plant1.weapons){
                            bullets.add(weapon.bulletMaker());
                            plant1.setLastshoot(0);
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
    }

    public boolean shootCondition(int column, int row){
        for(int i=row ; i<Map.rowNumber ; i++) {
            if (map.board[column][i].zombies.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public void zombieGenerate(){
        try {
            Zombie zombie = (Zombie) enemies.get(random.nextInt(enemies.size())).clone();
            zombie.setRow(Map.rowNumber - 1);
            zombie.setColumn(random.nextInt(Map.colNumber));
            zombies.add(zombie);
        } catch (CloneNotSupportedException e) {

        }
    }
    public void waveGenerate(){
        int numOfZombies = random.nextInt(7);
        numOfZombies += 4;
        for(int i=0 ; i<numOfZombies ; i++){
            zombieGenerate();
        }
    }

    @Override
    public void endTurn(){
        turn += 1;
        for (Card card1 : selectedCards){
            Plant plant1 = (Plant) card1;
            plant1.setLastshoot(plant1.getLastshoot() + 1);
            plant1.setAge(plant1.getAge() + 1);
        }
        for(Bullet bullet1 : bullets){
            bullet1.setRow(bullet1.getRow() + 1);
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
