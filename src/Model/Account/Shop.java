package Model.Account;

import Controller.Game;
import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Collection;
import Exception.invalidCardExeption;
import Model.Primary;

import java.util.ArrayList;

public class Shop {
    public ArrayList<Card> cardsNotBuy = new ArrayList();
    public ArrayList<Card> cardsBought=new ArrayList<>();
    public ArrayList<Plant> plantsBought = new ArrayList<>();
    public ArrayList<Zombie> zombiesBought= new ArrayList<>();
    private Player player;


    public Shop(){
        cardsNotBuy.addAll(Primary.plants);
        cardsNotBuy.addAll(Primary.zombies);
        player = Game.accounts[0].getPlayer();
    }

    public int findCard(String name, ArrayList<Card> arrayList) {
        name = name.toLowerCase();
        int index = -1;
        for (int i=0 ; i<arrayList.size() ; i++){
            if(arrayList.get(i).getName().toLowerCase().equals(name)){
                index = i;
            }
        }
        return index;
    }

    public static void calculateThePriceOfPlants(){
        for(Plant plant: Primary.plants){
            plant.setPrice(plant.getSun()*plant.getCoolDown()*plant.getHealth()+1);
        }
    }
    public static void calculateThePriceOfZombies(){
        for(Zombie zombie:Primary.zombies){
            zombie.setPrice((1+zombie.getSpeed())*zombie.getHealth()*10);
        }
    }
    public void showShop(){
        for (Card card : cardsNotBuy){
            System.out.println(card.getName());
        }
    }

    public void showCollection(){
        for(Card card: cardsBought){
            System.out.println(card.getName());
        }
    }
    public void buy(String name) throws invalidCardExeption{
        int index = findCard(name, cardsNotBuy);
        int price = 0;

        ArrayList<Card> tmp = new ArrayList<>();
        try {
            if (cardsNotBuy.get(index) instanceof Plant) {
                Plant plant = (Plant) cardsNotBuy.get(index);
                price = plant.getSun() * plant.getCoolDown() * plant.getHealth() + 1;
                if (player.getCoinForShop() >= price) {
                    cardsBought.add(cardsNotBuy.get(index));
                    plantsBought.add((Plant) cardsNotBuy.get(index));
                    cardsNotBuy.remove(index);
                    player.setCoinForShop(player.getCoinForShop() - price);
                    System.out.println("card bought successfully");

                }
            } else if (cardsNotBuy.get(index) instanceof Zombie) {
                Zombie zombie = (Zombie) cardsNotBuy.get(index);
                price = zombie.getHealth() * (1 + zombie.getSpeed()) * 10;
                if (player.getCoinForShop() >= price) {
                    cardsBought.add(cardsNotBuy.get(index));
                    zombiesBought.add((Zombie) cardsNotBuy.get(index));
                    cardsNotBuy.remove(index);
                    player.setCoinForShop(player.getCoinForShop() - price);
                    System.out.println("card bought successfully");
                }
            }
        }catch (Exception e){
            System.out.println("invalid card name");
            throw new invalidCardExeption();}
    }
    public void showMoney(){
        System.out.println("Your current money is:"+player.getCoinForShop());
    }

}
