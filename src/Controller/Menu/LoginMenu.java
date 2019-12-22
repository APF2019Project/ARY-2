package Controller.Menu;

import Controller.Game;
import Model.Account.Account;
import Exception.AccountAlreadyExistsException;
import Exception.InvalidAccountException;
import Exception.WrongPasswordException;

public class LoginMenu extends Menu{

    private static LoginMenu loginMenu;
    private Account temporaryAccount;

    private LoginMenu(String name) {
        super(name);
    }

    public static LoginMenu getLoginMenu(){
        if(loginMenu == null){
            loginMenu = new LoginMenu("LoginMenu");
        }
        return loginMenu;
    }

    @Override
    public Menu enter(Menu subMenu) {
        if(this.account==null && subMenu instanceof MainMenu){
            System.out.println("no account has been signed in yet");
            return this;
        }
        return super.enter(subMenu);
    }


    public void creatAccount(String username, String password) throws AccountAlreadyExistsException {
        if (Account.hasAccount(username)) {
            System.out.println("account already exist");
            throw new AccountAlreadyExistsException();
        }
        temporaryAccount = new Account(username, password);
        Account.addNewAccount(temporaryAccount);
    }

    public void login(String username, String password) throws InvalidAccountException, WrongPasswordException {
        Account account = Account.getAccount(username);
        if(account.getPassword().equals(password)){
            Game.accounts[0] = account;
            Game.hasLoggedIn = true;
            this.account = account;
        }else {
            throw new WrongPasswordException();
        }
    }
}
