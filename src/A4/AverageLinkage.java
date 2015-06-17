package A4;

public class AverageLinkage implements ClusterMethod {

    private DistanceMeasure distanceMeasure;

    AverageLinkage(DistanceMeasure distanceMeasure) {
        this.distanceMeasure = distanceMeasure;
    }

    public double calculateDistance(Cluster cluster1, Cluster cluster2) {
        int numberOfLoops = 0;
        double sumDistance = 0;
        for (int i = 0; i < cluster1.getWidth(); i++) {
            for (int j = 0; j < cluster2.getWidth(); j++) {
                sumDistance += distanceMeasure.calculateDistance(cluster1.getUnits().getUnit(i), cluster2.getUnits().getUnit(j));
                numberOfLoops++;
            }
        }
        return sumDistance/(numberOfLoops);//getWitdh?
    }
}
