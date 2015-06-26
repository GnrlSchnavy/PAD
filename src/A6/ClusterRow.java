package A6;

public class ClusterRow {

    private int length =0;
    private Cluster[] cluster;
    private Dataset dataSet;

    public ClusterRow(Dataset dataSet){
        cluster = new Cluster[dataSet.getNumberOfVariableRows()];
        createLeafes(dataSet);
        this.dataSet = dataSet;

    }
    private void createLeafes(Dataset dataSet){
        for (int i = 0; i < dataSet.getNumberOfVariableRows(); i++) {
            Cluster leaf = new Leaf(dataSet.getUnitRow().getUnit(i));
            addCluster(leaf);
        }
    }
    public void addCluster(Cluster cluster){
        this.cluster[length] = cluster;
        length++;
    }
    public void cluster(ClusterMethod clusterMethod) {
        if (length > dataSet.getNumberOfClusters()){
            Cluster[] closestClusters = getSmallestDistance(clusterMethod);
            removeClusters(closestClusters);
            addNode(closestClusters);
        }
    }
    private void addNode(Cluster[] closestClusters) {
        Node n=new Node(closestClusters[0],closestClusters[1]);
        addCluster(n);
    }
    private void removeClusters(Cluster[] toRemove) {

        Cluster[] newClusterRow = new Cluster[cluster.length-1];
        length-=toRemove.length;
        int placeHolder = 0;
        for (int i = 0; i < cluster.length; i++) {
            if(toRemove[0]!= cluster[i] && toRemove[1]!=cluster[i] && cluster[0] != cluster[1]){
                newClusterRow[placeHolder] = cluster[i];
                placeHolder++;
            }
        }
        cluster= newClusterRow;
    }
    private Cluster[] getSmallestDistance(ClusterMethod clusterMethod) {
        double minDistance = Double.MAX_VALUE;
        double distance;
        Cluster[] closestClusters = new Cluster[2];
        for (int i = 0; i < cluster.length; i++) {
            for (int j = 0; j < cluster.length; j++) {
                if (cluster[i] != cluster[j]) {
                    distance = clusterMethod.calculateDistance(cluster[i], cluster[j]);
                    if(distance < minDistance ){
                        minDistance = distance;
                        closestClusters[0] = cluster[i];
                        closestClusters[1] = cluster[j];
                    }
                }
            }
        }
        return closestClusters;
    }
    public Cluster getCluster(int i){
        return cluster[i];
    }
    public void setCluster(Cluster[] cluster){
        this.cluster = cluster;
    }
    public int getLength() {
        return length;
    }


}

