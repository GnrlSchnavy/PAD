package A4;

public class UnitRow {

    private Unit[] UnitRow;
    private int length = 0;

    UnitRow(int length){
        UnitRow=new Unit[length];
    }
    public void addUnit(Unit Unit){
        UnitRow[length] = Unit;
        length++;
    }
    public Unit getUnit(int i){
        return UnitRow[i];
    }
    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }

    public void setUnit(int i, Unit unit){
        UnitRow[i]=unit;
    }
}
