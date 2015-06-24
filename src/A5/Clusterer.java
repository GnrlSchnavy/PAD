package A5;


import ui.*;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;



public class Clusterer{

    ClusterRow clusterRow;
    PrintStream out;
    Unit unit;
    UnitRow unitRow;
    Dataset dataSet;
    NumberRow numberRow;
    DrawUserInterface ui;
    Event event;
    View view;
    DistanceMeasure distanceMeasure;
    ClusterMethod clusterMethod =new CompleteLinkage(distanceMeasure);

    Clusterer(){
        out = new PrintStream(System.out);
        Locale.setDefault(Locale.US);
        ui = UserInterfaceFactory.getDrawUI(600,600);
    }
    private void start() {
        prerequisities();
        normalizationAndPreselection();
        pickDistanceMeasures();
        clusterRow = new ClusterRow(dataSet);
        view = new Cartasian(ui,dataSet, clusterRow);
        handleEvents();
    }

    private void handleEvents() {
        while (true) {
            event=ui.getEvent();
            if (event.name.equals("other_key")) {
                if (event.data.equals("Escape")) {
                    exitProgram();
                }
                if (event.data.equals("Space")) {
                    clusterRow.cluster(clusterMethod);
                    view.draw(clusterRow);
                    System.out.println("HandleSpace");
                }
            }
            if (event.name.equals("mouseover")){
                System.out.println(event.data);
            }
        }


    }
    private void exitProgram(){
        System.exit(0);
    }
    private void pickDistanceMeasures() {
        String  clusteringMethod = UIAuxiliaryMethods.askUserForChoice("Choose distance measure", "AverageLinkage", "CompleteLinkage", "SingleLinkage");
        String distanceMeasure = UIAuxiliaryMethods.askUserForChoice("Choose clustering method", "Euclidean","Manhattan","Pearson");
        handleUserClusteringDistance(distanceMeasure, clusteringMethod);
    }
    private void prerequisities() {
        System.out.println("running A5");
        if(!UIAuxiliaryMethods.askUserForInput()){
            exitProgram();}
        Scanner file = new Scanner(System.in);
        dataSet = readFile(file);
    }
    private void normalizationAndPreselection() {
        NumberRow maximumValues = dataSet.calculateMaximumValue();
        NumberRow minimumValues = dataSet.calculateMinimum();
        dataSet.normalize(dataSet, maximumValues, minimumValues);
        dataSet.doPreselection(dataSet);
    }
    private void handleUserClusteringDistance(String distanceMeasureString, String clusteringMethodString) {
        switch(distanceMeasureString){
            case "Euclidean": distanceMeasure = new Euclidean();break;
            case "Manhattan": distanceMeasure = new Manhattan();break;
            case "Pearson": distanceMeasure = new Pearson();break;
        }
        switch(clusteringMethodString){
            case "AverageLinkage": clusterMethod = new AverageLinkage(distanceMeasure);break;
            case "CompleteLinkage": clusterMethod = new CompleteLinkage(distanceMeasure);break;
            case "SingleLinkage": clusterMethod = new SingleLinkage(distanceMeasure);break;
        }
    }
    private Dataset readFile(Scanner file){

        dataSet=new Dataset(file.nextInt(),file.nextInt(),file.nextInt());
        dataSet.setNames(getNames(file));
        unitRow = new UnitRow(dataSet.getNumberOfVariableRows());

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
        new Clusterer().start();
    }
}











