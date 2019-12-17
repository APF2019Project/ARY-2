package Model.Account;

import Exception.InvalidAccountException;
import Exception.InvalidAccountException;

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
    public static Account getAccount(String ID) throws InvalidAccountException, InvalidAccountException {
        for (Account account : Account.getAccounts()) {
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

}
