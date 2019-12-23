package View;
import Controller.Game;
import Controller.Menu.*;
import Model.Primary;

import java.io.IOException;
import java.util.Scanner;

public class MenuHandler {
    public static Menu currentMenu;

    public static void initMenus(){
        LoginMenu.getLoginMenu().addSubMenu(LeaderBoardMenu.getLeaderBoardMenu());
        LoginMenu.getLoginMenu().addSubMenu(CollectionMenu.getCollectionMenu());
        LoginMenu.getLoginMenu().addSubMenu(MainMenu.getMenu());

        MainMenu.getMenu().addSubMenu(Profile.getProfile());
        MainMenu.getMenu().addSubMenu(ShopMenu.getShopMenu());

        currentMenu = LoginMenu.getLoginMenu();

    }
    public static void main(String[] args) throws IOException {
        try {
            initMenus();
            Primary.preprocess();
        }catch (Exception e){}
        Primary.json();
        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.play();
    }

    public static void showMenu(){
//        MenuHandler.currentMenu
    }
    public static Scanner getGameScanner(){
        return Game.accounts[BattleMenu.getBattleMenu().getTurn()].getPlayer().getOutputStream();
    }
}
