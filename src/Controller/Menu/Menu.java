package Controller.Menu;

import Model.Account.Account;

import java.util.ArrayList;

public class Menu {
    protected Account account;
    Menu parentMenu;
    ArrayList<Menu> subMenus;


    public Menu(String name) {
    }

    public Menu enter(Menu subMenu){
        return subMenu;
    }

    public boolean init(){
        return true;
    }

    public void addSubMenu(Menu subMenu) {
        this.subMenus.add(subMenu);
    }

    public Menu getParentMenu() {
        return parentMenu;
    }




}
