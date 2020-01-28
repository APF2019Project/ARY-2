package Controller.GameMode;

import Controller.Game;
import Model.Account.Player;
import Model.Card.*;
import Model.Collection;
import Model.Map.Map;
import Model.Map.Square;
import Model.Primary;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Exception.invalidCardExeption;

import java.util.ArrayList;
import java.util.Random;

public class Water implements GameMode {
    private Player player;
    private Map map;
    private int turn;
    private Plant selected;
    private ArrayList<Card> selectedCards;
    ArrayList<Zombie> enemies = new ArrayList<>(); // badan bayad dar canstructor load shavad
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plantsInMap = new ArrayList<>();
    private int turnsAfterLastWave;
    private int zombieAliveNumber;
    Random random = new Random();
    public Water(){
        turn=0;
        map = generateMap();
        this.selectedCards = Game.accounts[0].getCollection().selectedCards;
        turnsAfterLastWave = 4;
        zombieAliveNumber = 0;
        player = Game.accounts[0].getPlayer();
        enemies.addAll(Primary.zombies); // in ghalat ast va badan bayad dorost shavad
    }
    public Map generateMap() {
        return Map.generateWater();
    }

    @Override
    public void showHand() throws NotPlantException {
        for (int i = 0; i < selectedCards.size(); i++) {
            Plant plant = (Plant)selectedCards.get(i);
            System.out.println(selectedCards.get(i).getName() + " "
                    +plant.getSun() + " " + plant.getTimeToReset());
        }
    }

    @Override
    public void select(String a) throws NotPlantException {
        int index = Collection.findCard(a, selectedCards);
        if(index != -1) {
            Plant plant = (Plant) selectedCards.get(index);
            if (player.getSun() >= plant.getSun() && plant.getPermissionTime() == 0) {
                selected = plant;
                System.out.println("select successfully");
            } else {
                System.out.println("you cant select this card");
            }
        }else {
            System.out.println("invalid card name");
            throw new NotPlantException();
        }
    }

    @Override
    public void select(int a) {

    }
    public boolean checkIfCanBePlanted(int row,int column,Plant plant){
        if(plant.isWater()==true && map.board[column][row].isWater()==false){
            return false;
        }
        if(plant.isWater()==false && map.board[column][row].isWater()==true &&
                !map.board[column][row].plant.get(0).getName().equals("lily-pad")){
            return false;
        }
        return true;
    }
    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {
        if(selected != null) {
            if (map.board[column][row].plant.size() == 0 || (map.board[column][row].plant.get(0).getName().equals("Lily-Pad") && map.board[column][row].plant.size()==1)) {
                if(checkIfCanBePlanted(row,column,selected) || (map.board[column][row].plant.get(0).getName().equals("Lily-Pad") && map.board[column][row].plant.size()==1)) {
                    try {
                        Plant tmp = (Plant) selected.clone();
                        ArrayList<Weapon> weapons = new ArrayList<>();
                        tmp.setRow(row);
                        tmp.setColumn(column);
                        for (int i = 0; i < tmp.weapons.size(); i++) {
                            Weapon tmpWeapon = (Weapon) tmp.weapons.get(i);
                            tmpWeapon = (Weapon) tmpWeapon.clone();
                            tmpWeapon.setRow(row);
                            tmpWeapon.setColumn(column);
                            weapons.add(tmpWeapon);
                        }
                        tmp.weapons = weapons;

                        for (Weapon w : tmp.weapons) {
                            w.turnsGenerate();
                        }
                        map.board[column][row].plant.add(tmp);
                        plantsInMap.add(tmp);
                        Game.accounts[0].getPlayer().setSun(Game.accounts[0].getPlayer().getSun() - selected.getSun());
                        selected.setPermissionTime(selected.getTimeToReset());
                    } catch (CloneNotSupportedException e) {
                    }
                }
                else{
                    System.out.println("this place is unavailable");
                }
            }
        } else {
            System.out.println("no card selected");
            throw new noCardSelected();
        }
    }

    @Override
    public void showLawn() {
        for (Plant plant1 : plantsInMap){
            System.out.println(plant1.getName()+"\thealth: "+plant1.getHealth()+
                    "\tcoordinate: ("+plant1.getColumn()+","+plant1.getRow()+")");
        }
//        for (Bullet bullet: bullets){
//            System.out.println(bullet.getColumn()+"\t"+bullet.getRow()+" col and row of bullet  "+bullet);
//        }
        for(Zombie zombie : zombies){
            System.out.println(zombie.getColumn()+"\t"+zombie.getRow()+"\t"+zombie.getHealth()+" zombie  "+zombie.getName()+" jun "+zombie.getHealth());
        }
    }

    @Override
    public void endTurn() {
        turn += 1;
        turnsAfterLastWave += 1;
        for(Card card :selectedCards){
            Plant p = (Plant) card;
            if(p.getPermissionTime() > 0)
                p.setPermissionTime(p.getPermissionTime() - 1);
        }


        for(int i1=0 ; i1<Map.colNumber; i1++) {
            for (int j = 0; j < Map.rowNumber; j++) {
                if (map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(map.board[i1][j].plant.size()-1);
                    for (Weapon w : plant1.weapons) {
                        if (w.getTurn() >= w.getCycle() - 1) {
                            w.setTurn(0);
                            w.turnsGenerate();
                        } else {
                            w.setTurn(w.getTurn() + 1);
                        }
                    }
                }
            }
        }

        for(Bullet bullet1 : bullets){
            bulletMove(bullet1);
        }

        zombieMove();
        eatPlant();
        healthDecrease();
        shoot();
        if(turnsAfterLastWave == 7){
            waveGenerate();
        }
        if(turn%2==0){
            int tmpSun=random.nextInt(3);
            tmpSun=tmpSun+2;
            Game.accounts[0].getPlayer().setSun(Game.accounts[0].getPlayer().getSun()+tmpSun);
        }
    }
    private void bulletMove(Bullet bullet1){
        bullet1.setRow(bullet1.getRow() + 1);
    }
    private void zombieMove(){
        for(Zombie zombie1 : zombies){
            int speed = zombie1.getSpeed();
            if(zombie1.speedReduce[1] > 0){
                speed = zombie1.speedReduce[0];
                zombie1.speedReduce[1] -= 1;
            }
            boolean isOkToGo = true;
            for(int i=1 ; i<= speed ; i++){
                if(map.board[zombie1.getColumn()][zombie1.getRow()-i].plant.size() != 0) {
                    isOkToGo = false;
                }
            }
            if(isOkToGo){
                if(zombie1.getRow() >= speed) {
                    map.board[zombie1.getColumn()][zombie1.getRow()].zombies.remove(zombie1);
                    zombie1.setRow(zombie1.getRow() - speed);
                    map.board[zombie1.getColumn()][zombie1.getRow()].zombies.add(zombie1);
                }else {
                    //bayad payam bede ke shoma bakhtid
                    System.out.println("YOU LOST");

                }
            }

        }
    }
    @Override
    public void showSun() {
        System.out.println(Game.accounts[0].getPlayer().getSun());
    }

    @Override
    public void record() {

    }

    @Override
    public void list() {

    }

    @Override
    public void remove(int column, int row) {
        Square square=map.board[row][column];
        if(square.plant.size()!=0){
            if(square.plant.size()==2){
                square.plant.remove(1);
            }
            else{
                square.plant.remove(0);
            }
        }
        else {
            System.out.println("no plant is here");
        }
    }

    @Override
    public void handleWin() {

    }

    @Override
    public void waveGenerate() {

    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public void setStart(boolean b) {

    }

    @Override
    public void showLanes() {

    }

    @Override
    public void put(String name, int number) throws CloneNotSupportedException, invalidCardExeption {

    }
    private void eatPlant(){ //zombie haa plant ro mikhoran
        for(Zombie z : zombies){
            int row = z.getRow();
            int col = z.getColumn();
            if(z.getDamage() >0 && row > 0 && map.board[col][row-1].plant.size() > 0){
                Plant p =map.board[col][row].plant.get(map.board[col][row].plant.size()-1);
                if(p.getHealth() > z.getDamage()) {
                    p.setHealth(p.getHealth() - z.getDamage());
                }else {
                    map.board[col][row-1].plant.remove(p);
                }
            }
        }
    }
    private void shoot(){
        for(int i1=0 ; i1<Map.colNumber; i1++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(map.board[i1][j].plant.size()-1);
                    for (Weapon weapon : plant1.weapons){
                        if(weapon.getDamage() > -1) {
                            if (shootCondition(i1, j)) {
                                if (weapon.turns.get(weapon.getTurn())) {
                                    bullets.add(weapon.bulletMaker());
                                }
                            }
                        }

                    }
                }
            }
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
    private void healthDecrease(){
        for(int s=bullets.size()-1 ; s >= 0 ; s--) {
            Bullet bullet1 = bullets.get(s);
            int row = bullet1.getRow();
            int c = bullet1.getColumn();
            for (int r = bullet1.getStartRow(); r < row; r++) {
                if (map.board[c][r].zombies.size() > 0) {
                    Zombie randomZ = map.board[c][r].zombies.get(random.nextInt(map.board[c][r].zombies.size()));
                    if (randomZ.getHealth() > bullet1.getWeapon().getDamage()) {
                        randomZ.setHealth(randomZ.getHealth() - bullet1.getWeapon().getDamage());
                    } else {
                        zombieAliveNumber -= 1;
                        map.board[c][r].zombies.remove(randomZ);
                        zombies.remove(randomZ);
                        if (zombieAliveNumber == 0) {
                            turnsAfterLastWave = 0;
                        }
                    }
                    speedReduce(randomZ, bullet1);
                    bullets.remove(bullet1);
                    break;
                }
            }
        }
    }
    private void speedReduce(Zombie zombie, Bullet bullet){
        zombie.speedReduce[0] = (int)(zombie.getSpeed() / bullet.getWeapon().getSpeedReduce());
        zombie.speedReduce[1] = 1;
    }

}
