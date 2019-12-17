package View;
import Controller.Menu.*;
public class MenuHandler {
    public static Menu currentMenu;

    public static void initMenus(){
        LoginMenu.getLoginMenu().addSubMenu(LeaderBoardMenu.getLeaderBoardMenu());
        LoginMenu.getLoginMenu().addSubMenu(CollectionMenu.getCollectionMenu());
        LoginMenu.getLoginMenu().addSubMenu(MainMenu.getMenu());

//        MainMenu.getMenu().addSubMenu();
        MainMenu.getMenu().addSubMenu(Profile.getProfile());
        MainMenu.getMenu().addSubMenu(ShopMenu.getShopMenu());


    }
    public static void main(String[] args){

    }

    public static void showMenu(){
//        MenuHandler.currentMenu
    }
}
