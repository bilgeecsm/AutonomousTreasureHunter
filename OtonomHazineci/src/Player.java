import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String charImagePath = "assets/character/player1.png";
    private String id;
    private String ad;
    private Location konum;
    private ArrayList<Location> ilerlenenKonumlar = new ArrayList<>();
    private GamePanel gp;
    private KeyHandler keyH;
    double worldX;
    double worldY;
    double screenX;
    double screenY;

    int girisEkranX = 0;
    int girisEkranY = 0;

    // Animasyon Değişkenleri
    int animationCounter = 0;
    int animationNum = 1;

    // Get-Set Metotları
    // Get Metotları
    String getCharImagePath() {
        return charImagePath;
    }

    String getAd() {
        return ad;
    }

    String getId() {
        return id;
    }

    Location getKonum() {
        return konum;
    }

    ArrayList<Location> getIlerlenenKonumlar() {
        return ilerlenenKonumlar;
    }

    GamePanel getGp() {
        return gp;
    }

    KeyHandler getKeyH() {
        return keyH;
    }

    int getWorldX() {
        return (int)worldX;
    }

    int getWorldY() {
        return (int)worldY;
    }

    int getScreenX() {
        return (int)screenX;
    }

    int getScreenY() {
        return (int)screenY;
    }

    void setCharImagePath(String charImagePath) {
        this.charImagePath = charImagePath;
    }

    void setAd(String ad) {
        this.ad = ad;
    }

    void setId(String id) {
        this.id = id;
    }

    void setKonum(Location konum) {
        this.konum = konum;
    }

    void setIlerlenenKonumlar(ArrayList<Location> ilerlenenKonumlar) {
        this.ilerlenenKonumlar = ilerlenenKonumlar;
    }

    void setGp(GamePanel gp){
        this.gp = gp;
    }

    void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    Location enKisaYol() {
        return konum; // GİRİLEN OBJELER/OBJE ARASINDAKİ EN KISA YOLU DÖNDÜRECEK
    }

    public void baslangicDegerleriOlustur() {
        screenX = getGp().screenWidth / 2 - (getGp().tileSize / 2);
        screenY = getGp().screenHeight / 2 - (getGp().tileSize / 2);


        worldX = getKonum().getYatay() * getGp().tileSize;
        worldY = getKonum().getDikey() * getGp().tileSize;

        getGp().playerSpeed = getGp().tileSize;
    }


    public void update() {
        if (getGp().oyunAsamasi.equals(GamePanel.asama.GIRIS)) {
            if(keyH.upPressed) {
                if(girisEkranY > -90)
                    girisEkranY -= 10;
            }
            else if(keyH.downPressed) {
                if(girisEkranY < 210)
                    girisEkranY += 10;
            }
            else if(keyH.leftPressed) {
                if(girisEkranX > -360)
                    girisEkranX -= 10;
            }
            else if(keyH.rightPressed) {
                if(girisEkranX < 290)
                    girisEkranX += 10;
            }
        }

        if(keyH.upPressed) {
            worldY -= gp.playerSpeed;
        }
        else if(keyH.downPressed) {
            worldY += gp.playerSpeed;
        }
        else if(keyH.leftPressed) {
            worldX -= gp.playerSpeed;
        }
        else if(keyH.rightPressed) {
            worldX += gp.playerSpeed;
        }

        animationCounter++;
        if (animationCounter > 6) {
            if (animationNum == 1) {
                animationNum = 2;
            }
            else if (animationNum == 2) {
                animationNum = 3;
            }
            else if (animationNum == 3) {
                animationNum = 4;
            }
            else if (animationNum == 4) {
                animationNum = 5;
            }
            else if (animationNum == 5) {
                animationNum = 6;
            }
            else if (animationNum == 6) {
                animationNum = 1;
            }
            animationCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        // Animasyon
        if (animationNum == 1) {
            setCharImagePath("assets/character/player1.png");
        }
        else if (animationNum == 2) {
            setCharImagePath("assets/character/player2.png");
        }
        else if (animationNum == 3) {
            setCharImagePath("assets/character/player3.png");
        }
        else if (animationNum == 4) {
            setCharImagePath("assets/character/player4.png");
        }

        Image karakterImage = new ImageIcon(getCharImagePath()).getImage();
        g2.drawImage(karakterImage, getScreenX(), getScreenY(), gp.tileSize, gp.tileSize, null);

    }

    public void draw(Graphics2D g2, int xCord, int yCord, int width, int height) {
        // Animasyon
        if (animationNum == 1) {
            setCharImagePath("assets/character/player1.png");
        }
        else if (animationNum == 2) {
            setCharImagePath("assets/character/player2.png");
        }
        else if (animationNum == 3) {
            setCharImagePath("assets/character/player3.png");
        }
        else if (animationNum == 4) {
            setCharImagePath("assets/character/player4.png");
        }


        // Karakter Çizimi
        Image karakterImage = new ImageIcon(getCharImagePath()).getImage();
        g2.drawImage(karakterImage, xCord, yCord, width, height, null);
    }


    Player() {}

    // Konum Bilgisi olmadan constructor metodu
    Player(String id, String ad){
        setId(id);
        setAd(ad);
    }

    // Hazır konum bilgisiyle constructor metodu
    Player(String id, String ad, Location konum){
        setId(id);
        setAd(ad);
        setKonum(konum);
    }

}
