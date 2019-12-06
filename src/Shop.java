import java.util.ArrayList;

public class Shop {
    static ArrayList<Card> cardsNotBuy = new ArrayList();

    public void showShop(){
        for (Card card : cardsNotBuy){
            System.out.println(card.name);
        }
    }
    public void showCollection(){
        for (Card card : Collection.allCards){
            System.out.println(card.name);
        }
    }
    public void buy(String name){
        int index = Find.findCard(name, cardsNotBuy);
        Collection.allCards.add(cardsNotBuy.get(index));
        cardsNotBuy.remove(index);
        // pool bayad kam shavad
    }


}
