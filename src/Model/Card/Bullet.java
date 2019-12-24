package Model.Card;

public class Bullet {
    private int row;
    private int column;
    private Plant plant;

    public Bullet(int row, int column, Plant plant){
        this.row = row;
        this.column = column;
        this.plant = plant;
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
    public Plant getPlant() {
        return plant;
    }
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
