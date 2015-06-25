package A6;

import ui.Colour;
import ui.DrawUserInterface;

public class Dendogram implements View {
    private DrawUserInterface ui;
    private Dataset d;
    private ClusterRow clusterRow;
    private final int XVALUE = 520;
    private final int YINCREASE = 19;
    private int yStart;
    private final int CIRCLEOFFSET = 8;
    private final int CIRCLERADIUS = 10;
    private Colour black = new Colour(0, 0, 0);


    Dendogram(DrawUserInterface ui, Dataset d, ClusterRow clusterRow) {
        this.ui = ui;
        this.d = d;
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

        int x = (int) (600 / ((cluster.getDepth() + 1) / 1.5));
        ui.drawLine(x, leftY, x+40, leftY, black);
        ui.drawLine(x, rightY,x+40 , rightY, black);
        ui.drawLine(x, leftY, x, rightY, black);
        ui.drawCircle(x, middleY, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        return middleY;
    }

    private int drawLeaf(Cluster cluster, Colour colour) {
        yStart += YINCREASE;
        ui.drawText(XVALUE, yStart, cluster.getUnits().getUnit(0).getUnitName(), black);
        ui.drawCircle(XVALUE - CIRCLEOFFSET, yStart + 5, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        return yStart;
    }

//    private int (Cluster cluster){
//
//    }

    public void draw(ClusterRow clusters) {
        ui.clearStatusBar();
        ui.clear();
        yStart = 10;
        for (int i = 0; i < clusterRow.getLength(); i++) {
            Colour c = createRandomColor();
            drawCluster(clusterRow.getCluster(i), c); //draws every cluster in clusterRow
        }
        ui.showChanges();
    }
}




