package Controller.GameMode;

import Controller.Game;
import Model.Account.Player;
import Model.Card.*;
import Model.Map.Map;
import Exception.noCardSelected;
import Exception.NotPlantException;
import Model.Primary;
import Exception.invalidCardExeption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ZombieMode implements GameMode{
    private Random random = new Random();
    private ArrayList<Card> selectedZombie;
    private ArrayList<Plant>  plantsInMap = new ArrayList<>();
    private ArrayList<Zombie> zombiesInWave = new ArrayList<>();
    private ArrayList<Zombie> zombiesInMap = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean isWater;
    private boolean isStart;
    private Player player;
    Map map;

    public ZombieMode(){
        this.selectedZombie = Game.accounts[0].getCollection().selectedCards;
        map = generateMap();
        this.player = Game.accounts[0].getPlayer();
    }

    public Zombie zombieGenerate(String name) throws CloneNotSupportedException, invalidCardExeption{
        Zombie result;
        Zombie zombie = new Zombie();
        boolean check = false;
        for(Card card : selectedZombie){
            if(card.getName().toLowerCase().equals(name)){
                zombie = (Zombie) card;
                check = true;
            }
        }
        result = (Zombie) zombie.clone();
        if(!check){
            System.out.println("invalid card name");
            throw new invalidCardExeption();
        }
        return result;
    }

    @Override
    public void put (String name, int number)throws CloneNotSupportedException, invalidCardExeption{
        if(zombiesInMap.size() + number <= 12) {
            for (int i = 0; i < number; i++) {
                zombiesInWave.add(zombieGenerate(name));
                System.out.println(zombiesInWave.get(zombiesInWave.size()-1).getName()+"  "+zombiesInMap.size());
            }
            waveGenerate();
        }else {
            System.out.println("out of limit Zombie for this Wave");
        }
    }

    @Override
    public void waveGenerate(){
        while (zombiesInWave.size() > 0){
            int col = random.nextInt(6);
            Zombie tmp = zombiesInWave.get(random.nextInt(zombiesInWave.size()));
            if(map.board[col][Map.rowNumber-1].zombies.size() == 0) {
                map.board[col][Map.rowNumber - 1].zombies.add(tmp);
                tmp.setRow(Map.rowNumber-1);
                tmp.setColumn(col);
                zombiesInWave.remove(tmp);
                zombiesInMap.add(tmp);
            }else if(map.board[col][Map.rowNumber-1].zombies.size() == 1 && map.board[col][Map.rowNumber].zombies.size() == 0){
                map.board[col][Map.rowNumber].zombies.add(tmp);
                zombiesInWave.remove(tmp);
                tmp.setRow(Map.rowNumber);
                tmp.setColumn(col);
                zombiesInMap.add(tmp);
            }

        }
    }

    @Override
    public boolean isStart() {
        return isStart;
    }

    @Override
    public void setStart(boolean b) {
        isStart = b;
    }

    @Override
    public void showLanes() {
        zombiesInMap.sort(Comparator.comparing(Zombie::getColumn));
        for(int i=0 ; i<zombiesInMap.size() ; i++){
            System.out.println(zombiesInMap.get(i).getName() +"\tcolumn: "+zombiesInMap.get(i).getColumn());
        }
    }

    public void generatePlantInMapDay(Map map1) throws CloneNotSupportedException{
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));

        for(int i=0 ; i<6 ; i++){
            plantsInMap.add(Primary.plantFindAndMake("Scaredy-shroom"));
        }

        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));
        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));

        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));
        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));

        plantsInMap.add(Primary.plantFindAndMake("Threepeater"));

        plantsInMap.add(Primary.plantFindAndMake("Gatling-Pea"));

        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
        plantInMapDay(map1);
    }
    public void plantInMapDay(Map map1){
        int t=0;
        Collections.shuffle(plantsInMap);
        for(int i=0 ; i<Map.colNumber ; i++){
            for(int j=0 ; j<3 ; j++){
                map1.board[i][j].plant.add( plantsInMap.get(t) );
                plantsInMap.get(t).setRow(j);
                plantsInMap.get(t).setColumn(i);
                plantsInMap.get(t).setWeaponsCoordinate();
                t += 1;
            }
        }
    }
    public void generatePlantInMapWater(Map map1) throws CloneNotSupportedException{
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));

        plantsInMap.add(Primary.plantFindAndMake("Tangle-Kelp"));

        for(int i=0 ; i<6 ; i++){
            plantsInMap.add(Primary.plantFindAndMake("Scaredy-shroom"));
        }

        plantsInMap.add(Primary.plantFindAndMake("Cattail"));

        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));
        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));

        plantsInMap.add(Primary.plantFindAndMake("Gatling-Pea"));

        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));
        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));


        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));

        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
    }

    private boolean shootCondition(int column, int row){
        for(int i=row ; i<Map.rowNumber ; i++) {
            if (map.board[column][i].zombies.size() > 0) {
                return true;
            }
        }
        return false;
    }
    private void shoot(){
        for(int i1=0 ; i1<Map.colNumber; i1++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(0);
                    for (Weapon weapon : plant1.weapons){
                        if(weapon.getCycle() > 0 && shootCondition(i1, j)){
                            if(weapon.turns.get(weapon.getTurn())){
                                bullets.add(weapon.bulletMaker());
                            }
                        }
                    }
                }
            }
        }
    }
    private void bulletMove(Bullet bullet1){
        bullet1.setRow(bullet1.getRow() + 1);
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
                        map.board[c][r].zombies.remove(randomZ);
                        zombiesInMap.remove(randomZ);
                    }
                    bullets.remove(bullet1);
                    break;
                }
            }
        }
    }
    private void eatPlant(){ //zombie haa plant ro mikhoran
        for(Zombie z : zombiesInMap){
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

    @Override
    public Map generateMap() {
//        int a = random.nextInt(2);
        int a=0;
        try {
            if (a == 0) {
                isWater = false;
                Map map1 = Map.generateDay();
                generatePlantInMapDay(map1);
                return map1;
            } else {
                isWater = true;
                Map map1 = Map.generateWater();
                generatePlantInMapWater(map1);
                return map1;
            }
        }catch (CloneNotSupportedException e){}
        return Map.generateDay();
    }
    @Override
    public void showHand() throws NotPlantException {
        for(Card c : selectedZombie){
            Zombie zombie = (Zombie) c;
            System.out.println(zombie.getName()+"\tHealth: "+zombie.getHealth());
        }
    }

    @Override
    public void select(String a) throws NotPlantException {

    }

    @Override
    public void select(int a) {

    }
    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {

    }
    @Override
    public void showLawn() {
        for (Plant plant1 : plantsInMap){
            System.out.println(plant1.getName()+"\thealth: "+plant1.getHealth()+
                    "\tcoordinate: ("+plant1.getColumn()+","+plant1.getRow()+")");
        }
        for (Bullet bullet: bullets){
            System.out.println(bullet.getColumn()+"\t"+bullet.getRow()+" col and row of bullet  "+bullet.getWeapon().getRow());
        }
        for(int i=0 ; i<Map.rowNumber+2 ; i++){
            for (int j=0 ; j<Map.colNumber ; j++) {
                    for(Zombie zombie : map.board[j][i].zombies){
                        System.out.println("Zombie "+zombie.getName()+"\tHealth: "+zombie.getHealth()+
                                "\tcoordinate:("+zombie.getColumn()+","+zombie.getRow()+")");
                    }
                }
            }
    }
    @Override
    public void endTurn() {
        for(int i1=0 ; i1<Map.colNumber; i1++) {
            for (int j = 0; j < Map.rowNumber; j++) {
                if (map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(0);
                    for (Weapon w : plant1.weapons) {
                        if(w.getCycle() > 0) {
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
        }

        for(Zombie zombie1 : zombiesInMap){
            if(map.board[zombie1.getColumn()][zombie1.getRow()-1].plant.size() == 0) {
                map.board[zombie1.getColumn()][zombie1.getRow()].zombies.remove(zombie1);
                zombie1.setRow(zombie1.getRow() - 1);
                map.board[zombie1.getColumn()][zombie1.getRow()].zombies.add(zombie1);
            }
        }

        for(Bullet bullet1 : bullets){
            bulletMove(bullet1);
        }

        healthDecrease();
        eatPlant();
        shoot();
    }
    @Override
    public void handleWin() {

    }
}

