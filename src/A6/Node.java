package A6;

import ui.Colour;

/**
 * Created by Yvan on 8-6-2015.
 */
public class Node implements Cluster {
    private int [] coordinates = new int[2];
    private Cluster cluster1,cluster2;
    private Colour colour;
    private UnitRow unitRow;
    public Node(Cluster cluster1, Cluster cluster2){
        unitRow = new UnitRow();
        this.cluster1= cluster1;
        this.cluster2= cluster2;
    }
    public int getDepth() {
        return Math.max(cluster1.getDepth(), cluster2.getDepth())+1;
    }
    public int getWidth() {
        return cluster1.getWidth()+cluster2.getWidth();
    }
    public UnitRow getUnits() {
        UnitRow unitRow1 = cluster1.getUnits();
        UnitRow unitRow2 = cluster2.getUnits();
        int totalLength = getWidth();
        UnitRow combinedUniteRow = new UnitRow(totalLength);
        for (int i = 0; i<cluster1.getWidth(); i++) {
            combinedUniteRow.addUnit(unitRow1.getUnit(i));
        }
        for (int i = 0; i <cluster2.getWidth() ; i++) {
            combinedUniteRow.addUnit(unitRow2.getUnit(i));
        }
        return combinedUniteRow;
    }
    public boolean hasChildren() {
        return true;
    }
    public Cluster getLeftChild() {
        return cluster1;
    }
    public Cluster getRightChild() {
        return cluster2;
    }
    public void setCoordinates(int x,int y){
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
    public int getCoordinates(int i){
        return coordinates[i];
    }
    public Colour getColour(){
        return colour;
    }
    public void setColour(Colour colour){
        this.colour = colour;
    }

}

