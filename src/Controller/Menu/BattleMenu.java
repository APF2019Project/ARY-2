package Controller.Menu;

import Controller.GameMode.GameMode;
import Model.Account.Player;
import Model.Map.Map;

public class BattleMenu extends Menu {
    private static BattleMenu battleMenu;
    private Map map;
    private Player[] players = new Player[2];
    private int turn = 0;
    private GameMode gameMode;


    private BattleMenu(String name) {
        super(name);
    }

    public static BattleMenu getBattleMenu() {
        if(battleMenu==null){
            battleMenu=new BattleMenu("BattleMenu");
        }
        return battleMenu;
    }

    public static void setBattleMenu(BattleMenu battleMenu) {
        BattleMenu.battleMenu = battleMenu;
    }
    public Map getMap() {
        return map;
    }
    public void setMap(Map map) {
        this.map = map;
    }
    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(Player[] players) {
        this.players = players;
    }
    public int getTurn() {
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }
    public GameMode getGameMode() {
        return gameMode;
    }
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
