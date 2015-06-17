package A3;

import java.util.Arrays;

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
                toNormalizeDataSet.unitRow.getUnit(i).getUnitValues().setValue(j,(toNormalizeDataSet.getUnitRow().getUnit(i).getUnitValues().getValues(j) - minimumValues.getValues(j))/(maximumValues.getValues(j)-minimumValues.getValues(j)));
            }
        }
        return toNormalizeDataSet;
    }
    public UnitRow doPreselection(Dataset normalizedDataSet){
        double [] averageValueRow = addAllNumbers(normalizedDataSet);
        NumberRow standardDeviationRow = calculateStandardDeviationRow(normalizedDataSet,averageValueRow);
        getHighestStandardDeviation(standardDeviationRow,normalizedDataSet);
        return null;
    }
    private void getHighestStandardDeviation(NumberRow standardDeviationRow, Dataset normalizedDataSet){
        //data contains more than 50
        if(standardDeviationRow.getLength()>50){
            getTop50(standardDeviationRow,normalizedDataSet);
        }

        //printCheck(normalizedDataSet);
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
            //System.out.println( highestStandardDeviationsIndecies[i]);
        }
        Arrays.sort(highestStandardDeviationsIndecies);
    }
    private void printCheck(Dataset normalizedDataSet) {

        if(normalizedDataSet.getNames().length>50) {
            for (int i = 0; i < 50; i++) {
                System.out.print(normalizedDataSet.getNames()[i] + " ");
            }
            System.out.print("\n");
            for (int i = 0; i < normalizedDataSet.getNumberOfVariableRows(); i++) {
                System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getUnitName() + "  ");
                for (int j = 0; j < 50; j++) {
                    System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getUnitValues().getValues(j) + "  ");
                }
                System.out.print("\n");
            }
        }
        else{
            for (int i = 0; i < normalizedDataSet.getNumberOfVariables(); i++) {
                System.out.print(normalizedDataSet.getNames()[i] + " ");
            }
            System.out.print("\n");
            for (int i = 0; i < normalizedDataSet.getNumberOfVariableRows(); i++) {
                System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getUnitName() + "  ");
                for (int j = 0; j < normalizedDataSet.getNumberOfVariables(); j++) {
                    System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getUnitValues().getValues(j) + "  ");
                }
                System.out.print("\n");
            }
        }

    }
    private void copyValuesToDataSet(Dataset normalizedDataSet,int copyTo, int copyFrom) {
        for(int i=0;i < normalizedDataSet.getNumberOfVariableRows();i++ ){
            //System.out.println(normalizedDataSet.unitRow.getUnit(i).getUnitValues().getValues(copyFrom));
            normalizedDataSet.unitRow.getUnit(i).getUnitValues().setValue(copyTo,normalizedDataSet.unitRow.getUnit(i).getUnitValues().getValues(copyFrom));
            normalizedDataSet.names[copyTo]=normalizedDataSet.names[copyFrom+1];
            //System.out.println(normalizedDataSet.getNames()[copyFrom+1]);
        }

    }
    private NumberRow calculateStandardDeviationRow(Dataset normalizedDataSet, double [] averageValueRow){
        int counter = 0;
        NumberRow standardDeviationRow = new NumberRow(normalizedDataSet.getNumberOfVariables());
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum+=Math.pow(normalizedDataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i)-averageValueRow[i],2);
                counter++;
                //System.out.println(sum);
            }
            standardDeviationRow.addValue(Math.sqrt((sum/normalizedDataSet.numberOfVariableRows*normalizedDataSet.numberOfVariables)));
            //System.out.println(standardDeviationRow.getValues(i));
        }
        return standardDeviationRow;
    }
    public NumberRow calculateMaximumValue(){
        NumberRow highestValueRow = new NumberRow(getNumberOfVariables());
        for (int i =0;i<getNumberOfVariables();i++){
            double highestValue = getUnitRow().getUnit(0).getUnitValues().getValues(0);
            for (int j = 0;j<getUnitRow().getLength();j++){
                if(getUnitRow().getUnit(j).getUnitValues().getValues(i)>highestValue){
                    highestValue=getUnitRow().getUnit(j).getUnitValues().getValues(i);
                }
            }
            highestValueRow.setValue(i, highestValue);
            //System.out.print(highestValueRow.getValues()[i] + "\n");

        }
        return highestValueRow;
    }
    public NumberRow calculateMinimum() {
        NumberRow lowestValueRow = new NumberRow(getNumberOfVariables());
        for(int i = 0; i<getNumberOfVariables();i++){
            double lowestValue = getUnitRow().getUnit(0).getUnitValues().getValues(i);
            for(int j = 0;j<getUnitRow().getLength();j++){
                if(getUnitRow().getUnit(j).getUnitValues().getValues(i)<lowestValue){
                    lowestValue=getUnitRow().getUnit(j).getUnitValues().getValues(i);
                }
            }
            lowestValueRow.setValue(i, lowestValue);
            //System.out.print(lowestValueRow.getValues()[i] + "\n");
        }
        return lowestValueRow;
    }
    private double [] addAllNumbers(Dataset normalizedDataSet){
        double [] sumRow = new double [normalizedDataSet.numberOfVariables];
        for(int i = 0;i<normalizedDataSet.getNumberOfVariables();i++){
            double sum = 0;
            for (int j = 0;j<normalizedDataSet.getUnitRow().getLength();j++){
                sum += normalizedDataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i);
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

