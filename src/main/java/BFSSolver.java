package main.java;

import java.util.ArrayList;

public class BFSSolver extends Solver{

    private final ArrayList<City> frontier;

    /**
     * Creates a new BFS main.java.Solver object and initializes the data
     *
     * @param start The starting city
     * @param goal The destination city
     */
    public BFSSolver(City start, City goal) {
        super(start, goal);
        frontier = new ArrayList<>();
        frontier.add(start);
    }

    /**
     * Implements a BFS algorithm that will attempt to solve a route and returns the shortest path, if possible
     */
    public void solve() {
        if (super.start.equals(super.goal)) {
            return;
        }

        while (!frontier.isEmpty()) {
            City curr = frontier.remove(0);
            for (City c : curr.getSortedNeighbors(new BFSComparator())) {
                if (c.equals(goal)) {
                    predMap.put(c, curr);
                    return; // Early Goal Done
                }
                if (!super.predMap.containsKey(c)) {
                    predMap.put(c, curr);
                    frontier.add(c);
                }
            }
        }
    }


}
