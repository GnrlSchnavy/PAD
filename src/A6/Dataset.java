package A6;

import java.util.Arrays;

public class Dataset {

    private int numberOfClusters;
    private int numberOfVariableRows;
    private int numberOfVariables;
    private UnitRow unitRow;
    private String[]names = new String[getNumberOfVariables()];

    Dataset(int numberOfClusters, int numberOfVariableRows, int numberOfVariables){
        this.numberOfClusters = numberOfClusters;
        this.numberOfVariableRows = numberOfVariableRows;
        this.numberOfVariables = numberOfVariables;
    }

    public Dataset normalize(Dataset toNormalizeDataSet, NumberRow maximumValues, NumberRow minimumValues){
        for (int i = 0;i<toNormalizeDataSet.getUnitRow().getLength();i++){
            for (int j = 0;j<toNormalizeDataSet.getNumberOfVariables();j++){
                toNormalizeDataSet.unitRow.getUnit(i).getNumberRow().setValue(j,(toNormalizeDataSet.getUnitRow().getUnit(i).getNumberRow().getValues(j) - minimumValues.getValues(j))
                /(maximumValues.getValues(j)-minimumValues.getValues(j)));
            }
        }
        return toNormalizeDataSet;
    }

    public void doPreselection(Dataset normalizedDataSet){
        double [] averageValueRow = addAllNumbers(normalizedDataSet);
        NumberRow standardDeviationRow = calculateStandardDeviationRow(normalizedDataSet,averageValueRow);
        getHighestStandardDeviation(standardDeviationRow,normalizedDataSet);
    }
    private void getHighestStandardDeviation(NumberRow standardDeviationRow, Dataset normalizedDataSet){
        if(standardDeviationRow.getLength()>50){
            getTop50(standardDeviationRow,normalizedDataSet);
        }
    }
    private void getTop50(NumberRow standardDeviationRow, Dataset normalizedDataSet) {
        int [] highestStandardDeviationsIndecies= new int [50];
        for(int i = 0;i<50;i++){
            int highestIndexNumber =0;
            double highestValue = -Double.MAX_VALUE;
            for(int j = 0;j<standardDeviationRow.getLength();j++){
                if(standardDeviationRow.getValues(j)>highestValue){
                    highestIndexNumber=j;
                    highestValue = standardDeviationRow.getValues(j);
                }
            }
            copyValuesToDataSet(normalizedDataSet,i,highestIndexNumber);
            highestStandardDeviationsIndecies[i]=highestIndexNumber+1;
            standardDeviationRow.setValue(highestIndexNumber,-Double.MAX_VALUE);
        }
    }
    private void copyValuesToDataSet(Dataset normalizedDataSet,int copyTo, int copyFrom) {
        for(int i=0;i < normalizedDataSet.getNumberOfVariableRows();i++ ){
            normalizedDataSet.unitRow.getUnit(i).getNumberRow().setValue(copyTo,normalizedDataSet.unitRow.getUnit(i).getNumberRow().getValues(copyFrom));
            normalizedDataSet.names[copyTo]=normalizedDataSet.names[copyFrom+1];
        }
    }
    private NumberRow calculateStandardDeviationRow(Dataset normalizedDataSet, double [] averageValueRow){
        NumberRow standardDeviationRow = new NumberRow(normalizedDataSet.getNumberOfVariables());
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum+=Math.pow(normalizedDataSet.getUnitRow().getUnit(j).getNumberRow().getValues(i)-averageValueRow[i],2);
            }
            standardDeviationRow.addValue(Math.sqrt((sum / (normalizedDataSet.numberOfVariableRows * normalizedDataSet.numberOfVariables))));
        }
        return standardDeviationRow;
    }
    public NumberRow calculateMaximumValue(){
        NumberRow highestValueRow = new NumberRow(getNumberOfVariables());
        for (int i =0;i<getNumberOfVariables();i++){
            double highestValue = getUnitRow().getUnit(0).getNumberRow().getValues(0);
            for (int j = 0;j<getUnitRow().getLength();j++){
                if(getUnitRow().getUnit(j).getNumberRow().getValues(i)>highestValue){
                    highestValue=getUnitRow().getUnit(j).getNumberRow().getValues(i);
                }
            }
            highestValueRow.setValue(i, highestValue);
        }
        return highestValueRow;
    }
    public NumberRow calculateMinimum() {
        NumberRow lowestValueRow = new NumberRow(getNumberOfVariables());
        for(int i = 0; i<getNumberOfVariables();i++){
            double lowestValue = getUnitRow().getUnit(0).getNumberRow().getValues(i);
            for(int j = 0;j<getUnitRow().getLength();j++){
                if(getUnitRow().getUnit(j).getNumberRow().getValues(i)<lowestValue){
                    lowestValue=getUnitRow().getUnit(j).getNumberRow().getValues(i);
                }
            }
            lowestValueRow.setValue(i, lowestValue);
        }
        return lowestValueRow;
    }
    private double [] addAllNumbers(Dataset normalizedDataSet){
        double [] sumRow = new double [normalizedDataSet.numberOfVariables];
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum += normalizedDataSet.getUnitRow().getUnit(j).getNumberRow().getValues(i);
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
    public String getNames(int i){
        return names[i];
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

