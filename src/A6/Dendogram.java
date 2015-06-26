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
    private int yStart;
    private Colour black = new Colour(0, 0, 0);



    Dendogram(DrawUserInterface ui, ClusterRow clusterRow) {
        this.ui = ui;
        this.clusterRow = clusterRow;
    }

    private Colour createRandomColor() {
        int randomR = (int) (Math.random() * 255);
        int randomG = (int) (Math.random() * 255);
        int randomB = (int) (Math.random() * 255);
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
        int leftY = drawCluster(((Node) cluster).getLeftChild(), colour);
        int rightY = drawCluster(((Node) cluster).getRightChild(), colour);
        int middleY = ((leftY + rightY) / 2);
        int lefX = getXValue(((Node) cluster).getLeftChild());
        int rightX = getXValue(((Node) cluster).getRightChild());
        int x = getXValue(cluster);
        ui.drawLine(x, leftY, lefX, leftY, black);              //draw horizontal line
        ui.drawLine(x, rightY,rightX , rightY, black);          //draw horizontal line
        ui.drawLine(x, leftY, x, rightY, black);
        ui.drawCircle(x, middleY, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        return middleY;
    }

    private int getXValue(Cluster cluster) {
        if(!cluster.hasChildren()){
            return XVALUE-14;
        }
        else{
            return ((XVALUE - CLUSTERDISTANCE) - CLUSTERDISTANCE * cluster.getDepth())- CIRCLEOFFSET;
        }
    }


    private int drawLeaf(Cluster cluster, Colour colour) {
        yStart += YINCREASE;
        ui.drawText(XVALUE, yStart - CIRCLEOFFSET/2, cluster.getUnits().getUnit(0).getUnitName(), black);
        ui.drawCircle(XVALUE - CIRCLEOFFSET, yStart, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        return yStart;
    }



    public void draw(ClusterRow clusters) {
        ui.clear();
        yStart = 10;
        for (int i = 0; i < clusterRow.getLength(); i++) {
            Colour c = createRandomColor();
            drawCluster(clusterRow.getCluster(i), c); //draws every cluster in clusterRow
        }

        ui.showChanges();
    }
}




