package main.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class City {
    private final String name;
    private final float lgt;
    private final float lat;
    private double distFromStart;
    private final ArrayList<City> neighbors;

    /**
     * Creates a new city object
     *
     * @param name The name of the city
     * @param lgt The longitudinal position of the city
     * @param lat The latitudinal position of the city
     */
    public City(String name, float lgt, float lat) {
        this.name = name;
        this.lgt = lgt;
        this.lat = lat;
        neighbors = new ArrayList<>();
    }

    /**
     * Calculates the straight-line distance from this city to another
     * @param o The other city to calculate the distance to
     * @return The straight-line distance
     */
    public double distToCity (City o) {
        return Math.sqrt( (lgt-o.lgt)*(lgt-o.lgt) + (lat-o.lat)*(lat-o.lat) ) * 100;
    }

    /**
     * Set distance from the starting node, this is set whenever a node is reached and updated whenever another node
     * notices the route it found to the node is shorter than the one already found
     * @param dist The distance to set
     */
    public void setDistFromStart(double dist) {
        distFromStart = dist;
    }

    /**
     * Grabs the current distance from the start node as it was last seen.
     * @return The distance from start
     */
    public double getDistFromStart() {
        return distFromStart;
    }

    /**
     * Adds a neighbor to this adjacency list and the list contained in the other city
     * @param o The city to add
     */
    public void addNeighbor(City o) {
        if (!neighbors.contains(o)) {
            this.neighbors.add(o);
            o.addNeighbor(this);
        }
    }

    /**
     * Gets the name of the city in string form
     * @return The name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of the neighbors as it would be sorted using the comparator, this list is immutable
     * @param sorter The comparator to sort the list by
     * @return The sorted, immutable, list
     */
    public List<City> getSortedNeighbors(Comparator<City> sorter) {
        return neighbors.stream().sorted(sorter).collect(Collectors.toList());
    }

    /**
     * An unsorted list of the neighbors
     * @return The neighbors list
     */
    public Collection<City> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "main.java.City{" +
                "name='" + name + '\'' +
                '}';
    }
}
