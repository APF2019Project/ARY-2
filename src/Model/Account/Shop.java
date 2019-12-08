package Model.Account;

import Model.Card.Card;
import Model.Collection;
import Exeption.invalidCardExeption;
import java.util.ArrayList;

public class Shop {
    static ArrayList<Card> cardsNotBuy = new ArrayList();

    public void showShop(){
        for (Card card : cardsNotBuy){
            System.out.println(card.getName());
        }
    }
    public void showCollection(){
        for (Card card : Collection.allCards){
            System.out.println(card.getName());
        }
    }
    public void buy(String name) throws invalidCardExeption{
        int index = Card.findCard(name, cardsNotBuy);
        Collection.allCards.add(cardsNotBuy.get(index));
        cardsNotBuy.remove(index);
        // pool bayad kam shavad
    }

}
