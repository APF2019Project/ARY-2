package Model.Card;


import java.util.ArrayList;
import java.util.Random;

public class Weapon {
    private int row;
    private int column;
    private int turn;
    private int power;
    private int speedReduce;
    private int cycle;
    private int numOfBulletInEachCycle;
    private int damage;
    private Random random = new Random();
    public ArrayList<Boolean> turns = new ArrayList<>();


    public Weapon(int power){
        this.power = power;
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
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
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
}
