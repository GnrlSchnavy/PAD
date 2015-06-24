package A6;

public class UnitRow {

    private Unit[] unitRow;
    private int length;

    public UnitRow(){
        length = 0;
        unitRow = new Unit[length];
    }

    UnitRow(int length){
        unitRow =new Unit[length];
    }
    public void addUnit(Unit Unit){
        unitRow[length] = Unit;
        length++;
    }
    public Unit getUnit(int i){
        return unitRow[i];
    }
    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }
    public void setUnit(int i, Unit unit){
        unitRow[i]=unit;
    }
}
