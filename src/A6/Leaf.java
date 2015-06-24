package A6;

import ui.Colour;

/**
 * Created by Yvan on 8-6-2015.
 */
public class Leaf implements Cluster {

    Unit unit;
    Colour colour;

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

    public Colour getColour(){
        return colour;
    }
    public void setColour(Colour colour){
        this.colour = colour;
    }


    public boolean hasChildren() {
        return false;
    }
}
