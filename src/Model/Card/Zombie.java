package Model.Card;

public class Zombie extends Card {
    private int speed;
    private int shieldHealth;
    private boolean startDelay;

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
