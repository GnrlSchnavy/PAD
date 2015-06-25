package A6;

public class Manhattan implements DistanceMeasure {
    public double calculateDistance(Unit unit1, Unit unit2){
        double distance = 0;
        int length = Math.min(unit1.getNumberRow().getLength(), 50);
        for (int i = 0; i < length; i++) {
            distance += Math.abs(unit1.getNumberRow().getValues(i) - unit2.getNumberRow().getValues(i));
        }
        return distance;
    }
}