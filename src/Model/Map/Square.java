package Model.Map;

import Model.Card.Plant;
import Model.Card.Zombie;

import java.util.ArrayList;

public class Square {
    private int rowNumber;
    private int columnNumber;
    private boolean isWater;
    private boolean isFull;
    public ArrayList<Plant> plant = new ArrayList<>();
    public ArrayList<Zombie> zombies = new ArrayList<>();


    public
    Square(int i, int j){
        this.columnNumber = i;
        this.rowNumber = j;
        this.isFull = false;
        this.isWater = false;
    }

    public int getRowNumber() {
        return rowNumber;
    }
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    public int getColumnNumber() {
        return columnNumber;
    }
    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
    public boolean isWater() {
        return isWater;
    }
    public void setWater(boolean water) {
        isWater = water;
    }
}
