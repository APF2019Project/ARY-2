package Model.Account;

import Exception.InvalidAccountException;
import Model.Collection;
import Model.Primary;
import com.gilecode.yagson.YaGson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Account {
    private static final Object INITIAL_MONEY =0 ;
    protected String username;
    protected String password;
    protected int ID;
    protected int money;
    protected int wins;
    private Collection collection;
    private Shop shop;
    private static Account defaultAccount = new Account("user","pass"); // badan bayad sakhte shavad
    protected Player player;

    public static void addNewAccount(Account account){
        if(account == null) return;
        if(Account.hasAccount(account)) return;
        Account.getAccounts().add(account);
        YaGson gson = new YaGson();
        try {
            FileWriter fileWriter = new FileWriter("Account.json", true);
            gson.toJson(account, fileWriter);
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
        }
    }

    public static Account getDefaultAccount() {
        return defaultAccount;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.money = (int) Account.INITIAL_MONEY;
        this.wins = 0;
        this.collection = new Collection();
    }

    public void save(){
        YaGson gson = new YaGson();
        File file = new File("Account.json");
        file.delete();
        for (Account account: getAccounts()) {
            try{
                FileWriter fileWriter = new FileWriter("Account.json", true);
                account.player=null;
                gson.toJson(account, fileWriter);
                fileWriter.write("\n");
                fileWriter.close();
            } catch (IOException ignored) {}
        }
    }

    public static Account getAccount(int ID) throws InvalidAccountException, InvalidAccountException {
        for (Account account : Account.getAccounts()) {
            int i = 12;
            if (account.getID() == ID) return account;
        }
        throw new InvalidAccountException();
    }

    public static Account getAccount(String username) throws InvalidAccountException {
        for (Account account : Account.getAccounts()) {
            if (account.getUsername().equals(username)) return account;
        }
        throw new InvalidAccountException();
    }
    public static ArrayList<Account> getAccounts() {
        return Primary.accounts;
    }


    public static boolean hasAccount(String username) {
        try {
            Account.getAccount(username);
            return true;
        } catch (InvalidAccountException e) {
            return false;
        }
    }
    public static boolean hasAccount(Account account) {
        try {
            Account.getAccount(account.getUsername());
            return true;
        } catch (InvalidAccountException e) {
            return false;
        }
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public Player getPlayer() {
        if(this.player == null){
            player = new Player();
        }
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Collection getCollection() {
        return collection;
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
    public Shop getShop() {
        if(shop == null)
            shop = new Shop();
        return shop;
    }
    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
