package A6;

import ui.Colour;
import ui.DrawUserInterface;

public class Dendogram implements View {
    private DrawUserInterface ui;
    private Dataset d;
    private ClusterRow clusterRow;
    private final int XVALUE = 520;
    private final int YINCREASE = 19;
    private  int YSTART;
    private final int CIRCLEOFFSET = 8;
    private final int LONGLINE = 50;
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

    private void drawDendogram2(Cluster cluster,Colour colour) {
        if (cluster.hasChildren()) {
            drawDendogram2(((Node) cluster).getLeftChild(), colour);
            drawDendogram2(((Node) cluster).getRightChild(), colour);

            ui.drawCircle((int) (500 / ((cluster.getDepth() + 1) / 1.5)), YSTART, CIRCLERADIUS, CIRCLERADIUS, colour, true);


        } else {
            drawLeaf(cluster,colour);
        }
    }

    private void drawLeaf(Cluster cluster,Colour colour) {
        ui.drawText(XVALUE, YSTART, cluster.getUnits().getUnit(0).getUnitName(), black);
        ui.drawCircle(XVALUE - CIRCLEOFFSET, YSTART + 5, CIRCLERADIUS, CIRCLERADIUS, colour, true);
        ui.drawLine(LONGLINE, YSTART + 5, XVALUE - 14, YSTART + 5, black);
        YSTART += YINCREASE;
        ((Leaf) cluster).setCoordinates(XVALUE - CIRCLEOFFSET,YSTART + 5);
    }

    public void draw(ClusterRow clusters) {
        ui.clearStatusBar();
        ui.clear();
        YSTART = 10;
        for (int i = 0; i < clusterRow.getLength(); i++) {
            Colour c = createRandomColor();
            drawDendogram2(clusterRow.getCluster(i),c);
        }
        ui.showChanges();
    }


}




