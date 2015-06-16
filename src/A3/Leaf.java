package A3;

/**
 * Created by Yvan on 8-6-2015.
 */
public class Leaf implements Cluster {
    public int getDepth() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public UnitRow getUnits() {
        return null;
    }

    public boolean hasChildren() {
        return false;
    }
}
