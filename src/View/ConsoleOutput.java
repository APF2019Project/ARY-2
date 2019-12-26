package View;

import Controller.Game;
import Controller.GameMode.Day;
import Controller.Menu.LoginMenu;
import Controller.Menu.*;
import Exception.*;
import Model.Account.Account;
import Model.Card.Plant;
import Model.Collection;
import Model.Primary;

public class ConsoleOutput {
    public static String[] scanUserPass(){
        String[] result = new String[3];
        System.out.print("enter Username: ");
        result[0] = MenuHandler.getGameScanner().nextLine();
        System.out.print("enter Password: ");
        result[1] = MenuHandler.getGameScanner().nextLine();
        return result;
    }
    public static boolean checkPass(String user, String pass){
        try {
            return Account.getAccount(user).getPassword().equals(pass);
        }catch (InvalidAccountException e){
            System.out.println("invalid user name");
            return false;
        }
    }
    public static void commandHandlerBegin(String[] input) throws invalidCardExeption {
        String username = "";
        String password = "";

        if(MenuHandler.currentMenu instanceof LoginMenu) {
            //initial user and pass
            if( (input[0].equals("create") && input[1].equals("account")) || input[0].equals("login") ){
                String[] userAndPas = scanUserPass();
                username = userAndPas[0];
                password = userAndPas[1];
            }

            if (input[0].equals("create") && input[1].equals("account")) {
                if (MenuHandler.currentMenu instanceof LoginMenu) {
                    LoginMenu loginMenu = (LoginMenu) MenuHandler.currentMenu;
                    try {
                        loginMenu.creatAccount(username, password);
                    } catch (AccountAlreadyExistsException e) {
                    }
                } else {
                    System.out.println("you are not in Login Menu");
                }
            }

            if (input[0].equals("login")) {
                if (MenuHandler.currentMenu instanceof LoginMenu) {
                    LoginMenu loginMenu = (LoginMenu) MenuHandler.currentMenu;
                    try {
                        loginMenu.login(username, password);
                        loginMenu.enter(MainMenu.getMenu());
                        System.out.println("login successfully");
                    } catch (InvalidAccountException e) {
                        System.out.println("invalid username");
                    } catch (WrongPasswordException e) {
                        System.out.println("please enter correct password");
                    }
                } else {
                    System.out.println("you are not in Login Menu");
                }
            }
            if (input[0].equals("leaderboard")) {
                if (MenuHandler.currentMenu instanceof LoginMenu) {
                    LoginMenu.getLoginMenu().enter(LeaderBoardMenu.getLeaderBoardMenu());
                    LeaderBoardMenu.getLeaderBoardMenu().show();
                }
            }
        }

        if(MenuHandler.currentMenu instanceof MainMenu){
            if(input[0].equals("profile")){
                MainMenu.getMenu().enter(Profile.getProfile());
            }
            if(input[0].equals("play")){
                MainMenu.getMenu().enter(Play.getMenu());
                //initial sazi collection
                Game.accounts[0].setCollection(new Collection());
            }
            if(input[0].equals("shop")){
                MainMenu.getMenu().enter(ShopMenu.getShopMenu());
            }
        }
        if(MenuHandler.currentMenu instanceof Profile){
            if (input[0].equals("change") || input[0].equals("delete") || input[0].equals("create")){
                String[] userAndPas = scanUserPass();
                username = userAndPas[0];
                password = userAndPas[1];
            }
            if(input[0].equals("create")){
                //kheili chert bod nazadim
            }
            if(input[0].equals("change")){
                Profile.getProfile().change(username, password);
            }
            if(input[0].equals("delete")){
                Profile.getProfile().delete(username, password);
            }
            if(input[0].equals("rename")){
                System.out.print("enter Username: ");
                String user = Game.accounts[0].getPlayer().getOutputStream().nextLine();
                Profile.getProfile().rename(user);
            }

            if(input[0].equals("show")){
                Profile.getProfile().show();
            }
        }
        if(MenuHandler.currentMenu instanceof ShopMenu){
            if(input[0].equals("show") && input[1].equals("shop")){
                Game.accounts[0].getShop().showShop();
            }
            if(input[0].equals("show") && input[1].equals("collection")){
                Game.accounts[0].getShop().showCollection();
            }
            if(input[0].equals("buy")){
                Game.accounts[0].getShop().buy(input[1]);
            }
            if(input[0].equals("money")){
                Game.accounts[0].getShop().showMoney();
            }
        }
        if(MenuHandler.currentMenu instanceof CollectionMenu){
            if(input[0].equals("show") && input[1].equals("hand")){
                Game.accounts[0].getCollection().showHand();
            }
            if(input[0].equals("show") && input[1].equals("collection")){
                Game.accounts[0].getCollection().showCollection();
            }
            if(input[0].length() >= 6 && input[0].substring(0, 6).equals("select")){
                try {
                    Game.accounts[0].getCollection().select(input[1]);
                }catch (invalidCardExeption e){}
            }
            if(input[0].length() >= 6 && input[0].substring(0, 6).equals("remove")){
                try {
                    Game.accounts[0].getCollection().remove(input[1]);
                }catch (invalidCardExeption e){}
            }
            if(input[0].equals("play")){
                switch (Game.accounts[0].getCollection().getPlayGameMode()){
                    case "day":
                        BattleMenu.getBattleMenu().setGameMode(new Day());
                        break;
//                    case "water":
//                        BattleMenu.getBattleMenu().setGameMode(new Water());
                }

                MenuHandler.currentMenu.enter(BattleMenu.getBattleMenu());
            }
        }
        if(MenuHandler.currentMenu instanceof Play){
            if(input[0].equals("day")){
                Game.accounts[0].getCollection().init(true, "day");
            }
        }

        if(input[0].equals("exit")){
            if(MenuHandler.currentMenu instanceof  LoginMenu)
                System.exit(0);
            else {
                MenuHandler.currentMenu.exit();
            }
        }
    }
}
/*

 */