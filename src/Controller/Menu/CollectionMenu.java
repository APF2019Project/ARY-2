package Controller.Menu;

public class CollectionMenu extends Menu{
    private static CollectionMenu collectionMenu;

    private CollectionMenu(String name) {
        super(name);
    }

    public static CollectionMenu getCollectionMenu(){
        if(collectionMenu==null){
            collectionMenu=new CollectionMenu("CollectionMenu");
        }
        return collectionMenu;
    }



}
