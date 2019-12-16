package Controller.GameMode;
import Model.Map.Map;
public interface GameMode {
    Map generateMap();
    void handleWin();
}
