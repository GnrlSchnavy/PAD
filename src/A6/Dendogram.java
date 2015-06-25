package A6;

import ui.Colour;
import ui.DrawUserInterface;

public class Dendogram implements View {
    DrawUserInterface ui;
    Dataset d;
    ClusterRow clusterRow;
    private final int XVALUE = 520;
    Colour black = new Colour(0, 0, 0);


    Dendogram(DrawUserInterface ui, Dataset d, ClusterRow clusterRow) {
        this.ui = ui;
        this.d = d;
        this.clusterRow = clusterRow;
    }

    private void drawDendogram(ClusterRow clusterRow) {
        int yValue = 10;
        for (int i = 0; i <= clusterRow.length; i++) {
            ui.drawText(XVALUE, yValue, d.getUnitRow().getUnit(i).getUnitName(), black);
            ui.drawCircle(XVALUE - 8, yValue + 5, 10, 10, createRandomColor(), true);
            ui.drawLine(50, yValue + 5, XVALUE - 14, yValue + 5, black);
            yValue += 19;
        }

    }

    private Colour createRandomColor() {
        int randomR = (int) (Math.random() * 255);
        int randomG = (int) (Math.random() * 255);
        int randomB = (int) (Math.random() * 255);
        return new Colour(randomR, randomG, randomB);
    }


    private void drawDendogram2(Cluster cluster) {
        if (cluster.hasChildren()) {
            drawDendogram2(((Node) cluster).getLeftChild());
            drawDendogram2(((Node) cluster).getRightChild());

            //drawshit here
        }
        if (!cluster.hasChildren()) {
            drawLeaf(cluster);
        }

    }

    private void drawLeaf(Cluster cluster) {
        int yValue = 10;
        ui.drawText(XVALUE, yValue, cluster.getUnits().getUnit(0).getUnitName(), black);
        ui.drawCircle(XVALUE - 8, yValue + 5, 10, 10, createRandomColor(), true);
        ui.drawLine(50, yValue + 5, XVALUE - 14, yValue + 5, black);
    }


    public void draw(ClusterRow clusters) {
//    drawDendogram(clusters);
        for (int i = 0; i < clusterRow.getLength(); i++) {
            drawDendogram2(clusterRow.getCluster(i));
        }
        ui.showChanges();
    }


}




