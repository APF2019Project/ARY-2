package Controller.Menu;

import Model.Account.Account;
import View.MenuHandler;

import java.util.ArrayList;

public class Menu {
    protected Account account;
    Menu parentMenu;
    ArrayList<Menu> subMenus = new ArrayList<>();


    public Menu(String name) {
    }

    public Menu enter(Menu subMenu){
        if(subMenus.contains(subMenu))
            MenuHandler.currentMenu = subMenu;
        return subMenu;
    }

    public boolean init(){
        return true;
    }

    public void addSubMenu(Menu subMenu) {
        subMenu.parentMenu = this;
        this.subMenus.add(subMenu);
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void exit(){
        MenuHandler.currentMenu = parentMenu;
    }

}
