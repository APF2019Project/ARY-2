package Controller.GameMode;
import Controller.Game;
import Model.Account.Player;
import Model.Card.*;
import Model.Map.Map;

import java.util.ArrayList;
import java.util.Random;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Model.Map.Square;
import Model.Primary;
import Exception.invalidCardExeption;

import static Model.Primary.zombies;

public class Rail implements GameMode {
    private Player player;
    private Map map;
    private Plant selected;
    private int turn;
    private int turnToGenerateRandomPlant;
    private int numberOfKilledZombies;
    private int zombieAliveNumber;
    private int turnsAfterLastWave;
    private ArrayList<Card> selectedCards;
    ArrayList<Zombie> enemies = new ArrayList<>(); // badan bayad dar canstructor load shavad
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plantsInMap = new ArrayList<>();
    Random random = new Random();

    public Rail(){
        turn = 0;
        map = generateMap();
        this.selectedCards = Game.accounts[0].getCollection().selectedCards;
        turnsAfterLastWave = 4;
        zombieAliveNumber = 0;
        numberOfKilledZombies=0;
        player = Game.accounts[0].getPlayer();
        enemies.addAll(Primary.zombies);
    }
    public void remove(int column,int row){
        Square square=map.board[row][column];
        if(square.plant.size()!=0){
            square.plant.remove(0);
        }
        else {
            System.out.println("no plant is here");
        }
    }

    public void generateRandomPlant(){
        try {
            Plant plant = (Plant) Primary.plants.get(random.nextInt(Primary.plants.size())).clone();
            selectedCards.add(plant);
        }
        catch (CloneNotSupportedException e){

        }
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

    public void list(){
        for (int i = 0; i < selectedCards.size(); i++) {
            System.out.println(selectedCards.get(i).getName());
        }
    }
    @Override
    public Map generateMap() {
        return Map.generateDay();
    }

    public void record(){
        System.out.println("number of killed zombies: "+numberOfKilledZombies);
    }

    @Override
    public void showHand() throws NotPlantException {
        //show hand nadarim
    }

    @Override
    public void select(String a) throws NotPlantException {
        //in selecto nadarim
    }

    @Override
    public void select(int a) {
        if(a>selectedCards.size()){
            System.out.println("invalid number");
        }
        else{
            Plant plant= (Plant) selectedCards.get(a);
            selected=plant;
            System.out.println("selected successfully");
        }
    }

    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {
        if(selected != null) {
            if (map.board[column][row].plant.size() == 0) {
                try {
                    Plant tmp = (Plant)selected.clone();
                    ArrayList<Weapon> weapons = new ArrayList<>();
                    tmp.setRow(row);
                    tmp.setColumn(column);
                    for(int i=0 ; i<tmp.weapons.size() ; i++){
                        Weapon tmpWeapon = (Weapon) tmp.weapons.get(i);
                        tmpWeapon = (Weapon) tmpWeapon.clone();
                        tmpWeapon.setRow(row);
                        tmpWeapon.setColumn(column);
                        weapons.add(tmpWeapon);
                    }
                    tmp.weapons = weapons;

                    for(Weapon w : tmp.weapons){
                        w.turnsGenerate();
                    }
                    map.board[column][row].plant.add(tmp);
                    plantsInMap.add(tmp);
                    System.out.println(plantsInMap.get(0).getName());
                    //Game.accounts[0].getPlayer().setSun(Game.accounts[0].getPlayer().getSun() - selected.getSun());
                    //selected.setPermissionTime(selected.getTimeToReset());
                }catch (CloneNotSupportedException e){}
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
        for (Bullet bullet: bullets){
            System.out.println(bullet.getColumn()+"\t"+bullet.getRow()+" row and col of bullet");
        }
        for(Zombie zombie : zombies){
            System.out.println(zombie.getColumn()+"\t"+zombie.getRow()+"\t"+zombie.getHealth()+" zombie  "+zombie.getName()+" jun "+zombie.getHealth());
        }
    }

    @Override
    public void endTurn() {
        turn++;
       /* if(turn%4==0){
            turnToGenerateRandomPlant=random.nextInt(5);
        }
        if(turn%4==turnToGenerateRandomPlant && selectedCards.size()<10){
            generateRandomPlant();
        } */
        generateRandomPlant();
        for(Bullet bullet1 : bullets){
            bulletMove(bullet1);
        }
        for(int i1=0 ; i1<Map.colNumber; i1++) {
            for (int j = 0; j < Map.rowNumber; j++) {
                if (map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(0);
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
        for(Zombie zombie1 : zombies){
            if(map.board[zombie1.getColumn()][zombie1.getRow()-1].plant.size() == 0) {
                map.board[zombie1.getColumn()][zombie1.getRow()].zombies.remove(zombie1);
                zombie1.setRow(zombie1.getRow() - 1);
                map.board[zombie1.getColumn()][zombie1.getRow()].zombies.add(zombie1);
            }
        }
        healthDecrease();
        shoot();
        eatPlant();
        int numOfZombies = random.nextInt(4);
        for(int i=0;i<numOfZombies;i++){
            zombieGenerate();
        }



    }

    private void shoot(){
        for(int i1=0 ; i1<Map.colNumber; i1++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i1][j].plant.size() > 0) {
                    Plant plant1 = map.board[i1][j].plant.get(0);
                    for (Weapon weapon : plant1.weapons){
                        if(shootCondition(i1, j)){
                            if(weapon.turns.get(weapon.getTurn())){
                                bullets.add(weapon.bulletMaker());
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

    private void bulletMove(Bullet bullet1){
        bullet1.setRow(bullet1.getRow() + 1);
    }

    private void speedReduce(Zombie zombie, Bullet bullet){
        zombie.speedReduce[0] = (int)(zombie.getSpeed() / bullet.getWeapon().getSpeedReduce());
        zombie.speedReduce[1] = 1;
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


    @Override
    public void handleWin() {

    }

    @Override
    public void waveGenerate(){
    }


    private void zombieGenerate(){
        try {
            Zombie zombie = (Zombie) enemies.get(random.nextInt(enemies.size())).clone();
            zombie.setRow(Map.rowNumber - 1);
            int column = random.nextInt(Map.colNumber);
            zombie.setColumn(column);
            zombieAliveNumber += 1;
            zombies.add(zombie);
            map.board[column][Map.rowNumber - 1].zombies.add(zombie);
        } catch (CloneNotSupportedException e) {

        }
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
}

