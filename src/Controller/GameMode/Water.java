package Controller.GameMode;

import Controller.Game;
import Model.Account.Player;
import Model.Card.Bullet;
import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Collection;
import Model.Map.Map;
import Model.Map.Square;
import Model.Primary;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Exception.invalidCardExeption;

import java.util.ArrayList;
import java.util.Random;

public class Water implements GameMode {
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

    @Override
    public void showHand() throws NotPlantException {
        for (int i = 0; i < selectedCards.size(); i++) {
            Plant plant = (Plant)selectedCards.get(i);
            System.out.println(selectedCards.get(i).getName() + " "
                    +plant.getSun() + " " + plant.getTimeToReset());
        }
    }

    @Override
    public void select(String a) throws NotPlantException {
        int index = Collection.findCard(a, selectedCards);
        if(index != -1) {
            Plant plant = (Plant) selectedCards.get(index);
            if (player.getSun() >= plant.getSun() && plant.getPermissionTime() == 0) {
                selected = plant;
                System.out.println("select successfully");
            } else {
                System.out.println("you cant select this card");
            }
        }else {
            System.out.println("invalid card name");
            throw new NotPlantException();
        }
    }

    @Override
    public void select(int a) {

    }

    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {

    }

    @Override
    public void showLawn() {
        for (Plant plant1 : plantsInMap){
            System.out.println(plant1.getName()+"\thealth: "+plant1.getHealth()+
                    "\tcoordinate: ("+plant1.getColumn()+","+plant1.getRow()+")");
        }
//        for (Bullet bullet: bullets){
//            System.out.println(bullet.getColumn()+"\t"+bullet.getRow()+" col and row of bullet  "+bullet);
//        }
        for(Zombie zombie : zombies){
            System.out.println(zombie.getColumn()+"\t"+zombie.getRow()+"\t"+zombie.getHealth()+" zombie  "+zombie.getName()+" jun "+zombie.getHealth());
        }
    }

    @Override
    public void endTurn() {

    }

    @Override
    public void showSun() {
        System.out.println(Game.accounts[0].getPlayer().getSun());
    }

    @Override
    public void record() {

    }

    @Override
    public void list() {

    }

    @Override
    public void remove(int column, int row) {
        Square square=map.board[row][column];
        if(square.plant.size()!=0){
            square.plant.remove(0);
        }
        else {
            System.out.println("no plant is here");
        }
    }

    @Override
    public void handleWin() {

    }

    @Override
    public void waveGenerate() {

    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public void setStart(boolean b) {

    }

    @Override
    public void showLanes() {

    }

    @Override
    public void put(String name, int number) throws CloneNotSupportedException, invalidCardExeption {

    }


}
