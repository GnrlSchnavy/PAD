package A5;

public class ClusterRow {

    int length =0;
    private Cluster[] cluster;

    public ClusterRow(Dataset dataSet){
        cluster = new Cluster[dataSet.getNumberOfVariableRows()];
        createLeafes(dataSet);
    }


    private void createLeafes(Dataset dataSet){
        for (int i = 0; i < dataSet.getNumberOfVariableRows(); i++) {
            Leaf leaf = new Leaf(dataSet.getUnitRow().getUnit(i));
            addCluster(leaf);
        }
    }
    public void addCluster(Cluster cluster){
        this.cluster[length] = cluster;
        length++;
    }

    public void cluster(ClusterMethod clusterMethod){
        Cluster [] closestClusters = getSmallestDistance(clusterMethod);
        removeClusters(closestClusters);
        addNode(closestClusters);

    }

    private void addNode(Cluster[] closestClusters) {
       Node n=new Node(closestClusters[0],closestClusters[1]);
        addCluster(n);
    }

    private void removeClusters(Cluster [] toRemove) {
        Cluster [] newClusterRow = new Cluster[cluster.length-1];
        length-=toRemove.length;
        int placeHolder = 0;
        for (int i = 0; i < cluster.length ; i++) {
            if(toRemove[0]!= cluster[i] && toRemove[1]!=cluster[i] && newClusterRow.length>1){
                newClusterRow[placeHolder] = cluster[i];
                placeHolder++;
            }

        }

        cluster= newClusterRow;
    }

    public Cluster [] getSmallestDistance(ClusterMethod clusterMethod) {
        double minDistance = Double.MAX_VALUE;
        double distance;
        Cluster [] closestClusters = new Cluster[2];
        for (int i = 0; i < cluster.length; i++) {
            for (int j = 0; j < cluster.length; j++) {
                if (cluster[i] != cluster[j]) {
                    distance = clusterMethod.calculateDistance(cluster[i], cluster[j]);
                    if(distance < minDistance && distance!= 0){
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
    public void setCluster(int i, Cluster cluster){
        this.cluster[i]=cluster;
    }
    public int getLength() {
        return length;
    }


}

