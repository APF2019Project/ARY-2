package Controller.Menu;

public class Profile extends Menu {

    private static Profile profile;

    private Profile(String name) {
        super(name);
    }

    public Profile getProfile(){
        if(Profile.profile == null){
            Profile.profile = new Profile("Profile");
        }
        return profile;
    }

     public boolean login(String username, String password){
        return true;
     }
}
