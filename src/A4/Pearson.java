package A4;

/**
 * Created by Yvan on 17-6-2015.
 */
public class Pearson implements DistanceMeasure{

    public double calculateDistance(Unit unit1, Unit unit2){
        int length = Math.min(unit1.getNumberRow().getLength(),50);
        double [] averageValue = new double[2];
        double [] standardDeviation = new double [2];
        averageValue[0] = calculateAverageValues(unit1,length);
        averageValue[1] = calculateAverageValues(unit2, length);
        standardDeviation[0] = calculateStandardDeviation(unit1, averageValue[0], length);
        standardDeviation[1] = calculateStandardDeviation(unit2, averageValue[1], length);
        return 1-calculatePearsonCorrelation(averageValue, standardDeviation, unit1, unit2, length);
    }
    private double calculateAverageValues(Unit unit, int length){
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += unit.getNumberRow().getValues(i);
        }
        return sum/length;
    }
    private double calculateStandardDeviation(Unit unit, Double averageValue, int length){
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Math.pow((unit.getNumberRow().getValues(i) - averageValue),2);
        }
        return Math.sqrt(sum/(length-1));
    }
    private double calculatePearsonCorrelation(double[] averageValue, double[] standardDeviation, Unit unit1, Unit unit2, int length) {
        double total =0;
        for (int i = 0; i <length ; i++) {
            total += ((unit1.getNumberRow().getValues(i) - averageValue[0])/standardDeviation[0]) * ((unit2.getNumberRow().getValues(i) - averageValue[1])/standardDeviation[1]);
        }
        return (1.0/(length-1))*total;
    }


}
