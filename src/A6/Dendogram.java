package A6;

import ui.Colour;
import ui.DrawUserInterface;

public class Dendogram implements View {
    private DrawUserInterface ui;
    private ClusterRow clusterRow;
    private final int XVALUE = 520;
    private final int YINCREASE = 19;
    private final int CIRCLEOFFSET = 8;
    private final int CIRCLERADIUS = 10;
    private final int CLUSTERDISTANCE = 50;
    private final int LEAFCIRCLEOFFSET = 14;
    private final int MAXCOLORVALUE = 255;
    private int yStart;
    private Colour black = new Colour(0, 0, 0);

    Dendogram(DrawUserInterface ui, ClusterRow clusterRow) {
        this.ui = ui;
        this.clusterRow = clusterRow;
    }

     private Colour createRandomColor() {
        int randomR = (int) (Math.random() * MAXCOLORVALUE);
        int randomG = (int) (Math.random() * MAXCOLORVALUE);
        int randomB = (int) (Math.random() * MAXCOLORVALUE);
        return new Colour(randomR, randomG, randomB);
    }

    private int drawCluster(Cluster cluster, Colour colour) {
        int yValue;
        if (cluster.hasChildren()) {
            yValue = drawNode(cluster, colour, yStart);
        } else {
            yValue = drawLeaf(cluster, colour);
        }
        return yValue;
    }

    private int drawNode(Cluster cluster, Colour colour, int yValue) {

        int [] yValues =  getYvalues(cluster, colour);
        int [] xValues = getxValues(cluster);
        int nodeY = ((yValues[0] + yValues[1] ) / 2);
        int nodeX = getXValue(cluster);
        drawThisNode(yValues, xValues, nodeY, nodeX,colour);
        return nodeY;
    }

    private int[] getYvalues(Cluster cluster, Colour colour) {
        int [] temp = {drawCluster(((Node) cluster).getLeftChild(), colour),drawCluster(((Node) cluster).getRightChild(),colour)};
        return temp;
    }

    private int[] getxValues(Cluster cluster){
        int [] temp = {getXValue(((Node) cluster).getLeftChild()),getXValue(((Node) cluster).getRightChild())};
        return temp;
    }

    private void drawThisNode(int[] yValues, int[] xValues, int middleY, int x, Colour colour) {
        ui.drawLine(x, yValues[0], xValues[0] - 5, yValues[0], black);          //draw horizontal line to children
        ui.drawLine(x, yValues[1], xValues[1] - 5, yValues[1], black);          //draw horizontal line to children
        ui.drawLine(x, yValues[0], x, yValues[1], black);
        ui.drawCircle(x, middleY, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        ui.drawCircle(x, middleY, CIRCLERADIUS, CIRCLERADIUS, black, false);
    }

    private int getXValue(Cluster cluster) {
        if(!cluster.hasChildren()){
            return XVALUE-LEAFCIRCLEOFFSET;
        }
        else{
            return (((XVALUE - CLUSTERDISTANCE) - CLUSTERDISTANCE * cluster.getDepth())- CIRCLEOFFSET);
        }
    }

    private int drawLeaf(Cluster cluster, Colour colour) {
        yStart += YINCREASE;
        ui.drawText(XVALUE, yStart - CIRCLEOFFSET/2, cluster.getUnits().getUnit(0).getUnitName(), black);
        ui.drawCircle(XVALUE - CIRCLEOFFSET, yStart, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        ui.drawCircle(XVALUE - CIRCLEOFFSET, yStart, CIRCLERADIUS, CIRCLERADIUS, black, false);
        return yStart;
    }

    public void draw(ClusterRow clusters) {
        ui.clear();
        yStart = 10;
        for (int i = 0; i < clusterRow.getLength(); i++) {
            drawCluster(clusterRow.getCluster(i), createRandomColor()); //draws every cluster in clusterRow
        }
        ui.showChanges();
    }


}




