import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Proj1 {
    private final String CITY_DAT = "city.dat";
    private final String EDGE_DAT = "edge.dat";

    private final HashMap<String, City> citiesMap;

    private final HashMap<City, Double> distToGoal;

    private final City goal;

    public Proj1 (String start, String end) {
        Scanner cities = null;
        Scanner edges = null;
        citiesMap = new HashMap<>();
        distToGoal = new HashMap<>();

        try {
            cities = new Scanner(new File(CITY_DAT));
        } catch (FileNotFoundException ignored) {
            System.err.println("File not found: city.dat");
            System.exit(0);
        }

        try {
            edges = new Scanner(new File(EDGE_DAT));
        } catch (FileNotFoundException ignored) {
            System.err.println("File not found: edge.dat");
            System.exit(0);
        }

        while (cities.hasNextLine()) {
            String[] line = cities.nextLine().strip().split("[\t| ]+");
            citiesMap.put(line[0], new City(line[0], Float.parseFloat(line[2]), Float.parseFloat(line[3])));
        }

        while (edges.hasNextLine()) {
            String[] line = edges.nextLine().strip().split("[\t| ]+");
            citiesMap.get(line[0]).addNeighbor(citiesMap.get(line[1]));
        }

        goal = citiesMap.get(end);

        for(City c : citiesMap.values()) {
            distToGoal.put(c, c.distToCity(goal));
        }

        System.out.println(distToGoal);

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
        Scanner in = new Scanner(System.in);

        if (args.length != 2) {
            System.err.println("Usage: java Search inputFile outputFile");
            System.exit(0);
        }

        String start;
        String end;

        if (args[0].equals("-")){
            System.out.println("Please input a starting city:");
            start = in.nextLine().strip();
            System.out.println("Please input an ending city:");
            end = in.nextLine().strip();
        } else {
            Scanner route = null;
            try {
                route = new Scanner(new File(args[0]));
            } catch (FileNotFoundException ignored) {
                System.err.println("File not found: " + args[0]);
                System.exit(0);
            }
            start = route.nextLine().strip();
            end = route.nextLine().strip();
        }



        if (end.equals("-")){

        }

        new Proj1(start, end);
    }
}
