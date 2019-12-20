package View;


import Controller.Menu.LoginMenu;
import Exception.*;

public class ConsoleOutput {
    public static void commandHandlerBegin(String[] input){
        if(input[0].equals("Create") && input[1].equals("account")){
            System.out.print("enter Username: ");
            String username = MenuHandler.getGameScanner().nextLine();
            System.out.print("enter Password: ");
            String password = MenuHandler.getGameScanner().nextLine();
            if(MenuHandler.currentMenu instanceof LoginMenu){
                LoginMenu loginMenu = (LoginMenu) MenuHandler.currentMenu;
                try {
                    loginMenu.creatAccount(username, password);
                }catch (AccountAlreadyExistsException e){}
            }else{
                System.out.println("you are not in Login Menu");
            }
        }
    }
}
