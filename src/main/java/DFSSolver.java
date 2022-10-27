package main.java;

import java.util.List;
import java.util.Stack;

public class DFSSolver extends Solver{

    private final Stack<City> open;

    /**
     * Creates a new DFS main.java.Solver object and initializes the data
     *
     * @param start The starting city
     * @param goal The destination city
     */
    public DFSSolver(City start, City goal) {
        super(start, goal);
        open = new Stack<>();
        open.push(start);
    }

    /**
     * Implements a DFS algorithm that will attempt to solve a route, and will modify the predecessor map used in
     * getPath if one exists
     */
    public void solve() {
        while (!open.empty()) {
            City curr = open.pop();
            if (curr.equals(goal)) {
                return;
            }
            List<City> children = curr.getSortedNeighbors(new DFSComparator());
            for (City c : children) { // Probably could do this in a cleaner way
                if (c.equals(goal)) {
                    predMap.put(c, curr);
                    return; // Early Goal Test
                }
            }
            for (City c : children) {
                if (!(predMap.containsKey(c) || open.contains(c))) {
                    predMap.put(c, curr);
                    open.push(c);
                }
            }
        }
    }
}
