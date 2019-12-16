package Controller.GameMode;

import Model.Card.Plant;
import Model.Collection;
import Model.Account.Player;
import Model.Map.Map;

public class Day implements GameMode{
    private Plant selected;
    private Collection collection;
    private Player player;

    public void showHand(){
        for (int i = 0; i < collection.selectedCards.size(); i++){
            System.out.println(collection.selectedCards.get(i).getName()+" "
                    +collection.selectedCards.get(i).getSun()+" "+collection.selectedCards.get(i).getTimeToReset());
        }
    }
    public void select(String name){
        int index = Collection.findCard(name, collection.selectedCards);
        Plant plant = collection.selectedCards.get(index);
        if(player.getSun() >= plant.getSun() && plant.getTimeToReset() == 0)
            selected = plant;
    }
    public void plant(int row, int column){

    }

    @Override
    public Map generateMap() {
        return Map.generateDay();
    }

    @Override
    public void handleWin() {

    }
}
