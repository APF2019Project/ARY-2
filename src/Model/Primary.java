package Model;

import Model.Account.Account;
import Model.Card.Plant;
import Model.Card.Zombie;

import java.util.ArrayList;

public class Primary {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Zombie> zombies= new ArrayList<Zombie>();
    public static ArrayList<Plant> plants = new ArrayList<>();
    private static void plantJson(){
        plants.add(new Plant("Peashooter",2,2,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Snow pea",3,3,3,3,0,false,0,false,false ,false,false, true, false, false, false, false,false));
        plants.add(new Plant("Cabbage-pult",2,3,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Repeater",4,4,3,3,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Threepeater",5,4,4,4,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Cactus",5,4,5,2,0,false,0,false,false ,false,false, false, false, false, false, false,true));
        plants.add(new Plant("Gatling Pea",3,4,5,5,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Scaredy-shroom",1,2,1,2,0,false,0,false,false ,false,false, false, true, false, false, false,false));
        plants.add(new Plant("Kernel-pult",2,3,3,4,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Split pea",3,4,4,0,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Explode-o-nut",3,5,4,0,0,false,0,false,false ,false,false, false, false, false, true, false,false));
        plants.add(new Plant("Melon-pult",3,3,3,4,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Lily Pad",1,1,0,2,0,true,0,false,false ,true,false, false, false, false, false, false,false));
        plants.add(new Plant("Winter Melon",3,5,4,4,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Wall-nut",4,4,2,0,0,false,0,false,false ,false,false, false, false, false, true, false,false));
        plants.add(new Plant("Tangle Kelp",0,3,3,0,0,false,0,false,false ,false,false, false, false, true, false, false,false));
        plants.add(new Plant("Tall-nut",6,6,4,0,0,false,0,false,false ,false,false, false, false, false, true, false,false));
        plants.add(new Plant("Cattail",3,5,5,0,0,false,0,false,true ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Potato Mine",1,3,2,0,1,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Cherry Bomb",0,4,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Magnet-shroom",2,4,4,3,0,false,0,false,false ,false,true, false, false, false, false, false,false));
        plants.add(new Plant("Sunflower",2,2,1,2,0,false,0,true,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Twin Sunflower",2,5,3,2,0,false,0,true,false ,false,false, false, false, false, false, false,false));
        plants.add(new Plant("Jalapeno",0,5,4,0,0,false,0,false,false ,false,false, false, false, false, false, false,false));



    }
}
