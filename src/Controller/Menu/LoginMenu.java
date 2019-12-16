package Controller.Menu;

import Model.Account.Account;

public class LoginMenu extends Menu{

    private static LoginMenu loginMenu;
    private Account temporaryAccount;

    private LoginMenu(String name) {
        super(name);
    }

    public LoginMenu getLoginMenu(){
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

    public void creatAccount(String name, String username, String password) throws AccountAlreadyExistsException {
        if (Account.hasAccount(username))
            throw new AccountAlreadyExistsException();
        temporaryAccount = new Account(name, username, password);
    }

}
