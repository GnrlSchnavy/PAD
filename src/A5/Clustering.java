package A5;

import A4.*;
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
    Clusterer clusterer;

    DistanceMeasure distanceMeasure = new Pearson(); //this should be adaptable to user input
    ClusterMethod clusterMethod =new SingleLinkage(distanceMeasure);//this should be adaptable to user input



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


    }
    private void drawGraph(ClusterRow clusterRow) {
        for (int i = 0; i < clusterRow.getLength(); i++) {
//            System.out.println(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(0) + " " +clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(1));
            ui.drawCircle((int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(0)*500)+10,(int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(1)*500)+10,10,10,createRandomColor(),true);
            ui.drawCircle((int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(0)*500)+10,(int)(clusterRow.getCluster(i).getUnits().getUnit(0).getNumberRow().getValues(1)*500)+10,10,10,black,false);

        }
        ui.showChanges();
    }
    private void prerequisities() {
        System.out.println("running A5");
        if(!UIAuxiliaryMethods.askUserForInput()){System.exit(0);}
        Scanner file = new Scanner(System.in);
        dataSet = readFile(file);
        drawGraphBackground();
    }
    private void normalizationAndPreselection() {
        NumberRow maximumValues = dataSet.calculateMaximumValue();
        NumberRow minimumValues = dataSet.calculateMinimum();
        dataSet.normalize(dataSet, maximumValues, minimumValues);
        dataSet.doPreselection(dataSet);

    }
    private Colour createRandomColor(){
        int randomR = (int)(Math.random()*255);
        int randomG = (int)(Math.random()*255);
        int randomB = (int)(Math.random()*255);
        Colour randomColor = new Colour(randomR,randomG,randomB);
        return randomColor;
    }
    private void drawGraphBackground(){
        ui.drawLine(10,10,10,500,black);
        ui.drawLine(10,10,500,10,black);
        ui.drawText(10,505,dataSet.getNames(2),black);
        ui.drawText(480,0,dataSet.getNames(1),black);
        ui.showChanges();

    };
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











