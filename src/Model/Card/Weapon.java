package Model.Card;

public class Weapon {
    private int row;
    private int column;
    private int turn;
    private int numOfBulletInEachTurn;
    private int cycle;
    private int power;
    private int speedReduce;
    private Plant plant;

    public Bullet bulletMaker(){
        Bullet bullet = new Bullet(row, column, plant);
        return bullet;
    }


    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public int getNumOfBulletInEachTurn() {
        return numOfBulletInEachTurn;
    }
    public void setNumOfBulletInEachTurn(int numOfBulletInEachTurn) {
        this.numOfBulletInEachTurn = numOfBulletInEachTurn;
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
    public Plant getPlant() {
        return plant;
    }
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
