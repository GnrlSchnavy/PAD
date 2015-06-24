package A6;

public interface Cluster {
	
	/**
	 * Returns the depth of the cluster.
	 * @return the depth of the cluster.
	 */
	int getDepth();
	
	/**
	 * Returns the width of the cluster.
	 * @return the width of the cluster.
	 */
	int getWidth();
	
	/**
	 * Returns all units contained in this cluster.
	 * @return all units contained in this cluster.
	 */
	UnitRow getUnits();
	
	/**
	 * Returns whether this cluster has children. 
	 * I.e. is this a node or a leaf?
	 * @return 	true: if this cluster has children. <br>
	 * 			false: if this cluster has no children.
	 */
	boolean hasChildren();
}
