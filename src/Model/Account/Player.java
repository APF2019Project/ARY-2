package Model.Account;

import Controller.Game;

import java.util.Scanner;

class ScannerWrapper{
    Scanner scanner;
}
public class Player {
    private String name;
    private int numOfKilledZombie;
    private int sun;
    private int coinForShop;
    private int coinInGame;
    protected static ScannerWrapper outputStream;

    public int getNumOfKilledZombie() {
        return numOfKilledZombie;
    }
    public void setNumOfKilledZombie(int numOfKilledZombie) {
        this.numOfKilledZombie = numOfKilledZombie;
    }
    public int getSun() {
        return sun;
    }
    public void setSun(int sun) {
        this.sun = sun;
    }
    public int getCoinForShop() {
        return coinForShop;
    }
    public void setCoinForShop(int coinForShop) {
        this.coinForShop = coinForShop;
    }
    public int getCoinInGame() {
        return coinInGame;
    }
    public void setCoinInGame(int coinInGame) {
        this.coinInGame = coinInGame;
    }

    public Scanner getOutputStream(){
        if(this.outputStream==null || this.outputStream.scanner==null){
            this.outputStream = new ScannerWrapper();
            this.outputStream.scanner = Game.scanner;
        }
        return outputStream.scanner;
    }
}
