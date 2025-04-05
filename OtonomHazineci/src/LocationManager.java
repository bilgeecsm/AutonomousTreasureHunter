import java.util.LinkedList;

public class LocationManager extends Location {
    private LinkedList<Location> locations;

    public LocationManager() {
        locations = new LinkedList<>();
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public Location getLocation(int x, int y) {
        for (Location loc : locations) {
            if (loc.getYatay() == x && loc.getDikey() == y) {
                return loc;
            }
        }
        return null;
    }

    public boolean isLocationOccupied(int x, int y) {
        Location location = getLocation(x, y);
        return location != null && location.getEngelDurum();
    }
}