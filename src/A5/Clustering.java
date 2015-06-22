package A5;

import ui.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class Clustering{

    private final int SCREEN_HEIGHT = 600;
    private final int SCREEN_WIDTH = 600;
    ClusterRow clusterRow;
    PrintStream out;
    Unit unit;
    UnitRow unitRow;
    Dataset dataSet;
    NumberRow numberRow;
    View view;
    DrawUserInterface ui;
    Colour black;



    Clustering(){
        out = new PrintStream(System.out);
        Locale.setDefault(Locale.US);
        ui = UserInterfaceFactory.getDrawUI(SCREEN_HEIGHT, SCREEN_WIDTH);
        black = new Colour(0,0,0);
    }
    private void start()  {

        prerequisities();
        normalizationAndPreselection();
        ClusterRow clusterRow = new ClusterRow(dataSet);
        drawGraph(clusterRow);
        //printDataSetToFile(dataSet);
        //printCheck(dataSet);
    }

    private Colour createRandomColor(){
        int randomR =(int)(Math.random() * 255);
        int randomG =(int)(Math.random() * 255);
        int randomB =(int)(Math.random() * 255);
        Colour rndColour = new Colour(randomR,randomG,randomB);
        return  rndColour;
    }
    private void drawGraph(ClusterRow clusterRow) {

        ui.clear();
        for (int i = 0; i < clusterRow.getLength(); i++) {
            System.out.println(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(0) + " " +clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(1));
            ui.drawCircle((int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(0)*500),(int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(1)*500),5,5,createRandomColor(),true);
        }
        ui.showChanges();

    }

    private void prerequisities() {
        System.out.println("running A5");
        if(!UIAuxiliaryMethods.askUserForInput()){System.exit(0);}
        Scanner file = new Scanner(System.in);
        dataSet = readFile(file);
    }
    private void normalizationAndPreselection() {
        NumberRow maximumValues = dataSet.calculateMaximumValue();
        NumberRow minimumValues = dataSet.calculateMinimum();
        dataSet.normalize(dataSet, maximumValues, minimumValues);
        dataSet.doPreselection(dataSet);

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
                    System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getNumberRow().getValues(j) + "  ");
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
                    System.out.print(normalizedDataSet.getUnitRow().getUnit(i).getNumberRow().getValues(j) + "  ");
                }
                System.out.print("\n");
            }
        }

    }
    private void printDataSetToFile(Dataset toPrintDataSet)  {
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
                writer.print(toPrintDataSet.getUnitRow().getUnit(i).getNumberRow().getValues(j)+"  ");
            }
            writer.println();
        }
        writer.close();
    }
    private Dataset readFile(Scanner file){

        dataSet=new Dataset(file.nextInt(),file.nextInt(),file.nextInt());
        dataSet.setNames(getNames(file));
        unitRow = new UnitRow(dataSet.getNumberOfVariableRows());
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











