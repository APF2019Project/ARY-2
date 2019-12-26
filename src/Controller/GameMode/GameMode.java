package Controller.GameMode;
import Model.Map.Map;
import Exception.NotPlantException;
import Exception.noCardSelected;
public interface GameMode  {
    Map generateMap();
    void showHand() throws NotPlantException;
    void select(String a) throws NotPlantException;
    void plant(int row, int column) throws noCardSelected, CloneNotSupportedException;
    void showLawn();
    void endTurn();
    void handleWin();
}
