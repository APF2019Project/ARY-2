package Controller.Menu;

import Model.Account.Account;
import Model.Collection;
import Model.Primary;

import java.util.Collections;
import java.util.Comparator;

public class LeaderBoardMenu extends Menu{

    private static LeaderBoardMenu leaderBoardMenu;
    public LeaderBoardMenu(String name) {
        super(name);
    }
    public static LeaderBoardMenu getLeaderBoardMenu(){
        if(leaderBoardMenu == null){
            leaderBoardMenu = new LeaderBoardMenu("LeaderBoardMenu");
        }
        return leaderBoardMenu;
    }
    public void show(){
        Collections.sort(Primary.accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o2.getPlayer().getNumOfKilledZombie() - o1.getPlayer().getNumOfKilledZombie();
            }
        });
        System.out.println("Leader Board:");
        for(Account account : Primary.accounts){
            System.out.println(account.getUsername()+"\t"+account.getPlayer().getNumOfKilledZombie());
        }
    }

}
