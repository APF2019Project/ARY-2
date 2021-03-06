package Model.Card;

import Model.Map.Square;

import java.util.ArrayList;

public abstract class Card {
    private String name;
    private int health;
    private int coolDown;
    private int currentCoolDown;
    protected int damage;
    private Square location;
    private int price;
    private int row, column;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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

    public static int findCard(String name, ArrayList<Card> allCards){
        int index = -1;
        for (int i=0 ; i<allCards.size() ; i++){
            if(allCards.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
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
