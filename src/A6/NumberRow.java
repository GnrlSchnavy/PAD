package A6;

public class NumberRow {

    private double []values;
    private int length=0;

    public NumberRow(int fileFormat){
        setValues(new double[fileFormat]);
    }
    public void addValue(double value){
        values[length] = value;
        length++;
    }
    public double getValues(int i){
        return values[i];
    }
    public void setValues(double[] values){
        this.values = values;
    }
    public void setValue(int i,double value){values[i]=value;}
    public int getLength() {
        return length;
    }
    public int setLength(int length){
        return this.length=length;
    }
}
