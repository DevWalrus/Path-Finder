import java.io.File;
import java.io.FileNotFoundException;
import java.security.Permission;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Proj1 {
    private final String CITY_DAT = "city.dat";
    private final String EDGE_DAT = "edge.dat";

    private HashMap<String, City> citiesMap;

    public Proj1 () {
        Scanner cities = null;
        Scanner edges = null;
        citiesMap = new HashMap<>();

        try {
            cities = new Scanner(new File(CITY_DAT));
        } catch (FileNotFoundException ignored) {
            System.out.println("File not found: city.dat");
            System.exit(1);
        }

        try {
            edges = new Scanner(new File(EDGE_DAT));
        } catch (FileNotFoundException ignored) {
            System.out.println("File not found: edge.dat");
            System.exit(2);
        }

        while (cities.hasNextLine()) {
            String[] line = cities.nextLine().strip().split("[\t| ]+");
            citiesMap.put(line[0], new City(line[0], Float.parseFloat(line[2]), Float.parseFloat(line[3])));
        }

        while (edges.hasNextLine()) {
            String[] line = edges.nextLine().strip().split("[\t| ]+");
            citiesMap.get(line[0]).addNeighbor(citiesMap.get(line[1]));
        }

    }

    public void printAll() {
        for (City c : citiesMap.values()) {
            System.out.println(c.getName());
            for(City o : c.getNeighbors()) {
                System.out.println("\t"+o.getName());
            }
        }
    }

    public static void main(String[] args) {
        new Proj1();
    }
}
