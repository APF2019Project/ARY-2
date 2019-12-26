package View;

import Controller.Game;
import Controller.Menu.Menu;
import Model.Account.Player;
import Model.Card.Plant;

import java.util.Scanner;
import Exception.*;

public class ConsoleInput {
    public void play(){
        Scanner scan = MenuHandler.getGameScanner();
        String input;
        // yekseri chize digar
        while (scan.hasNext()){
            input = scan.nextLine().toLowerCase().trim();
            try {
                ConsoleOutput.commandHandlerBegin(input.split(" "));
            }catch (Exception e){}
        }
    }
}
