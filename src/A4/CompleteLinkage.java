import A4.DistanceMeasure;

public class CompleteLinkage implements ClusterMethod {
	
	/* Variable declaration(s) */


	/* Constructor(s) */

    CompleteLinkage(DistanceMeasure distanceMeasure) {
        this.distanceMeasure = distanceMeasure;
    }
	
	/* Interface methods */

    public double calculateDistance(Cluster cluster1, Cluster cluster2) {

        double thisDistance;
        double smallestDistance = Double.MAX_VALUE;
        for (int c1 = 0; c1 < cluster1.getWidth(); c1++){
            for (int c2 = 0; c2 < cluster2.getWidth(); c2++){
                thisDistance = distanceMeasure.calculateDistance(cluster1.getUnit(c1), cluster2.getUnit(c2));
                if (thisDistance < smallestDistance) smallestDistance = thisDistance;
            }
        }
        return smallestDistance;
    }
}
