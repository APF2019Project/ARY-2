package View;

import Controller.Game;
import Controller.Menu.Menu;
import Model.Account.Player;
import Model.Card.Plant;

import java.util.Scanner;

public class ConsoleInput {
    public void play(){
        Scanner scan = MenuHandler.getGameScanner();
        String input;
        // yekseri chize digar
        while (scan.hasNext()){
            input = scan.nextLine().trim();
            ConsoleOutput.commandHandlerBegin(input.split(" "));
        // paas bede be consoleOut
        }
    }
}
