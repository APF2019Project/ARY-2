package Model.Card;

public class Zombie extends Card implements Cloneable {
    private int speed;
    private int shieldHealth;
    private boolean startDelay;
    private boolean isWater;
    public int[] speedReduce = new int[2];//avali effect ast va dovomi modati ke mande ast

    public int getShieldHealth() {
        return shieldHealth;
    }

    public void setShieldHealth(int shieldHealth) {
        this.shieldHealth = shieldHealth;
    }

    public boolean isStartDelay() {
        return startDelay;
    }

    public void setStartDelay(boolean startDelay) {
        this.startDelay = startDelay;
    }

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean water) {
        isWater = water;
    }

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Zombie(String name,int health,int speed,int shieldHealth,boolean isWater){
        super.setName(name);
        super.setHealth(health);
        setSpeed(speed);
        setShieldHealth(shieldHealth);
        setWater(isWater);
    }

    public Zombie(){}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
