package main.java;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Solver {


    protected final HashMap<City, City> predMap;
    protected final City start;
    protected final City goal;

    protected Solver(City start, City goal) {
        this.start = start;
        this.goal = goal;
        predMap = new HashMap<>();
        predMap.put(start, null);
    }

    public ArrayList<City> getPath() {
        ArrayList<City> path = new ArrayList<>();
        City curr = goal;
        if (goal.equals(start)) {   // Same goal & start
            path.add(curr);
            return path;
        }
        if (predMap.get(curr) == null) {    // Route wasn't found
               return null;
        }
        while (curr != null) {  // Assemble Route
            path.add(0, curr);
            curr = predMap.get(curr);
        }
        return path;
    }
}
