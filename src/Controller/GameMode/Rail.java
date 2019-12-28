package Controller.GameMode;
import Model.Account.Player;
import Model.Card.Bullet;
import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Map.Map;

import java.util.ArrayList;
import java.util.Random;
import Exception.NotPlantException;
import Exception.noCardSelected;
import Model.Map.Square;
import Model.Primary;

import static Model.Primary.zombies;

public class Rail implements GameMode {
    private Player player;
    private Map map;
    private Plant selected;
    private int turn;
    private int turnToGenerateRandomPlant;
    private int numberOfKilledZombies;
    private ArrayList<Card> selectedCards;
    ArrayList<Zombie> enemies = new ArrayList<>(); // badan bayad dar canstructor load shavad
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Plant> plantsInMap = new ArrayList<>();
    Random random=new Random();

    public void remove(int column,int row){
        Square square=map.board[row][column];
        if(square.plant.size()!=0){
            square.plant.remove(0);
        }
        else {
            System.out.println("no plant is here");
        }
    }

    public void generateRandomPlant(){
        try {
            Plant plant = (Plant) Primary.plants.get(random.nextInt(Primary.plants.size())).clone();
            selectedCards.add(plant);
        }
        catch (CloneNotSupportedException e){

        }
    }

    public void list(){
        for (int i = 0; i < selectedCards.size(); i++) {
            System.out.println(selectedCards.get(i).getName());
        }
    }
    @Override
    public Map generateMap() {
        return null;
    }

    public void record(){
        System.out.println("number of killed zombies: "+numberOfKilledZombies);
    }

    @Override
    public void showHand() throws NotPlantException {

    }

    @Override
    public void select(String a) throws NotPlantException {

    }

    @Override
    public void select(int a) {
        if(a>selectedCards.size()){
            System.out.println("invalid number");
        }
        else{
            Plant plant= (Plant) selectedCards.get(a);
            selected=plant;
            System.out.println("selected successfully");
        }
    }

    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {
        if(selected!=null){

        }
        else{
            System.out.println("no card selected");
            throw new noCardSelected();
        }

    }

    @Override
    public void showLawn() {
        for (Plant plant1 : plantsInMap){
            System.out.println(plant1.getName()+"\thealth: "+plant1.getHealth()+
                    "\tcoordinate: ("+plant1.getColumn()+","+plant1.getRow()+")");
        }
        for (Bullet bullet: bullets){
            System.out.println(bullet.getColumn()+"\t"+bullet.getRow()+" row and col of bullet");
        }
        for(Zombie zombie : zombies){
            System.out.println(zombie.getColumn()+"\t"+zombie.getRow()+"\t"+zombie.getHealth()+" zombie  "+zombie.getName()+" jun "+zombie.getHealth());
        }
    }

    @Override
    public void endTurn() {
        turn++;
        if(turn%4==0){
            turnToGenerateRandomPlant=random.nextInt(5);
        }
        if(turn%4==turnToGenerateRandomPlant && selectedCards.size()<10){
            generateRandomPlant();
        }

    }

    @Override
    public void handleWin() {

    }
}
