public class UnitRow {

	private Unit[] UnitRow;
	private int length = 0;
	
	UnitRow(int x){
		setUnitRow(new Unit[x]);
	}
	
	public Unit[] addUnit(Unit unit){
		getUnitRow()[getLength()]=unit;
		setLength(getLength() + 1);
		return getUnitRow();
		
	}
	public Unit[] getUnitRow(){
		return UnitRow;
	
	}
	
	public void setUnitRow(Unit [] unitRow){
		UnitRow = unitRow;
		
	}
	public int getLength(){
		return length;
		
	}
	public void setLength(int length){
		this.length = length;
	}
	
}
