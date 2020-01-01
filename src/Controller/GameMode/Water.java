package Controller.GameMode;

import Controller.Game;
import Model.Account.Player;
import Model.Card.Bullet;
import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Map.Map;
import Model.Primary;

import java.util.ArrayList;
import java.util.Random;

public class Water {
    private Player player;
    private Map map;
    private int turn;
    private Plant selected;
    private ArrayList<Card> selectedCards;
    ArrayList<Zombie> enemies = new ArrayList<>(); // badan bayad dar canstructor load shavad
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plantsInMap = new ArrayList<>();
    private int turnsAfterLastWave;
    private int zombieAliveNumber;
    Random random = new Random();
    public Water(){
        turn=0;
        map = generateMap();
        this.selectedCards = Game.accounts[0].getCollection().selectedCards;
        turnsAfterLastWave = 4;
        zombieAliveNumber = 0;
        player = Game.accounts[0].getPlayer();
        enemies.addAll(Primary.zombies); // in ghalat ast va badan bayad dorost shavad
    }
    public Map generateMap() {
        return Map.generateWater();
    }
}
