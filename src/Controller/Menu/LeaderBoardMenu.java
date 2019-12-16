package Controller.Menu;

public class LeaderBoardMenu extends Menu{

    private static LeaderBoardMenu leaderBoardMenu;
    public LeaderBoardMenu(String name) {
        super(name);
    }
    public LeaderBoardMenu getLeaderBoardMenu(){
        if(leaderBoardMenu == null){
            leaderBoardMenu = new LeaderBoardMenu("LeaderBoardMenu");
        }
        return leaderBoardMenu;
    }


}
