package Model.Card;

import Model.Map.Square;

import java.util.ArrayList;

public abstract class Card {
    private String name;
    private int health;
    private int coolDown;
    private int currentCoolDown;
    private int damage;
    private Square location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

//    public static int findCard(String name, ArrayList<Card> allCards){
//        int index = -1;
//        for (int i=0 ; i<allCards.size() ; i++){
//            if(allCards.get(i).getName().equals(name)){
//                index = i;
//            }
//        }
//        return index;
//    }


}
