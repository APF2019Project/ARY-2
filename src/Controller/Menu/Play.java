package Controller.Menu;

public class Play extends Menu{
    private static Play menu;
    public Play(String name) {
        super(name);
    }

    public static Play getMenu(){
        if(Play.menu==null){
            Play.menu=new Play("Play");
        }
        return Play.menu;
    }
}
