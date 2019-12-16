package Model.Account;

import Controller.Exception.InvalidAccountException;
import Controller.Exception.InvalidAccoutException;
import Exception.InvalidAccountException;
import Exception.InvalidAccoutException;

public class Account {
    private static final Object INITIAL_MONEY =0 ;
    protected String name;
    protected String username;
    protected String password;
    protected int ID;
    protected int money;
    protected int wins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.money = (int) Account.INITIAL_MONEY;
        this.wins = 0;
    }
    public static Account getAccount(String ID) throws InvalidAccountException, InvalidAccoutException {
        for (Account account : Account.getAccounts()) {
            if (account.getID() == ID) return account;
        }
        throw new InvalidAccoutException();
    }


    public static boolean hasAccount(String username) {
        try {
            Account.getAccount(username);
            return true;
        } catch (InvalidAccountException | InvalidAccoutException e) {
            return false;
        }
    }

}