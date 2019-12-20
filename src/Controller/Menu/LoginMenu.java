package Controller.Menu;

import Model.Account.Account;
import Exception.AccountAlreadyExistsException;

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

    public Menu enter(Menu subMenu) {
        if(this.account==null){
            System.out.println("no account has been signed in yet");
            return this;
        }
        return super.enter(subMenu);
    }

    public void creatAccount(String username, String password) throws AccountAlreadyExistsException {
        if (Account.hasAccount(username))
            throw new AccountAlreadyExistsException();
        temporaryAccount = new Account(username, password);
    }

}
