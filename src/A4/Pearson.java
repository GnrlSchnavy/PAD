package A4;

/**
 * Created by Yvan on 17-6-2015.
 */
public class Pearson {

    public double calculateDistance(Unit unit1, Unit unit2){
        int length = Math.min(unit1.getNumberRow().getLength(),50);
        double [] averageValue = new double[2];
        double [] standardDeviation = new double [2];
        averageValue[0] = calculateAverageValues(unit1,length);
        averageValue[1] = calculateAverageValues(unit2, length);
        standardDeviation[0] = caculateStandardDeviation(unit1, averageValue[0], length);
        standardDeviation[1] = caculateStandardDeviation(unit2, averageValue[1], length);
        return calculatePearsonCorrelation(averageValue, standardDeviation, unit1, unit2, length);
    }
    private double calculateAverageValues(Unit unit, int length){
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum =+ unit.getNumberRow().getValues(i);
        }
        return sum/unit.getNumberRow().getLength();
    }
    private double caculateStandardDeviation(Unit unit, Double averageValue, int length){
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Math.pow((unit.getNumberRow().getValues(i) - averageValue),2);
        }
        return Math.sqrt((1.0/(length-1)) * sum);
    }
    private double calculatePearsonCorrelation(double[] averageValue, double[] standardDeviation, Unit unit1, Unit unit2, int length) {
        int total =0;
        for (int i = 0; i <length ; i++) {
            total += ((unit1.getNumberRow().getValues(i) - averageValue[0])/standardDeviation[0]) * ((unit2.getNumberRow().getValues(i) - averageValue[1])/standardDeviation[1]);
        }
        return (1.0/(length-1))*total;
    }
}
