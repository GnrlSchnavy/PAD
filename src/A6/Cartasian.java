package A6;

import ui.Colour;
import ui.DrawUserInterface;

public class Cartasian implements View {
    DrawUserInterface ui;
    Dataset d;
    ClusterRow clusterRow;
    Colour black;
    private final int SCALE = 500;
    private final int SHIFT = 10;



    Cartasian(DrawUserInterface ui, Dataset d, ClusterRow clusterRow) {
        this.ui = ui;
        this.d = d;
        this.clusterRow = clusterRow;
        black = new Colour(0, 0, 0);
    }

    private void drawCircles(Cluster cluster, Colour colour) {
        double sumX = 0;
        double sumY = 0;
        double maxX;
        double maxY;
        double maxDistance = -Double.MAX_VALUE;
        for (int i = 0; i < cluster.getUnits().getLength(); i++) {
            sumX += cluster.getUnits().getUnit(i).getNumberRow().getValues(0);
            sumY += cluster.getUnits().getUnit(i).getNumberRow().getValues(1);
        }
        double averageValueX = sumX / cluster.getUnits().getLength();
        double averageValueY = sumY / cluster.getUnits().getLength();
        for (int i = 0; i < cluster.getUnits().getLength(); i++) {
            maxX = (cluster.getUnits().getUnit(i).getNumberRow().getValues(0) - averageValueX) * SCALE;
            maxY = (cluster.getUnits().getUnit(i).getNumberRow().getValues(1) - averageValueY) * SCALE;
            if (Math.sqrt(Math.pow(maxY, 2) + Math.pow(maxX, 2)) > maxDistance) {
                maxDistance = Math.sqrt(Math.pow(maxY, 2) + Math.pow(maxX, 2));
            }
        }
        ui.drawCircle((int) ((averageValueX * SCALE) + SHIFT), (int) ((averageValueY * SCALE) + SHIFT), (int) ((maxDistance * 2)), (int) ((maxDistance * 2)), colour, false);
    }

    private void drawGraph(ClusterRow clusterRow) {
        for (int i = 0; i < clusterRow.getLength(); i++) {
            Colour c = createRandomColor();
            for (int j = 0; j < clusterRow.getCluster(i).getUnits().getLength(); j++) {
                ui.drawCircle((int) (clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(0) * SCALE) + SHIFT, (int) (clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(1) * SCALE) + SHIFT, 10, 10, c, true);
                ui.drawCircle((int) (clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(0) * SCALE) + SHIFT, (int) (clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(1) * SCALE) + SHIFT, 10, 10, black, false);
                ui.setCircleHotspot((int) (clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(0) * SCALE) + SHIFT, (int) ((clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(1)) * SCALE) +SHIFT,10,10, clusterRow.getCluster(i).getUnits().getUnit(j).getUnitName());

            }
            if (clusterRow.getCluster(i).hasChildren()) {
                drawCircles(clusterRow.getCluster(i),c);
            }

        }
    }


    private void drawGraphBackground() {
        ui.drawLine(10, 10, 10, 500, black);
        ui.drawLine(10, 10, 500, 10, black);
        ui.showChanges();

    }

    private Colour createRandomColor() {
        int randomR = (int) (Math.random() * 255);
        int randomG = (int) (Math.random() * 255);
        int randomB = (int) (Math.random() * 255);
        return new Colour(randomR, randomG, randomB);
    }


    private void drawNames(Dataset d) {
        ui.drawText(10, 505, d.getNames(2), black);
        ui.drawText(480, 0, d.getNames(1), black);
    }

    public void draw(ClusterRow clusters) {
        ui.clear();
        drawGraphBackground();
        drawGraph(clusterRow);
        drawNames(d);
        ui.showChanges();
    }


}

