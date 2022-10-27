package main.java;

import java.util.HashMap;
import java.util.TreeSet;

public class AStarSolver extends Solver{

    private final TreeSet<City> open;
    private final HashMap<City, Double> heuristic;

    /**
     * Creates a new A* main.java.Solver object and initializes the data
     *
     * @param start The starting city
     * @param goal The destination city
     * @param heuristic A map of the cities to their distances (to goal)
     */
    public AStarSolver(City start, City goal, HashMap<City, Double> heuristic) {
        super(start, goal);
        this.heuristic = heuristic;
        open = new TreeSet<>(new AStarComparator(heuristic));
        open.add(start);
    }

    /**
     * Implements the A* algorithm using the straight-line distance as the heuristic
     */
    public void solve(){
        City curr = open.first();
        open.remove(curr);
        while (curr != null) {
            if (curr.equals(goal)) {
                return;
            }
            for (City c : curr.getNeighbors()) {
                if ((!open.contains(c) || c.getDistFromStart() > heuristic.get(c) + curr.getDistFromStart())
                        && !predMap.containsKey(c)) {
                    open.remove(c);
                    c.setDistFromStart(heuristic.get(c) + curr.getDistFromStart());
                    predMap.put(c, curr);
                    open.add(c);
                }
            }
            curr = open.first();
            open.remove(curr);
        }
    }

}
