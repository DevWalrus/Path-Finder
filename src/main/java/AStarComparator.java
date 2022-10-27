package main.java;

import java.util.Comparator;
import java.util.HashMap;

public class AStarComparator implements Comparator<City> {

    private final HashMap<City, Double> heuristic;

    /**
     * Initializes a new comparator to be used in the A* solver. This takes in the heuristic to be used by the
     * comparator
     * @param heuristic A map of the cities to their distances (to goal)
     */
    public AStarComparator(HashMap<City, Double> heuristic) {
        this.heuristic = heuristic;
    }

    /**
     * Compares the cities using the sum of the distance from the start provided internally and the heuristic value
     * @param o1 the first city to be compared.
     * @param o2 the second city to be compared.
     * @return A positive value if the value of {@code o1.getDistFromStart() + heuristic.get(o1)} is greater than
     *         {@code o2.getDistFromStart() + heuristic.get(o2)}
     */
    @Override
    public int compare(City o1, City o2) {
        return (int) ((o1.getDistFromStart() + heuristic.get(o1)) - (o2.getDistFromStart() + heuristic.get(o2)));
    }
}
