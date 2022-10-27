package main.java;

import java.util.Comparator;

public class DFSComparator implements Comparator<City> {

    /**
     * Compares the cities using the reverse lexicographic ordering of the city's names
     * @param o1 the first city to be compared
     * @param o2 the second city to be compared
     * @return the value 0 if the argument string is equal to this string; a value less than 0 if this string is
     *         lexicographically greater than the string argument; and a value greater than 0 if this string is
     *         lexicographically less than the string argument
     */
    @Override
    public int compare(City o1, City o2) {
        return -o1.getName().compareTo(o2.getName());
    }
}
