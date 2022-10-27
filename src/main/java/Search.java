package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * A basic route finding program using BFS, DFS, and A*.
 */
public class Search {
    private final String CITY_DAT = "./data/city.dat";
    private final String EDGE_DAT = "./data/edge.dat";

    private final HashMap<String, City> citiesMap;

    private final HashMap<City, Double> heuristic;

    private final City start;
    private final City goal;

    private final FileWriter writer;

    /**
     * Initializes the main object that will solve the routes.
     *
     * @param start The name of the starting city
     * @param goal The name of the ending city
     * @param writer The FileWriter to be used for output or null if stdout should be used
     */
    public Search(String start, String goal, FileWriter writer) {
        Scanner cities = null;
        Scanner edges = null;
        citiesMap = new HashMap<>();
        heuristic = new HashMap<>();
        this.writer = writer;

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
            citiesMap.put(line[0],
                    new City(line[0], Float.parseFloat(line[2]), Float.parseFloat(line[3])));
        }

        while (edges.hasNextLine()) {
            String[] line = edges.nextLine().strip().split("[\t| ]+");
            citiesMap.get(line[0]).addNeighbor(citiesMap.get(line[1]));
        }

        this.start = citiesMap.get(start);
        this.goal = citiesMap.get(goal);
    }

    /**
     * The method that finds the 3 routes and outputs the results to the desired output file or stdout
     *
     * @throws IOException When file was not able to be written to
     */
    public void execute() throws IOException {
        for(City c : citiesMap.values()) {
            heuristic.put(c, c.distToCity(goal));
        }

        BFSSolver bfs = new BFSSolver(start, goal);
        bfs.solve();
        printSolution("Breadth-First", bfs.getPath());

        DFSSolver dfs = new DFSSolver(start, goal);
        dfs.solve();
        printSolution("Depth-First", dfs.getPath());

        AStarSolver astar = new AStarSolver(start, goal, heuristic);
        astar.solve();
        printSolution("A*", astar.getPath());
    }

    private void printSolution(String s, ArrayList<City> cities) throws IOException {
        println();
        println(s + " main.java.Search Results: ");
        if (cities == null) {
            println("No result found!");
            return;
        }
        double dist = 0;
        int hops = cities.size() - 1;
        City prev = cities.get(0);
        for (City c : cities) {
            println(c.getName());
            dist += prev.distToCity(c);
            prev = c;
        }
        println("That took " + hops + " hops to find.\nTotal distance = " + (int) Math.ceil(dist) + " miles.");
        println();
    }

    private void print(String s) throws IOException {
        if (writer != null) {
            writer.write(s);
        } else {
            System.out.print(s);
        }
    }

    private void println(String s) throws IOException {
        print(s + "\n");
    }

    private void println() throws IOException {
        print("\n");
    }

    /**
     * A program that takes in 2 cities and finds the route using the following searching algorithms: Breath-First
     * main.java.Search, Depth-First main.java.Search, A* main.java.Search.
     *
     * @param args The input and output files for the program. The input file should be either in the format
     *             "City1\nCity2" or should be a "-" to denote you will enter it on the command line. The program will
     *             prompt you when to do so. The output file should be either a file able to be opened or should be a
     *             "-" to denote the output should be sent stdout.
     * @throws IOException When file was not able to be either created or written to
     */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);


        if (args.length != 2) {
            System.err.println("Usage: java main.java.Search inputFile outputFile");
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


        FileWriter writer;
        if (args[1].equals("-")){
            writer = null;
        } else {
            writer = new FileWriter(args[1]);
        }

        Search p = new Search(start, end, writer);
        p.execute();
        if (!args[1].equals("-")) {
            writer.flush();
            writer.close();
        }
    }
}
