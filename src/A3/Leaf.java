package A3;

/**
 * Created by Yvan on 8-6-2015.
 */
public class Leaf implements Cluster {

    Unit unit;

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
}
