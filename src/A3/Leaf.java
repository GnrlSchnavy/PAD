package A3;

/**
 * Created by Yvan on 8-6-2015.
 */
public class Leaf implements Cluster {
    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public UnitRow getUnits() {
        return null;
    }

    @Override
    public boolean hasChildren() {
        return false;
    }
}
