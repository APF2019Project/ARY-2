package Controller.GameMode;

import Model.Card.Card;
import Model.Card.Plant;
import Model.Card.Zombie;
import Model.Map.Map;
import Exception.noCardSelected;
import Exception.NotPlantException;
import Model.Primary;
import Exception.invalidCardExeption;

import java.util.ArrayList;
import java.util.Random;

public class ZombieMode implements GameMode{
    private Random random = new Random();
    private ArrayList<Card> selectedZombie;
    private ArrayList<Plant>  plantsInMap;
    private ArrayList<Zombie> zombiesInWave;
    private boolean isWater;
    Map map;

    public Zombie zombieGenerate(String name) throws CloneNotSupportedException, invalidCardExeption{
        Zombie result;
        Zombie zombie = new Zombie();
        boolean check = false;
        for(Card card : selectedZombie){
            if(card.getName().equals(name)){
                zombie = (Zombie) card;
                check = true;
            }
        }
        result = (Zombie) zombie.clone();
        if(!check){
            System.out.println("invalid card name");
            throw new invalidCardExeption();
        }
        return result;
    }

    public void put (String name, int number)throws CloneNotSupportedException, invalidCardExeption{
        if(zombiesInWave.size() + number <= 12) {
            for (int i = 0; i < number; i++) {
                zombiesInWave.add(zombieGenerate(name));
            }
        }else {
            System.out.println("out of limit Zombie for this Wave");
        }
    }

    @Override
    public void waveGenerate(){
        while (zombiesInWave.size() > 0){
            int col = random.nextInt(6);
            Zombie tmp = zombiesInWave.get(random.nextInt(zombiesInWave.size()));
            if(map.board[col][Map.rowNumber-1].zombies.size() == 0) {
                map.board[col][Map.rowNumber - 1].zombies.add(tmp);
                zombiesInWave.remove(tmp);
            }else if(map.board[col][Map.rowNumber-1].zombies.size() == 1){
                map.board[col][Map.rowNumber].zombies.add(tmp);
                zombiesInWave.remove(tmp);
            }

        }
    }

    public void generatePlantInMapDay() throws CloneNotSupportedException{
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));

        for(int i=0 ; i<6 ; i++){
            plantsInMap.add(Primary.plantFindAndMake("Scaredy-shroom"));
        }

        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));
        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));

        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));
        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));

        plantsInMap.add(Primary.plantFindAndMake("Threepeater"));

        plantsInMap.add(Primary.plantFindAndMake("Gatling-Pea"));

        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));
    }
    public void generatePlantInMapWater() throws CloneNotSupportedException{
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));
        plantsInMap.add(Primary.plantFindAndMake("Explode-o-nut"));

        plantsInMap.add(Primary.plantFindAndMake("Tangle-Kelp"));

        for(int i=0 ; i<6 ; i++){
            plantsInMap.add(Primary.plantFindAndMake("Scaredy-shroom"));
        }

        plantsInMap.add(Primary.plantFindAndMake("Cattail"));

        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));
        plantsInMap.add(Primary.plantFindAndMake("Snow-pea"));

        plantsInMap.add(Primary.plantFindAndMake("Gatling-Pea"));

        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));
        plantsInMap.add(Primary.plantFindAndMake("Cabbage-pult"));


        plantsInMap.add(Primary.plantFindAndMake("Potato-Mine"));

        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
        plantsInMap.add(Primary.plantFindAndMake("Lily-Pad"));
    }

    @Override
    public Map generateMap() {
        int a = random.nextInt(2);
        try {
            if (a == 0) {
                isWater = false;
                generatePlantInMapDay();
                return Map.generateDay();
            } else {
                isWater = true;
                generatePlantInMapWater();
                return Map.generateWater();
            }
        }catch (CloneNotSupportedException e){}
        return Map.generateDay();
    }
    @Override
    public void showHand() throws NotPlantException {

    }
    @Override
    public void select(String a) throws NotPlantException {

    }
    @Override
    public void select(int a) {

    }
    @Override
    public void plant(int row, int column) throws noCardSelected, CloneNotSupportedException {

    }
    @Override
    public void showLawn() {

    }
    @Override
    public void endTurn() {

    }
    @Override
    public void handleWin() {

    }
}

