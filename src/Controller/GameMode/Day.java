package Controller.GameMode;

import Model.Card.Plant;
import Model.Collection;
import Model.Account.Player;
import Model.Map.Map;
import Exception.NotPlantException;

public class Day implements GameMode{
    private Plant selected;
    private Collection collection;
    private Player player;

    public void showHand() throws NotPlantException{
            for (int i = 0; i < collection.selectedCards.size(); i++) {
                Plant plant = (Plant)collection.selectedCards.get(i);
                System.out.println(collection.selectedCards.get(i).getName() + " "
                        +plant.getSun() + " " + plant.getTimeToReset());
            }
    }
    public void select(String name) throws NotPlantException{
        int index = Collection.findCard(name, collection.selectedCards);
        Plant plant = (Plant) collection.selectedCards.get(index);
        if (player.getSun() >= plant.getSun() && plant.getTimeToReset() == 0)
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
