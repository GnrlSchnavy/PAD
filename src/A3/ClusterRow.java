package A3;

public class ClusterRow {

    int length =0;
    Cluster [] cluster;

    ClusterRow(Dataset dataSet){
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
    public Cluster getCluster(int i){
        return cluster[i];
    }
    public void setCluster(Cluster[] cluster){
        this.cluster = cluster;
    }
    public void setCluster(int i,Cluster cluster){
        this.cluster[i]=cluster;
    }
    public int getLength() {
        return length;
    }
}

