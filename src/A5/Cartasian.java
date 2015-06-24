package A5;
import ui.*;
/**
 * Created by Yvan on 21-6-2015.
 */
public class Cartasian implements View {
    DrawUserInterface ui;
    private final int SCREEN_HEIGHT = 600;
    private final int SCREEN_WIDTH = 600;
    Colour black;
    String [] names;
    Dataset d;
    ClusterRow clusterRow;
    Cluster[]clusters;



    Cartasian(DrawUserInterface ui, Dataset d,ClusterRow clusterRow) {
        this.ui = ui;
        black = new Colour(0, 0, 0);
        this.d = d;
        this.clusterRow = clusterRow;
    }

    private void drawCircles(ClusterRow clusterRow) {

        for (int i = 0; i < clusterRow.getLength(); i++) {
            for (int j = 0; j < clusterRow.getCluster(i).getUnits().getLength() ; j++) {

            }

        }
    }

    private void drawGraph(ClusterRow clusterRow) {
        for (int i = 0; i < clusterRow.getLength(); i++) {
            Colour c=createRandomColor();
            for (int j = 0; j < clusterRow.getCluster(i).getUnits().getLength(); j++) {
                ui.drawCircle((int)(clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(0)*500)+10,(int)(clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(1)*500)+10,10,10,c,true);
                ui.drawCircle((int)(clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(0)*500)+10,(int)(clusterRow.getCluster(i).getUnits().getUnit(j).getNumberRow().getValues(1)*500)+10,10,10,black,false);
            }
        }

        ui.showChanges();
    }

    private void drawGraphBackground(){
        ui.drawLine(10, 10, 10, 500, black);
        ui.drawLine(10, 10, 500, 10, black);

        ui.showChanges();

    }

    private Colour createRandomColor(){
        int randomR = (int)(Math.random()*255);
        int randomG = (int)(Math.random()*255);
        int randomB = (int)(Math.random()*255);
        Colour randomColor = new Colour(randomR,randomG,randomB);
        return randomColor;
    }



    private void drawNames(Dataset d) {
        ui.drawText(10, 505, d.getNames(2), black);
        ui.drawText(480, 0, d.getNames(1), black);
    }

    public void draw(ClusterRow clusters) {
        ui.clear();
        drawGraphBackground();
        drawGraph(clusterRow);
        drawCircles(clusterRow);
        drawNames(d);
        ui.showChanges();
    }


}

