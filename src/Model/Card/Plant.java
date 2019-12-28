package Model.Card;

import Model.Map.Map;

import java.util.ArrayList;

public class Plant extends Card implements Cloneable{
    private int sun;
    private int activateDelay;
    private int permissionTime;
    private int timeToReset;
    private int firstShoot;
    private boolean isWater;
    private boolean isSunFlower;
    private boolean isCatTail;
    private boolean isLilyPad;
    private boolean hasMagnet;
    private boolean isSnowPea;
    private boolean isScaredMushroom;
    private boolean isTangleKelp;
    private boolean isNut;
    private boolean isFreezing;
    private boolean isCactus;
    public ArrayList<Weapon> weapons = new ArrayList<>();

    public Plant(){}
    public Plant(String name,int health,int cooldown,int sun,int timeToReset,int activateDelay,boolean isWater,int price,boolean isSunFlower,boolean isCatTail, boolean isLilyPad,boolean hasMagnet,boolean isSnowPea,boolean isScaredMushroom,boolean isTangleKelp, boolean isNut,boolean isFreezing,boolean isCactus,Weapon weapon){
        super.setName(name);
        super.setHealth(health);
        super.setCoolDown(cooldown);
        setSun(sun);
        setTimeToReset(timeToReset);
        setActivateDelay(activateDelay);
        setWater(isWater);
        super.setPrice(price);
        setSunFlower(isSunFlower);
        setCatTail(isCatTail);
        setLilyPad(isLilyPad);
        setHasMagnet(hasMagnet);
        setSnowPea(isSnowPea);
        setScaredMushroom(isScaredMushroom);
        setTangleKelp(isTangleKelp);
        setNut(isNut);
        setFreezing(isFreezing);
        setCactus(isCactus);
        weapon.setRow(this.getRow());
        weapon.setColumn(this.getColumn());
        this.weapons.add(weapon);
    }


    public int getFirstshoot() {
        return firstShoot;
    }
    public void setFirstshoot(int lastshoot) {
        this.firstShoot = firstShoot;
    }
    public int getSun() {
        return sun;
    }
    public void setSun(int sun) {
        this.sun = sun;
    }
    public int getActivateDelay() {
        return activateDelay;
    }
    public void setActivateDelay(int activateDelay) {
        this.activateDelay = activateDelay;
    }
    public boolean isWater() {
        return isWater;
    }
    public void setWater(boolean water) {
        isWater = water;
    }
    public int getTimeToReset() {
        return timeToReset;
    }
    public void setTimeToReset(int timeToReset) {
        this.timeToReset = timeToReset;
    }
    public boolean isSunFlower() {
        return isSunFlower;
    }
    public void setSunFlower(boolean sunFlower) {
        isSunFlower = sunFlower;
    }
    public boolean isCatTail() {
        return isCatTail;
    }
    public void setCatTail(boolean catTail) {
        isCatTail = catTail;
    }
    public boolean isLilyPad() {
        return isLilyPad;
    }
    public void setLilyPad(boolean lilyPad) {
        isLilyPad = lilyPad;
    }
    public boolean isHasMagnet() {
        return hasMagnet;
    }
    public void setHasMagnet(boolean hasMagnet) {
        this.hasMagnet = hasMagnet;
    }
    public boolean isSnowPea() {
        return isSnowPea;
    }
    public void setSnowPea(boolean snowPea) {
        isSnowPea = snowPea;
    }
    public boolean isScaredMushroom() {
        return isScaredMushroom;
    }
    public void setScaredMushroom(boolean scaredMushroom) {
        isScaredMushroom = scaredMushroom;
    }
    public boolean isTangleKelp() {
        return isTangleKelp;
    }
    public void setTangleKelp(boolean tangleKelp) {
        isTangleKelp = tangleKelp;
    }
    public boolean isNut() {
        return isNut;
    }
    public void setNut(boolean nut) {
        isNut = nut;
    }
    public boolean isFreezing() {
        return isFreezing;
    }
    public void setFreezing(boolean freezing) {
        isFreezing = freezing;
    }
    public boolean isCactus() {
        return isCactus;
    }
    public void setCactus(boolean cactus) {
        isCactus = cactus;
    }
    public int getPermissionTime() {
        return permissionTime;
    }
    public void setPermissionTime(int permissionTime) {
        this.permissionTime = permissionTime;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
