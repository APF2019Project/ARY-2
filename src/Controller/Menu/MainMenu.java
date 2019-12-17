package Controller.Menu;

public class MainMenu extends Menu {
    private static MainMenu menu;
    public MainMenu(String name) {
        super(name);
    }

    public static MainMenu getMenu(){
        if(MainMenu.menu==null){
            MainMenu.menu=new MainMenu("MainMenu");
        }
        return menu;
    }
}
