package Controller.Menu;

public class ShopMenu extends Menu{
    private static ShopMenu shopMenu;

    private ShopMenu(String name) {
        super(name);
    }

    public static ShopMenu getShopMenu() {
        if(shopMenu==null){
            shopMenu=new ShopMenu("ShopMenu");

        }
        return shopMenu;
    }
}
