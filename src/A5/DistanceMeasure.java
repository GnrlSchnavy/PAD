package A5;

public interface DistanceMeasure {
	
	/**
	 * Calculates the distance between two units.
	 * @param unit1 The first unit from which the distance is calculated.
	 * @param unit2 The second unit to which the distance is calculated.
	 * @return The distance between unit1 and unit2.
	 */
	double calculateDistance(Unit unit1, Unit unit2);

}
