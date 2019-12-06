import com.gilecode.yagson.YaGson;

import java.util.ArrayList;

public class Collection {
    public static ArrayList<Card> allCards = new ArrayList<>();
    public ArrayList<Card> unSelectedCards = new ArrayList<>();
    public ArrayList<Card> selectedCards = new ArrayList<>();
    int maxCard;


    public void showHand(){
        for(Card card : selectedCards){
            System.out.println(card.name);
        }
    }
    public void select(String name){
        int index = Find.findCard(name, allCards);
        if(index == -1){
            System.out.println("invalid card");
        }else {
            selectedCards.add(allCards.get(index));
        }
    }
    public void showCollection(){
        for (Card card : unSelectedCards){
            System.out.println(card.name);
        }
    }
    public void remove(String name){
        int index = Find.findCard(name, selectedCards);
        if(index == -1){
            System.out.println("invalid card");
        }else {
            selectedCards.remove(index);
        }
    }
}
