package A6;

public class Leaf implements Cluster {

    private Unit unit;
    private int [] coordinates = new int[2];
    Leaf(Unit unit){
        this.unit = unit;
    }
    public int getDepth() {
        return 0;
    }
    public int getWidth() {
        return 1;
    }
    public UnitRow getUnits() {
        UnitRow unitRow = new UnitRow(getWidth());
        unitRow.addUnit(this.unit);
        return unitRow;
    }
    public boolean hasChildren() {
        return false;
    }
    public void setCoordinates(int x,int y){
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
    public int getCoordinates(int i){
        return coordinates[i];
    }
}
