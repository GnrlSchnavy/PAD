package A4;

import ui.UIAuxiliaryMethods;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Scanner;


public class Clustering{

    ClusterRow clusterRow;
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
        prerequisities();
        normalizationAndPreselection();
        ClusterRow clusterRow = new ClusterRow(dataSet);
//        Euclidean distanceEuclidean = new Euclidean();
//        Manhattan distanceManhattan = new Manhattan();
//        Pearson distancePearson = new Pearson();

//        System.out.println("Eucledian " + distanceEuclidean.calculateDistance(dataSet.getUnitRow().getUnit(0), dataSet.getUnitRow().getUnit(1)));
//        System.out.println("Manhattan " + distanceManhattan.calculateDistance(dataSet.getUnitRow().getUnit(0), dataSet.getUnitRow().getUnit(1)));
//        System.out.println("Pearson " + distancePearson.calculateDistance(dataSet.getUnitRow().getUnit(0), dataSet.getUnitRow().getUnit(1)));


        CompleteLinkage completeLinkage = new CompleteLinkage(new Euclidean());
        System.out.println(completeLinkage.calculateDistance(clusterRow.getCluster(0),clusterRow.getCluster(1)));
        AverageLinkage averageLinkage = new AverageLinkage(new Euclidean());
        System.out.println(averageLinkage.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));
        SingleLinkage singleLinkage = new SingleLinkage(new Euclidean());
        System.out.println(singleLinkage.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));

        CompleteLinkage completeLinkage1 = new CompleteLinkage(new Manhattan());
        System.out.println(completeLinkage1.calculateDistance(clusterRow.getCluster(0),clusterRow.getCluster(1)));
        AverageLinkage averageLinkage1 = new AverageLinkage(new Manhattan());
        System.out.println(averageLinkage1.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));
        SingleLinkage singleLinkage1 = new SingleLinkage(new Manhattan());
        System.out.println(singleLinkage1.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));

        CompleteLinkage completeLinkage2 = new CompleteLinkage(new Pearson());
        System.out.printf("%.6f",completeLinkage2.calculateDistance(clusterRow.getCluster(0),clusterRow.getCluster(1)));
        AverageLinkage averageLinkage2 = new AverageLinkage(new Pearson());
        System.out.println(averageLinkage2.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));
        SingleLinkage singleLinkage2 = new SingleLinkage(new Pearson());
        System.out.println(singleLinkage2.calculateDistance(clusterRow.getCluster(0), clusterRow.getCluster(1)));

//


        //printDataSetToFile(dataSet);
        //printCheck(dataSet);
    }
    private void prerequisities() {
        System.out.println("running A4");
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
                writer.print(toPrintDataSet.getUnitRow().getUnit(i).getNumberRow().getValues(j)+"  ");
            }
            writer.println();
        }
        writer.close();
    }
    private Dataset readFile(Scanner file){

        dataSet=new Dataset();
        dataSet.setNumberOfClusters(file.nextInt());
        dataSet.setNumberOfVariableRows(file.nextInt());
        dataSet.setNumberOfVariables(file.nextInt());
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











