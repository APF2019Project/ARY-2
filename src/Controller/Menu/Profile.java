package Controller.Menu;

import Controller.Game;
import Model.Account.Account;
import Exception.AccountAlreadyExistsException;
import Exception.InvalidAccountException;

public class Profile extends Menu {

    private static Profile profile;

    private Profile(String name) {
        super(name);
    }

    public static Profile getProfile(){
        if(Profile.profile == null){
            Profile.profile = new Profile("Profile");
        }
        return profile;
    }

     public boolean login(String username, String password){
        return true;
     }

    public void creatAccount(String username, String password) throws AccountAlreadyExistsException {
        if (Account.hasAccount(username)) {
            System.out.println("account already exist");
            throw new AccountAlreadyExistsException();
        }
        Account temporaryAccount = new Account(username, password);
        Account.addNewAccount(temporaryAccount);
    }


    public void change(String username, String password){
        if(Game.accounts[0].getUsername().equals(username))
            Game.accounts[0].setPassword(password);
        else{
            System.out.println("incorrect username");
        }
     }

     public void delete(String username, String password){
         if(Game.accounts[0].getUsername().equals(username)){
             if(Game.accounts[0].getPassword().equals(password)){
                 for (int i=0 ; i<Account.getAccounts().size(); i++){
                     if(Account.getAccounts().get(i).getUsername().equals(username)) {
                         Account.getAccounts().remove(i);
                         break;
                     }
                 }
             }
         }
         else{
             System.out.println("incorrect username");
         }
     }

     public void rename(String username){
        Game.accounts[0].setUsername(username);
     }

     public void show(){
         System.out.println(Game.accounts[0].getUsername());
     }
}
