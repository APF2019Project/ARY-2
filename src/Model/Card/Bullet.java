package Model.Card;

public class Bullet {
    private int row;
    private int column;
    private int damage;
    private Weapon weapon;
    int startRow;
    public Bullet(int row, int column, int damage, Weapon weapon){
        this.row = row;
        this.startRow = row;
        this.column = column;
        this.weapon = weapon;
        this.damage = damage;
    }
    public void bulletMove(){
        row += 1;
        //bayad fekr shavad
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    public int getStartRow() {
        return startRow;
    }
}
