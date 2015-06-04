package A2;

public class NumberRow {

    private double []values;
    private int length=0;

    public NumberRow(int fileFormat){
        setValues(new double[fileFormat]);
    }
    public double [] addValue(double value){
        getValues()[length] = value;
        length++;
        return getValues();
    }
    public double [] getValues(){
        return values;
    }
    public void setValues(double[] values){
        this.values = values;
    }
    public int getLength() {
        return length;
    }
    public int setLength(int length){
        return this.length=length;
    }
}
