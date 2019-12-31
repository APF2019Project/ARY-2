package Controller.GameMode;
import Model.Map.Map;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Exception.invalidCardExeption;
public interface GameMode  {
    Map generateMap();
    void showHand() throws NotPlantException;
    void select(String a) throws NotPlantException;
    void select(int a);
    void plant(int row, int column) throws noCardSelected, CloneNotSupportedException;
    void showLawn();
    void endTurn();
    void record();
    void list();
    void remove(int column,int row);
    void handleWin();
    void waveGenerate();
    boolean isStart();
    void setStart(boolean b);
    void showLanes();
    void put(String name, int number) throws CloneNotSupportedException, invalidCardExeption;
}
