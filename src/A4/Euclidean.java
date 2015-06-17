package A4;

public class Euclidean implements DistanceMeasure {

    /**
     * Created by Yvan on 17-6-2015.
     */

    public double calculateDistance(Unit unit1, Unit unit2){
        int length = Math.min(unit1.getNumberRow().getLength(), 50);
        double distance = 0;
        for (int i = 0; i < length; i++) {
            distance += Math.pow(unit1.getNumberRow().getValues(i) - unit2.getNumberRow().getValues(i),2);
        }
        return Math.sqrt(distance);
    }
}
