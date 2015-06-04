import A1.NumberRow;
import A1.Unit;
import A1.UnitRow;

public class Dataset {

    private int numberOfClusters;
    private int numberOfVariableRows;
    private int numberOfVariables;
    private UnitRow unitRow;
    private String[]names = new String[getNumberOfVariables()+1];

    Dataset(){

    }
    public Dataset normalize(Dataset toNormalizeDataSet, NumberRow maximumValues, NumberRow minimumValues){
        for (int i = 0;i<toNormalizeDataSet.getUnitRow().getLength();i++){
            for (int j = 0;j<toNormalizeDataSet.getNumberOfVariables();j++){
                toNormalizeDataSet.getUnitRow().getUnitRow()[i].getUnitValues().getValues()[j]=(toNormalizeDataSet.getUnitRow().getUnitRow()[i].getUnitValues().getValues()[j] - minimumValues.getValues()[j])/(maximumValues.getValues()[j]-minimumValues.getValues()[j]);
            }
        }
        return toNormalizeDataSet;
    }
    public Dataset doPreselection(Dataset normalizedDataSet){
        double [] averageValueRow = addAllNumbers(normalizedDataSet);
        NumberRow standardDeviationRow = calculateStandardDeviationRow(normalizedDataSet,averageValueRow);
        getHighestStandardDeviation(standardDeviationRow,normalizedDataSet);
        return normalizedDataSet;
    }
    private void getHighestStandardDeviation(NumberRow standardDeviationRow,Dataset normalizedDataSet){
        System.out.print("is used");
        for(int i = 0;i<50;i++){
            int highestValueSpot=(int) standardDeviationRow.getValues()[0];
            for(int j = 0;j<standardDeviationRow.getLength();j++){
                if(standardDeviationRow.getValues()[j]>highestValueSpot){
                    highestValueSpot=j;

                }
            }
            switchValues(i,highestValueSpot, standardDeviationRow, normalizedDataSet);
        }
    }
    private void switchValues(int i, int highestValueSpot, NumberRow standardDeviationRow, Dataset normalizedDataSet) {
        Unit temp = normalizedDataSet.getUnitRow().getUnitRow()[i-1];
        normalizedDataSet.unitRow.getUnitRow()[i] = normalizedDataSet.unitRow.getUnitRow()[highestValueSpot];
        normalizedDataSet.unitRow.getUnitRow()[highestValueSpot] = temp;
    }
    private NumberRow calculateStandardDeviationRow(Dataset normalizedDataSet, double [] averageValueRow){
        int counter = 0;
        NumberRow standardDeviationRow = new NumberRow(normalizedDataSet.getNumberOfVariables());
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum+=Math.pow(normalizedDataSet.getUnitRow().getUnitRow()[j].getUnitValues().getValues()[i]-averageValueRow[i],2);
                counter++;
            }
            standardDeviationRow.getValues()[i]=Math.sqrt((sum/normalizedDataSet.numberOfVariableRows*normalizedDataSet.numberOfVariables)-1);
            System.out.print(counter/20+"  " + Math.sqrt(sum/((normalizedDataSet.numberOfVariableRows)-1))+"\n");
        }
        return standardDeviationRow;
    }
    private double [] addAllNumbers(Dataset normalizedDataSet){
        double [] sumRow = new double [normalizedDataSet.numberOfVariables];
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum += normalizedDataSet.getUnitRow().getUnitRow()[j].getUnitValues().getValues()[i];
            }
            sumRow[i]=sum/normalizedDataSet.numberOfVariableRows;
        }
        return sumRow;
    }
    public void addUnitRow(UnitRow unitRow){
        this.setUnitRow(unitRow);
    }
    public int getNumberOfClusters(){
        return numberOfClusters;
    }
    public void setNumberOfClusters(int numberOfClusters){
        this.numberOfClusters = numberOfClusters;
    }
    public int getNumberOfVariables(){
        return numberOfVariables;
    }
    public void setNumberOfVariables(int numberOfVariables){
        this.numberOfVariables = numberOfVariables;
    }
    public UnitRow getUnitRow(){
        return unitRow;
    }
    public void setUnitRow(UnitRow unitRow){
        this.unitRow = unitRow;
    }
    public String [] getNames(){
        return names;
    }
    public void setNames(String [] names){
        this.names = names;
    }
    public int getNumberOfVariableRows(){
        return numberOfVariableRows;
    }
    public void setNumberOfVariableRows(int numberOfVariableRows){
        this.numberOfVariableRows = numberOfVariableRows;
    }


}

