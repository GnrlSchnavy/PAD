package A2;

public class Unit {


    private String unitName;
    private NumberRow unitValues;
    private Unit unit;

    public Unit(String unitName, NumberRow unitValues){
        this.setUnitName(unitName);
        this.setUnitValues(unitValues);
    }
    public String getUnitName(){
        return unitName;
    }
    public void setUnitName(String unitName){
        this.unitName = unitName;

    }
    public NumberRow getUnitValues(){
        return unitValues;

    }
    public void setUnitValues(NumberRow unitValues){
        this.unitValues=unitValues;
    }
    public Unit getUnit(Unit unit){
        return this.unit;
    }


}
