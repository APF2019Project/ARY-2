package Controller.GameMode;

import Model.Card.Bullet;
import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Weapon;
import Model.Collection;
import Model.Account.Player;
import Model.Map.Map;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Model.Map.Square;
import java.util.ArrayList;

public class Day implements GameMode{
    private Plant selected;
    private ArrayList<Card> selectedCards;
    private Player player;
    private Map map;
    ArrayList<Bullet> bullets = new ArrayList<>();

    public void showHand() throws NotPlantException{
            for (int i = 0; i < selectedCards.size(); i++) {
                Plant plant = (Plant)selectedCards.get(i);
                System.out.println(selectedCards.get(i).getName() + " "
                        +plant.getSun() + " " + plant.getTimeToReset());
            }
    }
    public void select(String name) throws NotPlantException{
        int index = Collection.findCard(name, selectedCards);
        Plant plant = (Plant) selectedCards.get(index);
        if (player.getSun() >= plant.getSun() && plant.getTimeToReset() == 0)
            selected = plant;
    }
    public void plant(int row, int column) throws noCardSelected{
        if(selected != null) {
            if (map.board[row][column].plant.size() == 0) {
                map.board[row][column].plant.add(selected);
            }
        } else {
            System.out.println("no card selected");
            throw new noCardSelected();
        }
    }
    public void healthDecrease(){
        //dar har turn check mikonad agar tir be zombie khord az janash gozashtan
    }
    public void shoot(){
        for(int i=0 ; i<Map.colNumber; i++){
            for (int j=0 ; j < Map.rowNumber ; j++) {
                if(map.board[i][j].plant.size() > 0) {
                    Plant plant1 = map.board[i][j].plant.get(0);
                    if(plant1.getTimeToReset() <= plant1.getLastshoot() && shootConditioin(i, j)){
                        for (Weapon weapon : plant1.weapons){
                            bullets.add(weapon.bulletMaker());
                            plant1.setLastshoot(0);
                        }
                    }
                }
            }
        }
    }
    public boolean shootConditioin(int column, int row){
        for(int i=row ; i<Map.rowNumber ; i++){
            if(map.board[column][i].zombies.size()>0){
                return true;
            }
            return false;
        }
    }

    @Override
    public Map generateMap() {
        return Map.generateDay();
    }

    @Override
    public void handleWin() {

    }
}
