package Model.Card;


import java.util.ArrayList;
import java.util.Random;

public class Weapon {
    private int row;
    private int column;
    private int turn;
    private int speedReduce; // agar 1 bud taghir nemikonad agar 2 bud nesf mishavad
    private Plant plant;
    private int cycle;
    private int numOfBulletInEachCycle;
    private int damage;
    private Random random = new Random();
    public ArrayList<Boolean> turns = new ArrayList<>();


    public Weapon(int cycle,int speedReduce,int numOfBulletInEachCycle,int damage){
        this.cycle=cycle;
        this.speedReduce=speedReduce;
        this.numOfBulletInEachCycle=numOfBulletInEachCycle;
        this.damage=damage;
        this.turn = -1;
    }

    public Bullet bulletMaker(){
        Bullet bullet = new Bullet(row, column, damage, this);
        return bullet;
    }

    public void turnsGenerate(){
        turns = new ArrayList<>();
        for(int i=0 ; i<numOfBulletInEachCycle; i++){
            turns.add(false);
        }
        int numOfTrue = 0;
        while (numOfTrue < numOfBulletInEachCycle){
            int index = random.nextInt(numOfBulletInEachCycle);
            if(!turns.get(index)){
                turns.set(index, new Boolean(true));
                numOfTrue += 1;
            }
        }
    }

    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public int getNumOfBulletInEachCycle() {
        return numOfBulletInEachCycle;
    }
    public void setNumOfBulletInEachCycle(int numOfBulletInEachTurn) {
        this.numOfBulletInEachCycle = numOfBulletInEachTurn;
    }
    public int getCycle() {
        return cycle;
    }
    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    public int getSpeedReduce() {
        return speedReduce;
    }
    public void setSpeedReduce(int speedReduse) {
        this.speedReduce = speedReduce;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
}
