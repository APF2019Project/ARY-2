package View;


import Controller.Menu.LoginMenu;
import Controller.Menu.MainMenu;
import Exception.*;

public class ConsoleOutput {
    public static void commandHandlerBegin(String[] input){
        String username = "";
        String password = "";
        //initial user and pass
        if( (input[0].equals("Create") && input[1].equals("account")) || input[0].equals("Login") ){
            System.out.print("enter Username: ");
            username = MenuHandler.getGameScanner().nextLine();
            System.out.print("enter Password: ");
            password = MenuHandler.getGameScanner().nextLine();
        }

        if(input[0].equals("Create") && input[1].equals("account")){
            if(MenuHandler.currentMenu instanceof LoginMenu){
                LoginMenu loginMenu = (LoginMenu) MenuHandler.currentMenu;
                try {
                    loginMenu.creatAccount(username, password);
                }catch (AccountAlreadyExistsException e){}
            }else{
                System.out.println("you are not in Login Menu");
            }
        }
        if(input[0].equals("Login")){
            if(MenuHandler.currentMenu instanceof LoginMenu){
                LoginMenu loginMenu = (LoginMenu) MenuHandler.currentMenu;
                try {
                    loginMenu.login(username, password);
                    loginMenu.enter(MainMenu.getMenu());
                    System.out.println("login successfully");
                }catch (InvalidAccountException e){
                    System.out.println("invalid username");
                }catch (WrongPasswordException e){
                    System.out.println("please enter correct password");
                }
            }else{
                System.out.println("you are not in Login Menu");
            }
        }
    }
}
