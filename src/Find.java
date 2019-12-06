import java.util.ArrayList;

public class Find {
    public static int findCard(String name, ArrayList<Card> cards){
        int index = -1;
        for (int i=0 ; i<cards.size() ; i++){
            if(cards.get(i).name.equals(name)){
                index = i;
            }
        }
        return index;
    }
}
