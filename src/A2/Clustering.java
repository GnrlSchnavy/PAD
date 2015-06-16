package A2;

import ui.UIAuxiliaryMethods;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Scanner;


public class Clustering{

    PrintStream out;
    Unit unit;
    UnitRow unitRow;
    Dataset dataSet;
    NumberRow numberRow;
    Clustering(){
        out = new PrintStream(System.out);
        Locale.setDefault(Locale.US);
    }
    private void start()  {
        System.out.println("running A2");
        UIAuxiliaryMethods.askUserForInput();
        Scanner file = new Scanner(System.in);//.useLocale(Locale.US);
        dataSet = readFile(file);
        NumberRow maximumValues = calculateMaximumValues(dataSet);
        NumberRow minimumValues = calculateMinumumValues(dataSet);
        dataSet.normalize(dataSet, maximumValues, minimumValues);
        dataSet.doPreselection(dataSet);
        printDataSetToFile(dataSet);
      //  printDataSet(dataSet);
    }
    public void printDataSetToFile(Dataset toPrintDataSet)  {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("C:/Users/Yvan/Desktop/test.txt","UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

          for (int i = 0;i<toPrintDataSet.getNumberOfVariableRows();i++){
            writer.print(toPrintDataSet.getUnitRow().getUnit(i).getUnitName()+"  ");
            for(int j= 0;j<toPrintDataSet.getNumberOfVariables();j++){
                writer.print(toPrintDataSet.getUnitRow().getUnit(i).getUnitValues().getValues(j)+"  ");
            }
            writer.println();
        }
        writer.close();
    }
    public void printDataSet(Dataset toPrintDataSet){
        for (int i = 0;i<toPrintDataSet.getNumberOfVariables()+1;i++){
            System.out.print(toPrintDataSet.getNames()[i]);
        }
        System.out.print("\n");
        for (int i = 0;i<toPrintDataSet.getUnitRow().getLength();i++){
            System.out.print(toPrintDataSet.getUnitRow().getUnit(i) .getUnitName()+"  ");
            for (int j = 0;j<toPrintDataSet.getNumberOfVariables();j++){
                System.out.print(toPrintDataSet.getUnitRow().getUnit(i).getUnitValues().getValues(j) + "  ");
            }
            System.out.print("\n");
        }
    }
    private NumberRow calculateMinumumValues(Dataset dataSet2){
        NumberRow lowestValueRow = new NumberRow(dataSet2.getNumberOfVariables());
        for(int i = 0; i<dataSet2.getNumberOfVariables();i++){
            double lowestValue = dataSet.getUnitRow().getUnit(0).getUnitValues().getValues(i);
            for(int j = 0;j<dataSet.getUnitRow().getLength();j++){
                if(dataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i)<lowestValue){
                    lowestValue=dataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i);
                }
            }
            lowestValueRow.setValue(i, lowestValue);
            //System.out.print(lowestValueRow.getValues()[i] + "\n");

        }

        return lowestValueRow;
    }
    private NumberRow calculateMaximumValues(Dataset dataSet2){
        NumberRow highestValueRow = new NumberRow(dataSet2.getNumberOfVariables());
        for (int i =0;i<dataSet2.getNumberOfVariables();i++){
            double highestValue = dataSet.getUnitRow().getUnit(0).getUnitValues().getValues(0);
            for (int j = 0;j<dataSet.getUnitRow().getLength();j++){
                if(dataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i)>highestValue){
                    highestValue=dataSet.getUnitRow().getUnit(j).getUnitValues().getValues(i);
                }
            }
            highestValueRow.setValue(i,highestValue);
            //System.out.print(highestValueRow.getValues()[i] + "\n");

        }
        return highestValueRow;
    }
    private Dataset readFile(Scanner file){

        dataSet=new Dataset();
        dataSet.setNumberOfClusters(file.nextInt());
        dataSet.setNumberOfVariableRows(file.nextInt());
        dataSet.setNumberOfVariables(file.nextInt());
        dataSet.setNames(getNames(file));
        unitRow = new UnitRow (dataSet.getNumberOfVariableRows());
        //System.out.println(dataSet.getNumberOfVariableRows());
        //System.out.println(dataSet.getNumberOfVariables());

        for (int i = 0;i<dataSet.getNumberOfVariableRows();i++){
            numberRow = new NumberRow(dataSet.getNumberOfVariables());
            String unitName= file.next();
            for (int j = 0;j<dataSet.getNumberOfVariables();j++){
                double value = file.nextDouble();
                numberRow.addValue(value);
            }
            unit = new Unit(unitName,numberRow);
            unitRow.addUnit(unit);
            dataSet.addUnitRow(unitRow);
        }
        return dataSet;
    }
    private String[] getNames (Scanner file){
        String [] names = new String [dataSet.getNumberOfVariables()+1];
        for (int i =0;i<dataSet.getNumberOfVariables()+1;i++){
            names[i] = file.next();
        }
        return names;
    }
    public static void main(String[] argv) {
        new Clustering().start();
    }
}











