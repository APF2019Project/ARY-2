package Model.Account;

import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Collection;
import Exception.invalidCardExeption;
import java.util.ArrayList;

public class Shop {
    static ArrayList<Card> cardsNotBuy = new ArrayList();
    private Player player;

    public static int findCard(String name) {
        int index = -1;
        for (int i=0 ; i<cardsNotBuy.size() ; i++){
            if(cardsNotBuy.get(i).getName().equals(name)){
                index = i;
            }
        }
        return index;
    }

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
        int index = findCard(name);
        int price = 0;

        if(cardsNotBuy.get(index) instanceof Plant){
            Plant plant = (Plant)cardsNotBuy.get(index);
            price = plant.getSun() * plant.getCoolDown() * plant.getHealth() + 1;
        }
        else if(cardsNotBuy.get(index) instanceof Zombie){
            Zombie zombie = (Zombie) cardsNotBuy.get(index);
            price = zombie.getHealth() * (1 + zombie.getSpeed()) * 10;
        }

        if(player.getCoinForShop() >= price) {
            Collection.allCards.add(cardsNotBuy.get(index));
            cardsNotBuy.remove(index);
            player.setCoinForShop(player.getCoinForShop() - price);
        }
    }

}
