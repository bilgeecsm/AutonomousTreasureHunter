public class Location {
    private int xCoord;
    private int yCoord;
    private String season;
    private boolean engelDurum;
    private Location next;

    // Constructor Metotları
    public Location() {}


    // Engelsiz Düz Nesne
    public Location(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
        this.next = null;
    }

    // Engelsiz Düz Nesne
    public Location(int x, int y, String season) {
        this.xCoord = x;
        this.yCoord = y;
        this.season = season;
        this.next = null;
    }

    // Engele Sahip Nesne
    public Location(int x, int y, boolean engelDurum, String season) {
        this.xCoord = x;
        this.yCoord = y;
        this.engelDurum = engelDurum;
        this.season = season;
        this.next = null;
    }

    public int getYatay() {
        return xCoord;
    }

    public void setYatay(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getDikey() {
        return yCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public boolean getEngelDurum() {
        return engelDurum;
    }

    public void setEngelDurum(boolean engelDurum) {
        this.engelDurum = engelDurum;
    }

    public Location getNext() {
        return next;
    }

    public void setNext(Location next) {
        this.next = next;
    }

    // toString Metodu
    @Override
    public String toString() {
        return "(" + xCoord + ", " + yCoord + ") - " + season;
    }


}