package A6;

public class SingleLinkage implements ClusterMethod {

    private DistanceMeasure distanceMeasure;

    SingleLinkage(DistanceMeasure distanceMeasure) {
        this.distanceMeasure = distanceMeasure;
    }

    public double calculateDistance(Cluster cluster1, Cluster cluster2) {
        double distance;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < cluster1.getWidth(); i++) {
            for (int j = 0; j < cluster2.getWidth(); j++) {
                distance = distanceMeasure.calculateDistance(cluster1.getUnits().getUnit(i),cluster2.getUnits().getUnit(j));
                if(distance < minDistance){
                    minDistance = distance;
                }
            }
        }
        return minDistance;
    }
}
