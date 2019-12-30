package Model.Map;

import Model.Card.Plant;
import Model.Card.Zombie;

import java.util.ArrayList;

public class Map {
    static ArrayList<Map> maps = new ArrayList<>();
    public static final int rowNumber = 19;
    public static final int colNumber = 6;
    public Square[][] board = new Square[colNumber][rowNumber+3];

    public static Map generateDay(){
        Map map = new Map();
        for (int i=0 ; i<colNumber ; i++) {
            for (int j = 0; j <= rowNumber + 1; j++) {
                map.board[i][j] = new Square(i, j);
            }
        }
        return map;
    }

    public static Map generateWater(){
        Map map = new Map();
        for (int i=0 ; i<colNumber ; i++)
            for (int j=0 ; j<=rowNumber+1; j++){
                map.board[i][j] =new Square(i, j);
                if(i == 2 || i == 3)
                    map.board[i][j].setWater(true);
            }
        return map;
    }



}
