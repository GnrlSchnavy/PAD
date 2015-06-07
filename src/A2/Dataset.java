package A2;

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
            highestStandardDeviationsIndecies[i]=highestIndexNumber;
            standardDeviationRow.setValue(highestIndexNumber,-Double.MAX_VALUE);
            //System.out.println( highestStandardDeviationsIndecies[i]);

        }
        Arrays.sort(highestStandardDeviationsIndecies);
        for (int i = 0; i <50 ; i++) {
            System.out.println(normalizedDataSet.getNames()[highestStandardDeviationsIndecies[i]]);
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
            }
            standardDeviationRow.addValue(Math.sqrt((sum/normalizedDataSet.numberOfVariableRows*normalizedDataSet.numberOfVariables)-1));
            //System.out.print(counter/20+"  " + Math.sqrt(sum/((normalizedDataSet.numberOfVariableRows)-1))+"\n");
            //System.out.println(standardDeviationRow.getValues(i));
        }
        return standardDeviationRow;
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

