package Controller;

import Controller.Menu.BattleMenu;
import Model.Account.Account;

import java.util.Scanner;

public class Game {
    public static Account[] accounts ={Account.getDefaultAccount(),Account.getDefaultAccount()};
    public static BattleMenu battle= BattleMenu.getBattleMenu();
    public static boolean hasLoggedIn = false;
    public static Scanner scanner=new Scanner(System.in);
}
