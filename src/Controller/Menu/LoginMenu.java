package Controller.Menu;

public class LoginMenu extends Menu{

    private static LoginMenu loginMenu;

    private LoginMenu(String name) {
        super(name);
    }

    public LoginMenu getLoginMenu(){
        if(loginMenu == null){
            loginMenu = new LoginMenu("LoginMenu");
        }
        return loginMenu;
    }
}
