package View;

import Controller.Game;
import Controller.GameMode.Day;
import Controller.GameMode.PVP;
import Controller.GameMode.Rail;
import Controller.GameMode.Water;
import Controller.GameMode.ZombieMode;
import Controller.Menu.LoginMenu;
import Controller.Menu.*;
import Exception.*;
import Model.Account.Account;
import Model.Card.Plant;
import Model.Collection;
import Model.Primary;

import javax.management.BadAttributeValueExpException;

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
    public static void commandHandlerBegin(String[] input) throws invalidCardExeption, NotPlantException, noCardSelected, CloneNotSupportedException {
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
                        loginMenu.login(username, password, 0);
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
                try {
                    Profile.getProfile().creatAccount(username, password);
                } catch (AccountAlreadyExistsException e) {
                    e.printStackTrace();
                }
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
                Game.accounts[Game.currentPlayer].getCollection().showHand();
            }
            if(input[0].equals("show") && input[1].equals("collection")){
                Game.accounts[Game.currentPlayer].getCollection().showCollection();
            }
            if(input[0].length() >= 6 && input[0].substring(0, 6).equals("select")){
                try {
                    Game.accounts[Game.currentPlayer].getCollection().select(input[1]);
                }catch (invalidCardExeption e){}
            }
            if(input[0].length() >= 6 && input[0].substring(0, 6).equals("remove")){
                try {
                    Game.accounts[Game.currentPlayer].getCollection().remove(input[1]);
                }catch (invalidCardExeption e){}
            }
            if(input[0].equals("play")){
                switch (Game.accounts[Game.currentPlayer].getCollection().getPlayGameMode()){
                    case "day":
                        BattleMenu.getBattleMenu().setGameMode(new Day());
                        break;
                    case "water":
                       BattleMenu.getBattleMenu().setGameMode(new Water());
                       break;
                    case "zombie":
                        BattleMenu.getBattleMenu().setGameMode(new ZombieMode());
                        break;
                    case "pvp1":
                        Game.currentPlayer = 1;
                        Game.accounts[0].getCollection().setPlayGameMode("pvp2");
                        break;
                    case "pvp2":
                        Game.currentPlayer = 0;
                        Game.accounts[0].getCollection().setPlayGameMode("pvp");
                        BattleMenu.getBattleMenu().setGameMode(new PVP());
                }
                if(!Game.accounts[0].getCollection().getPlayGameMode().equals("pvp2")) {
                    MenuHandler.currentMenu.enter(BattleMenu.getBattleMenu());
                }
            }
        }
        if(MenuHandler.currentMenu instanceof Play){
            System.out.println("you enter Play Menu");
            if(input[0].equals("day")){
                Game.accounts[0].getCollection().init(true, "day", 7);
                MenuHandler.currentMenu.enter(CollectionMenu.getCollectionMenu());
            }
            if(input[0].equals("water")){
                Game.accounts[0].getCollection().init(true,"water",7);
                MenuHandler.currentMenu.enter(CollectionMenu.getCollectionMenu());
            }
            if(input[0].equals("zombie")){
                Game.accounts[0].getCollection().init(false, "zombie", 7);
                MenuHandler.currentMenu.enter(CollectionMenu.getCollectionMenu());
            }
            if(input[0].equals("rail")){
                Game.accounts[0].getCollection().init(false,"rail",10);
                BattleMenu.getBattleMenu().setGameMode(new Rail());
                MenuHandler.currentMenu.enter(BattleMenu.getBattleMenu());
            }
            if(input[0].equals("pvp")){
                System.out.println("please enter second account");
                String[] userAndPas = scanUserPass();
                username = userAndPas[0];
                password = userAndPas[1];
                try {
                    LoginMenu.getLoginMenu().login(username, password, 1);
                    System.out.println("login successfully");
                } catch (InvalidAccountException e) {
                    System.out.println("invalid username");
                } catch (WrongPasswordException e) {
                    System.out.println("please enter correct password");
                }
                Game.accounts[0].getCollection().init(true, "pvp1", 7);
                Game.accounts[1].getCollection().init(false, "pvp2", 7);
                MenuHandler.currentMenu.enter(CollectionMenu.getCollectionMenu());
            }
        }

        if(MenuHandler.currentMenu instanceof BattleMenu){
            if(Game.accounts[0].getCollection().getPlayGameMode().equals("day")) {
                if (input[0].equals("show") && input[1].equals("hand")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().showHand();
                    } catch (NotPlantException e) {
                    }
                }
                if (input[0].equals("select")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().select(input[1]);
                    } catch (NotPlantException e) {
                    }
                }
                if (input[0].equals("plant")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().plant(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    } catch (noCardSelected e) {
                    } catch (CloneNotSupportedException e) {
                    }
                }
                if(input[0].equals("remove")){
                    BattleMenu.getBattleMenu().getGameMode().remove(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                }
                if (input[0].equals("end")) {
                    BattleMenu.getBattleMenu().getGameMode().endTurn();
                }
                if (input[0].equals("show") && input[1].equals("lawn")) {
                    BattleMenu.getBattleMenu().getGameMode().showLawn();
                }
                if(input[0].equals("sun")){
                    BattleMenu.getBattleMenu().getGameMode().showSun();
                }
            }
            if(Game.accounts[0].getCollection().getPlayGameMode().equals("water")){
                if (input[0].equals("show") && input[1].equals("hand")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().showHand();
                    } catch (NotPlantException e) {
                    }
                }
                if (input[0].equals("select")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().select(input[1]);
                    } catch (NotPlantException e) {
                    }
                }
                if(input[0].equals("remove")){
                    BattleMenu.getBattleMenu().getGameMode().remove(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                }
                if (input[0].equals("plant")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().plant(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    } catch (noCardSelected e) {
                    } catch (CloneNotSupportedException e) {
                    }
                }
                if (input[0].equals("end")) {
                    BattleMenu.getBattleMenu().getGameMode().endTurn();
                }
                if (input[0].equals("show") && input[1].equals("lawn")) {
                    BattleMenu.getBattleMenu().getGameMode().showLawn();
                }
                if(input[0].equals("sun")){
                    BattleMenu.getBattleMenu().getGameMode().showSun();
                }

            }
            if(Game.accounts[0].getCollection().getPlayGameMode().equals("zombie")) {
                if (input[0].equals("show") && input[1].equals("hand")) {
                    try {
                        BattleMenu.getBattleMenu().getGameMode().showHand();
                    } catch (NotPlantException e) {
                    }
                }
                if (input[0].equals("end")) {
                    if(BattleMenu.getBattleMenu().getGameMode().isStart())
                        BattleMenu.getBattleMenu().getGameMode().endTurn();
                }
                if(input[0].equals("show") && input[1].equals("lanes")){
                    BattleMenu.getBattleMenu().getGameMode().showLanes();
                }
                if(input[0].equals("put")){
                    BattleMenu.getBattleMenu().getGameMode().put(input[1], Integer.parseInt(input[2]));
                }
                if (input[0].equals("show") && input[1].equals("lawn")) {
                    BattleMenu.getBattleMenu().getGameMode().showLawn();
                }
                if(input[0].equals("start")){
                    BattleMenu.getBattleMenu().getGameMode().setStart(true);
                    BattleMenu.getBattleMenu().getGameMode().endTurn();
                }
            }
            if(Game.accounts[0].getCollection().getPlayGameMode().equals("rail")){
                System.out.println("in rail mode");
                if(input[0].equals("record")){
                    BattleMenu.getBattleMenu().getGameMode().record();
                }
                if(input[0].equals("list")){
                    BattleMenu.getBattleMenu().getGameMode().list();
                }
                if(input[0].equals("show") && input[1].equals("lawn")){
                    BattleMenu.getBattleMenu().getGameMode().showLawn();
                }
                if(input[0].equals("select")){
                    BattleMenu.getBattleMenu().getGameMode().select(Integer.parseInt(input[1]));
                }
                if(input[0].equals("end") && input[1].equals("turn")){
                    BattleMenu.getBattleMenu().getGameMode().endTurn();
                }
                if(input[0].equals("remove")){
                    BattleMenu.getBattleMenu().getGameMode().remove(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                }
                if(input[0].equals("plant")){
                //    System.out.println("in plant");
                    BattleMenu.getBattleMenu().getGameMode().plant(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
                }

            }
            if(Game.accounts[0].getCollection().getPlayGameMode().equals("pvp")){
                System.out.println("PVP");
                if(Game.currentPlayer == 1){
                    if (input[0].equals("show") && input[1].equals("hand")) {
                        try {
                            BattleMenu.getBattleMenu().getGameMode().showHand();
                        } catch (NotPlantException e) {
                        }
                    }
                    if (input[0].equals("end")) {
                        if(BattleMenu.getBattleMenu().getGameMode().isStart())
                            BattleMenu.getBattleMenu().getGameMode().endTurn();
                    }
                    if(input[0].equals("show") && input[1].equals("lanes")){
                        BattleMenu.getBattleMenu().getGameMode().showLanes();
                    }
                    if(input[0].equals("put")){
                        BattleMenu.getBattleMenu().getGameMode().put(input[1], Integer.parseInt(input[2]));
                    }
                    if (input[0].equals("show") && input[1].equals("lawn")) {
                        BattleMenu.getBattleMenu().getGameMode().showLawn();
                    }
                    if(input[0].equals("start")){
                        BattleMenu.getBattleMenu().getGameMode().setStart(true);
                        BattleMenu.getBattleMenu().getGameMode().endTurn();
                    }
                }
                else if(Game.currentPlayer == 0){
                    if (input[0].equals("show") && input[1].equals("hand")) {
                        try {
                            BattleMenu.getBattleMenu().getGameMode().showHand();
                        } catch (NotPlantException e) {
                        }
                    }
                    if (input[0].equals("select")) {
                        try {
                            System.out.println(input[1]);
                            BattleMenu.getBattleMenu().getGameMode().select(input[1]);
                        } catch (NotPlantException e) {
                        }
                    }
                    if (input[0].equals("plant")) {
                        try {
                            BattleMenu.getBattleMenu().getGameMode().plant(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                        } catch (noCardSelected e) {
                        } catch (CloneNotSupportedException e) {
                        }
                    }
                    if (input[0].equals("ready")) {
                        Game.currentPlayer = 1;
                    }
                    if (input[0].equals("show") && input[1].equals("lawn")) {
                        BattleMenu.getBattleMenu().getGameMode().showLawn();
                    }
                }
            }
        }
        if(input[0].equals("exit")){
            if(MenuHandler.currentMenu instanceof  LoginMenu)
                System.exit(0);
            else {
                MenuHandler.currentMenu.exit();
                System.out.println(MenuHandler.currentMenu);
            }
        }
    }
}
/*
create account
ali
123
create account
amir
456
login
ali
123
shop
buy repeater
buy Peashooter
buy Snow-pea
buy Cabbage-pult
buy Cactus
buy Gatling-Pea
buy Scaredy-shroom
buy Kernel-pult
buy Zombie
buy Football-Zombie
buy Buckethead-Zombie
buy Conehead-Zombie
buy Zomboni
buy Catapult-Zombie
buy Bungee-Zombie
buy balloon-Zombie
exit
play
pvp
amir
456
select repeater
select Peashooter
select Snow-pea
select Cabbage-pult
select Cactus
select Gatling-Pea
select Scaredy-shroom
select Kernel-pult
play
select Zombie
select Football-Zombie
select Buckethead-Zombie
select Conehead-Zombie
select Zomboni
select Catapult-Zombie
select Bungee-Zombie
select balloon-Zombie
play
 */


/*
create account
ali
123
login
ali
123
shop
buy Zombie
buy Football-Zombie
buy Buckethead-Zombie
buy Conehead-Zombie
buy Zomboni
buy Catapult-Zombie
buy Bungee-Zombie
buy balloon-Zombie
exit
play
zombie
select Zombie
select Football-Zombie
select Buckethead-Zombie
select Conehead-Zombie
select Zomboni
select Catapult-Zombie
select Bungee-Zombie
select balloon-Zombie

play

put zombie 2
put Buckethead-Zombie 2
put Conehead-Zombie 2
put Zomboni 2
put Zomboni 2
put Conehead-Zombie 2
put zombie 1
start
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn



 */


/*
create account
ali
123
login
ali
123
shop
buy repeater
buy Peashooter
buy Snow-pea
buy Cabbage-pult
buy Cactus
buy Gatling-Pea
buy Scaredy-shroom
buy Kernel-pult
buy lily-pad
exit
play
water
select lily-pad
select Peashooter
select Snow-pea
select Cabbage-pult
select Cactus
select Gatling-Pea
select Scaredy-shroom
select Kernel-pult
play
select lily-pad
plant 2 2
plant 3 3
plant 0 4
plant 1 1
plant 4 5
plant 4 0
end turn
end turn
end turn
end turn
end turn
end turn
end turn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
end turn
show lawn
 */
/*
create account
yasi
123
login
yasi
123
play
rail
end turn
end turn
select 1
plant
iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
 */