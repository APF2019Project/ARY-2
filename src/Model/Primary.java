package Model;

import Model.Account.Account;
import Model.Account.Shop;
import Model.Card.Plant;
import Model.Card.Weapon;
import Model.Card.Zombie;
import com.gilecode.yagson.YaGson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Primary {
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static ArrayList<Zombie> zombies= new ArrayList<Zombie>();
    public static ArrayList<Plant> plants = new ArrayList<>();
    public static void preprocess() throws IOException {
        plantJson();
        zombiesJson();
        Shop.calculateThePriceOfPlants();
        Shop.calculateThePriceOfZombies();
    }
    public static Plant plantFindAndMake(String name) throws CloneNotSupportedException{
        Plant result = new Plant();
        for(Plant plant : plants){
            if(plant.getName().equals(name)){
                result = (Plant) plant.clone();
                ArrayList<Weapon> weapons = new ArrayList<>();
                for(Weapon w : result.weapons){
                    Weapon tmp = (Weapon) w;
                    weapons.add((Weapon)tmp.clone());
                }
                result.weapons = weapons;
            }
        }
        return result;
    }
    public static void plantJson() throws IOException {
        YaGson gson = new YaGson();
        plants.add(new Plant("Peashooter",2,2,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(2,1,1,1)));
        plants.add(new Plant("Snow-pea",3,3,3,3,0,false,0,false,false ,false,false, true, false, false, false, false,false,new Weapon(3,2,1,1)));
        plants.add(new Plant("Cabbage-pult",2,3,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(2,1,1,2)));
        plants.add(new Plant("Repeater",4,4,3,3,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(3,1,2,1)));
        plants.add(new Plant("Threepeater",5,4,4,4,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(4,1,1,1)));
        plants.add(new Plant("Cactus",5,4,5,2,0,false,0,false,false ,false,false, false, false, false, false, false,true,new Weapon(2,1,1,1)));
        plants.add(new Plant("Gatling-Pea",3,4,5,5,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(5,1,4,1)));
        plants.add(new Plant("Scaredy-shroom",1,2,1,2,0,false,0,false,false ,false,false, false, true, false, false, false,false,new Weapon(2,1,1,1)));
        plants.add(new Plant("Kernel-pult",2,3,3,4,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(4,1,1,0)));
        //plants.add(new Plant("Split-pea",3,4,4,0,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(1,)));
        plants.add(new Plant("Explode-o-nut",3,5,4,0,0,false,0,false,false ,false,false, false, false, false, true, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Melon-pult",3,3,3,4,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(4,1,1,3)));
        plants.add(new Plant("Lily-Pad",1,1,0,2,0,true,0,false,false ,true,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Winter-Melon",3,5,4,4,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(4,2,1,3)));
        //plants.add(new Plant("Wall-nut",4,4,2,0,0,false,0,false,false ,false,false, false, false, false, true, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Tangle-Kelp",0,3,3,0,0,false,0,false,false ,false,false, false, false, true, false, false,false,new Weapon(0,0,0,0)));
        //plants.add(new Plant("Tall-nut",6,6,4,0,0,false,0,false,false ,false,false, false, false, false, true, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Cattail",3,5,5,0,0,false,0,false,true ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Potato-Mine",1,3,2,0,1,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
       // plants.add(new Plant("Cherry-Bomb",0,4,2,2,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
       // plants.add(new Plant("Magnet-shroom",2,4,4,3,0,false,0,false,false ,false,true, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        plants.add(new Plant("Sunflower",2,2,1,2,0,false,0,true,false ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        //plants.add(new Plant("Twin-Sunflower",2,5,3,2,0,false,0,true,false ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        //plants.add(new Plant("Jalapeno",0,5,4,0,0,false,0,false,false ,false,false, false, false, false, false, false,false,new Weapon(0,0,0,0)));
        FileWriter fileWriter = new FileWriter("Plant.json", false);
        for (Plant plant :
                plants) {
            gson.toJson(plant, fileWriter);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
    private static void zombiesJson() throws IOException {
        YaGson gson = new YaGson();
        zombies.add(new Zombie("Zombie",2,2,0,false,1));
        zombies.add(new Zombie("Football-Zombie",4,3,0,false,1));
        zombies.add(new Zombie("Buckethead-Zombie",3, 2,0,false,1));
        zombies.add(new Zombie("Conehead-Zombie",3,2,0,false,1));
        zombies.add(new Zombie("Zomboni",3,2,0,false,1));
        zombies.add(new Zombie("Catapult-Zombie",3,2,0,false,1));
        zombies.add(new Zombie("Bungee-Zombie",3,0 ,0,false,1));
        zombies.add(new Zombie("balloon-Zombie",3,2,0,false,1));
        zombies.add(new Zombie("Newspaper-Zombie",2,2,2,false,1));
        zombies.add(new Zombie("Target-Zombie",3,2,3,false,1));
        zombies.add(new Zombie("Screen-door-Zombie",2,2,4,false,1));
        zombies.add(new Zombie("Giga-gargantuar",6,1,0,false,1000));
        zombies.add(new Zombie("Pogo-Zombie",2,2,0,false,1));
        zombies.add(new Zombie("Snorkel-Zombie",2,2,0,true,1));
        zombies.add(new Zombie("Dolphine-Rider-Zombie",2,2,2,true,1));
        FileWriter fileWriter = new FileWriter("Zombie.json", false);
        for (Zombie zombie :
                zombies) {
            gson.toJson(zombie, fileWriter);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
}
