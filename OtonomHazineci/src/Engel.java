interface IsabitEngel {

}

interface IhareketliEngel {

}

public class Engel {

    protected Location konum;
    protected int yatay;
    protected int dikey;
    protected String ImagePath;

    Location getKonum() {
        return konum;
    }

    int getYatay() {return yatay;}

    int getDikey() {
        return dikey;
    }

    String getImagePath() {
        return ImagePath;
    }

    void setKonum(Location konum) {
        this.konum = konum;
        this.konum.setEngelDurum(true);
    }

    void setYatay(int yatay) {this.yatay = yatay;}

    void setDikey(int dikey) {
        this.dikey = dikey;
    }

    void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public Engel() {}

    // Hazır Lokasyon kullanan construct metodu
    public Engel(Location konumGirdi) {
        setKonum(konumGirdi);
    }
}

class HareketliEngel extends Engel {
    // Gui Değişkenleri
    private GamePanel gp;
    private int yon = 1;
    private int ilerlenenKareX = 0;
    private int ilerlenenKareY = 0;
    public int animationCounter = 0;

    private int ileriMaxKareX;
    private int ileriMaxKareY;


    public void update() {
    }

    // İlerledikleri yolun bilgisini döndüren metot
    public int guzergahBitis() {
        return 0;
    }

    GamePanel getGp() {
        return gp;
    }

    int getYon() {
        return yon;
    }

    int getIlerlenenKareX() {
        return ilerlenenKareX;
    }

    int getIlerlenenKareY() {
        return ilerlenenKareY;
    }

    int getIleriMaxKareX() {
        return ileriMaxKareX;
    }

    int getIleriMaxKareY() {
        return ileriMaxKareY;
    }

    // Set Metotları
    void setGp(GamePanel gp){
        this.gp = gp;
    }

    void setYon(int yon) {
        this.yon = yon;
    }

    void setIlerlenenKareX(int ilerlenenKareX) {
        this.ilerlenenKareX = ilerlenenKareX;
    }

    void setIlerlenenKareY(int ilerlenenKareY) {
        this.ilerlenenKareY = ilerlenenKareY;
    }

    void setIleriMaxKareX(int ileriMaxKareX) {
        this.ileriMaxKareX = ileriMaxKareX;
    }

    void setIleriMaxKareY(int ileriMaxKareY) {
        this.ileriMaxKareY = ileriMaxKareY;
    }


    HareketliEngel() {}

    HareketliEngel(Location konum) {
        super(konum);
    }
}
