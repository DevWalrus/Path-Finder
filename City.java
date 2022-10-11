import java.util.ArrayList;
import java.util.Collection;

public class City {
    private final String name;
    private final float lgt;
    private final float lat;

    private final ArrayList<City> neighbors;

    public City(String name, float lgt, float lat) {
        this.name = name;
        this.lgt = lgt;
        this.lat = lat;
        neighbors = new ArrayList<>();
    }

    public double distToCity (City o) {
        return Math.sqrt( (lgt-o.lgt)*(lgt-o.lgt) + (lat-o.lat)*(lat-o.lat) ) * 100;
    }

    public void addNeighbor(City o) {
        if (!neighbors.contains(o)) {
            this.neighbors.add(o);
            o.addNeighbor(this);
        }
    }

    public String getName() {
        return name;
    }

    public Collection<City> getNeighbors() {
        return neighbors;
    }
}
