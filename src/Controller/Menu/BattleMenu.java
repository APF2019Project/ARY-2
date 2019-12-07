package Controller.Menu;

public class BattleMenu extends Menu {
    private static BattleMenu battleMenu;

    private BattleMenu(String name) {
        super(name);
    }

    public static BattleMenu getBattleMenu()
    {
        if(battleMenu==null){
            battleMenu=new BattleMenu("BattleMenu");
        }
        return battleMenu;
    }

}
