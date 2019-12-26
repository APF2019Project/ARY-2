package Model;
import Controller.Game;
import Controller.GameMode.GameMode;
import Controller.Menu.Menu;
import Model.Card.*;
import Exception.invalidCardExeption;
import java.util.ArrayList;
import java.util.Arrays;

public class Collection {
    public ArrayList<Card> unSelectedCards = new ArrayList<>();
    public ArrayList<Card> selectedCards = new ArrayList<>();
    private boolean isPlantCollection;
    private int maxCard;
    private GameMode playGameMode;

    public Collection(){
    }

    public void init(boolean isPlant, GameMode gameMode){
        isPlantCollection = isPlant;
        if(isPlantCollection) {
            unSelectedCards.addAll(Game.accounts[0].getShop().plantsBought);
            //card haye avalie badan bayad ezafe shavad
        }else {
            unSelectedCards.addAll(Game.accounts[0].getShop().zombiesBought);
            //card haye avalie badan bayad ezafe shavad
        }
        this.playGameMode = gameMode;
    }
    public void showHand(){
        for(Card card : selectedCards){
            System.out.println(card.getName());
        }
        if(selectedCards.size() == 0)
            System.out.println("no card selected");
    }
    public void select(String name) throws invalidCardExeption{
        int index = findCard(name, unSelectedCards);
        selectedCards.add(unSelectedCards.get(index));
        unSelectedCards.remove(index);
        System.out.println("select successfully");
    }
    public void showCollection(){
        for (Card card : unSelectedCards){
            System.out.println(card.getName());
        }
    }
    public void remove(String name) throws invalidCardExeption {
        int index = findCard(name, selectedCards);
        if(index != -1) {
            unSelectedCards.add(selectedCards.get(index));
            selectedCards.remove(index);
            System.out.println("remove successfully");
        }else {
            System.out.println("there is no card with this name in selected card");
        }
    }
    public static int findCard(String name, ArrayList<Card> array) {
        int index = -1;
        for (int i=0 ; i<array.size() ; i++){
            if(array.get(i).getName().toLowerCase().equals(name)){
                index = i;
            }
        }
        return index;
    }
    public boolean isPlantCollection() {
        return isPlantCollection;
    }
    public void setPlantCollection(boolean plantCollection) {
        isPlantCollection = plantCollection;
    }
    public GameMode getPlayGameMode() {
        return playGameMode;
    }
}
