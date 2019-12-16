package Model.Card;

import Model.Map.Map;

import java.util.ArrayList;

public class Plant extends Card{
    private int sun;
    private int activateDelay;
    private int timeToReset;
    private boolean isWater;
    public ArrayList<Weapon> weapons = new ArrayList<>();

    public static int findCard(String name, ArrayList<Plant> allCards){
        int index = -1;
        for (int i=0 ; i<allCards.size() ; i++){
            if(allCards.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
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
}
