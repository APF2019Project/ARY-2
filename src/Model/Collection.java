package Model;
import Model.Card.*;
import Exeption.invalidCardExeption;
import java.util.ArrayList;

public class Collection {
    public static ArrayList<Plant> allCards = new ArrayList<>();
    public ArrayList<Plant> unSelectedCards = new ArrayList<>();
    public ArrayList<Plant> selectedCards = new ArrayList<>();
    private int maxCard;


    public void showHand(){
        for(Card card : selectedCards){
            System.out.println(card.getName());
        }
    }
    public void select(String name) throws invalidCardExeption{
        int index = Plant.findCard(name, allCards);
        selectedCards.add(allCards.get(index));
    }
    public void showCollection(){
        for (Card card : unSelectedCards){
            System.out.println(card.getName());
        }
    }
    public void remove(String name) throws invalidCardExeption {
        int index = Plant.findCard(name, selectedCards);
        selectedCards.remove(index);
    }
    public static int findCard(String name, ArrayList<Plant> allCards){
        int index = -1;
        for (int i=0 ; i<allCards.size() ; i++){
            if(allCards.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
    }
}
