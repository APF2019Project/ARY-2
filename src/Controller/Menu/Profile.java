package Controller.Menu;

public class Profile extends Menu {

    private static Profile profile;

    private Profile(String name) {
        super(name);
    }

    public static Profile getProfile(){
        if(Profile.profile == null){
            Profile.profile = new Profile("Profile");
        }
        return profile;
    }

     public boolean login(String username, String password){
        return true;
     }
}
