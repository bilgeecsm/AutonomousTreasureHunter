import java.awt.*;
import javax.swing.*;

import java.awt.geom.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Game extends JFrame{
    Player karakterNesnesi;
    ArrayList<Engel> engelListesi;
    ArrayList<Engel> sandikListesi;
    ArrayList<HareketliEngel> hareketliEngelListesi;



    Player getKarakterNesnesi() {
        return karakterNesnesi;
    }

    ArrayList<Engel> getEngelListesi() {
        return engelListesi;
    }

    ArrayList<Engel> getSandikListesi() {
        return sandikListesi;
    }

    ArrayList<HareketliEngel> getHareketliEngelListesi() {
        return hareketliEngelListesi;
    }

    void setKarakterNesnesi(Player karakterNesnesi){
        this.karakterNesnesi = karakterNesnesi;
    }

    void setEngelListesi(ArrayList<Engel> engelListesi) {
        this.engelListesi = engelListesi;
    }

    void setSandikListesi(ArrayList<Engel> sandikListesi) {
        this.sandikListesi = sandikListesi;
    }

    void setHareketliEngelListesi(ArrayList<HareketliEngel> hareketliEngelListesi) {
        this.hareketliEngelListesi = hareketliEngelListesi;
    }


    // Constructorlar
    public Game(int yatayKareSayisi, int dikeyKareSayisi,
               Player karakterNesnesi,
               ArrayList<Engel> engelListesi,
               ArrayList<HareketliEngel> dinamikEngelListesi,
               ArrayList<Engel> sandikListesi) {
        // Nesnelerin atanması
        setKarakterNesnesi(karakterNesnesi);
        setEngelListesi(engelListesi);
        setHareketliEngelListesi(dinamikEngelListesi);
        setSandikListesi(sandikListesi);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Otonom Hazine Avcısı");



        GamePanel gamePanel = new GamePanel(yatayKareSayisi, dikeyKareSayisi,
                getKarakterNesnesi(), getEngelListesi(), getHareketliEngelListesi(), getSandikListesi());

        this.add(gamePanel);



        this.pack();



        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

}


// Pencere içeriği
class GamePanel extends JPanel implements Runnable {


    public enum asama{
        GIRIS,
        HARITA_OLUSTURMA,
        OYUN_EKRANI,
        BITIS_EKRANI
    }
    asama oyunAsamasi;

    Font cooper;

    // Oyun Nesneleri
    Player karakter;
    ArrayList<Engel> engelListesi;
    ArrayList<Engel> sandikListesi;
    ArrayList<HareketliEngel> hareketliEngelListesi;


    // Ekran Ayarları
    public final int originalTileSize = 20; // 20x20 Orijinal kare boyutu
    public final int scale = 3; // Oran

    public int tileSize = originalTileSize * scale; // 1 karenin hangi oranda gözükeceği
    final int maxScreenCol = 16; // Ekranda gözükecek dikey kare sayisi
    final int maxScreenRow = 12; // Ekrande gözükecek yatay kare sayısı
    final int screenWidth = tileSize * maxScreenCol; // Ekran Genişliği
    final int screenHeight = tileSize * maxScreenRow; // Ekran yüksekliği

    // Dünya değişkenleri
    int worldYatayKareSayisi;
    int worldDikeyKareSayisi;
    int worldWidth;
    int worldHeight;
    int newWorldWidth;
    int newWorldHeight;

    // Çizgilerin renk nesnesi
    float transparency = 0.4f;
    Color transparentBlack = new Color(0, 0, 0, transparency);

    // Fps
    int FPS = 60;

    // Ayar Değişkenleri
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    private boolean isRunning = false;
    // Sis Ayarları
    int fogSize = 25;  // Kaç Karelik alan görebileceği - 7 orijinal değeri
    Sis fogManager;


    public void setAsama(asama yeniAsama) {
        oyunAsamasi= yeniAsama;
    }



    private void girisAsamasi() {

    }

    private void haritaOlusturmaAsamasi() {
    }

    private void oyunEkraniAsamasi() {
        // Oyun ekranı aşaması işlemleri
    }

    private void bitisEkraniAsamasi() {
        // Bitiş ekranı aşaması işlemleri
    }
    // Karakterin hızı
    public double playerSpeed = tileSize;

    public void asamaKontrol() {
        switch (oyunAsamasi) {
            case GIRIS:
                girisAsamasi();
                break;
            case HARITA_OLUSTURMA:
                haritaOlusturmaAsamasi();
                break;
            case OYUN_EKRANI:
                oyunEkraniAsamasi();
                break;
            case BITIS_EKRANI:
                bitisEkraniAsamasi();
                break;
            default:
                System.out.println("Geçersiz aşama!");
        }
    }

    @Override
    public void run() {
        // zaman değişkenleri
        double drawInternal = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInternal;
            lastTime = currentTime;

            if (delta > 1)
            {
                // 1- Update - Karakter lokasyonları vs.
                update();

                // 2 - Draw - Ekrandaki nesneleri güncellenmiş bilgiyle çiz
                repaint();


                delta--;
            }

        }

    }



    // Güncelleme alınmasını sağlar
    public void update() {
        // Giriş Ekranındayken Çizilecekler
        if (oyunAsamasi.equals(asama.GIRIS)) {
            karakter.update();
        }
        // Harita Oluşturma Ekranındayken Çizilecekler
        else if (oyunAsamasi.equals(asama.HARITA_OLUSTURMA)) {
            karakter.update();
            updateHareketliEngeller();
        }
        // Oyun Oynanma Ekranındayken Çizilecekler
        else if (oyunAsamasi.equals(asama.OYUN_EKRANI)) {
            System.out.println("Oyun Oynanma Durumunda");
            karakter.update();
            updateHareketliEngeller();
        }
        // Bitiş Ekranında Çizilecekler
        else if (oyunAsamasi.equals(asama.BITIS_EKRANI)) {
            System.out.println("Oyun Bitiş Ekranında");
        }
    }

    // Her şeyin çizdirilmesini sağlar
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        switch (oyunAsamasi) {
            case GIRIS:
                girisEkraniCiz(g2);
                break;
            case HARITA_OLUSTURMA:
                olusturmaEkraniCiz(g2);
                break;
            case OYUN_EKRANI:
                System.out.println("Oyun Oynanma Durumunda");
                oyunEkraniCiz(g2);
                break;
            case BITIS_EKRANI:
                System.out.println("Oyun Bitiş Ekranında");
                break;
            default:
                System.out.println("Geçersiz aşama!");
        }

        g2.dispose();
    }

    // GİRİS EKRANI FONKSİYONLARI
    // Giriş Ekranını Çizen Fonksiyon
    public void girisEkraniCiz(Graphics2D g2){
        g2.setFont(cooper);

        // Giris Ekranı Arkaplanı
        Image backgroundImage = new ImageIcon("assets/giriş.jpg").getImage();
        g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);

        // Karakter Çizimi
        karakter.draw(g2,
                screenWidth/2 - 30 + karakter.girisEkranX,
                60 * 5 + karakter.girisEkranY,
                60, 60);

        // Giris Başlığı
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String girisBaslik = "Otonom Hazine Avcısı";
        int x = getXOrtalanmisBaslik(girisBaslik, g2);
        int y = 60 * 3;

        g2.setColor(Color.black);
        g2.drawString(girisBaslik, x + 5, y + 5);


        g2.setColor(Color.WHITE);
        g2.drawString(girisBaslik, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String olusturmaBaslik = "Harita olusturmak için ENTER'e basın";
        x = getXOrtalanmisBaslik(olusturmaBaslik, g2);
        y = 60 * 10;

        g2.setColor(Color.black);
        g2.drawString(olusturmaBaslik, x + 3, y + 3);

        g2.setColor(Color.WHITE);
        g2.drawString(olusturmaBaslik, x, y);


    }

    // Yazının Ekran için ortalanmaış x kordinatını döndürür
    public int getXOrtalanmisBaslik(String text, Graphics2D g2) {
        int uzunluk = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return screenWidth/2 - uzunluk/2;
    }

    public void olusturmaEkraniCiz(Graphics2D g2) {
        g2.setColor(transparentBlack);
        g2.setFont(cooper);

        arkaplanCizdir(g2);

        kareSinirlariCiz(g2);

        karakter.draw(g2);

        sabitEngelleriCizdir(g2);

        hareketliEngelleriCizdir(g2);

        sandiklariCizdir(g2);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String olusturmaBaslik = "Harita Oluşturma Aşaması";
        int x = getXOrtalanmisBaslik(olusturmaBaslik, g2);
        int y = 60 * 2;

        // Giriş Başlığı Gölgesi
        g2.setColor(Color.black);
        g2.drawString(olusturmaBaslik, x + 3, y + 3);

        // Giriş Başlığını Çizme
        g2.setColor(Color.WHITE);
        g2.drawString(olusturmaBaslik, x, y);



        // Oyun Başlatma Bilgisi Başlığı
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        String baslatmaBaslik = "Başlatmak için ENTER'e basın";
        x = getXOrtalanmisBaslik(baslatmaBaslik, g2);
        y = 60 * 10;

        // Oyun Başlatma Bilgisi Başlığı Gölgesi
        g2.setColor(Color.black);
        g2.drawString(baslatmaBaslik, x + 3, y + 3);

        // Oyun Başlatma Bilgisi Başlığını Çizme
        g2.setColor(Color.WHITE);
        g2.drawString(baslatmaBaslik, x, y);

    }

    // OYUN EKRANI FONKSİYONLARI
    // Oyun Ekranını Çizen Fonksiyon
    public void oyunEkraniCiz(Graphics2D g2){
        g2.setColor(transparentBlack);

        // Arkaplanın Çizimi
        arkaplanCizdir(g2);

        // Karelerin Sınırlarının Çizimi
        kareSinirlariCiz(g2);

        // Karakter Çizimi
        karakter.draw(g2);

        // Engellerin Çizimi
        sabitEngelleriCizdir(g2);

        // Dinamik Engellerin Çizimi
        hareketliEngelleriCizdir(g2);

        // Sandıkların Ekrana Çizimi
        sandiklariCizdir(g2);

        // Siz Çizimi
        fogManager = new Sis(this, fogSize);
        fogManager.draw(g2);
    }




    public void kareSinirlariCiz(Graphics2D g2) {
        int cizgiX, cizgiY;

        // Dikey Çizgiler
        for (int xCurrent = 0; xCurrent < worldDikeyKareSayisi * tileSize; xCurrent += tileSize) {
            cizgiX = xCurrent - karakter.getWorldX() + karakter.getScreenX();
            cizgiY = worldDikeyKareSayisi * tileSize - karakter.getWorldY() + karakter.getScreenY();
            g2.drawLine(cizgiX, -karakter.getWorldY() + karakter.getScreenY(), cizgiX, cizgiY);
        }

        // Yatay Çizgiler
        for (int yCurrent = 0; yCurrent < worldYatayKareSayisi * tileSize; yCurrent += tileSize) {
            cizgiX = worldYatayKareSayisi * tileSize - karakter.getWorldX() + karakter.getScreenX();
            cizgiY = yCurrent - karakter.getWorldY() + karakter.getScreenY();
            g2.drawLine(-karakter.getWorldX() + karakter.getScreenX(), cizgiY, cizgiX, cizgiY);
        }

        // Karelerin kenarlarını tamamlayan kısım
        cizgiX = worldDikeyKareSayisi * tileSize - karakter.getWorldX() + karakter.getScreenX();
        cizgiY = worldYatayKareSayisi * tileSize - karakter.getWorldY() + karakter.getScreenY();
        g2.drawLine(cizgiX, -karakter.getWorldY() + karakter.getScreenY(), cizgiX, cizgiY);
        g2.drawLine(-karakter.getWorldX() + karakter.getScreenX(), cizgiY, cizgiX, cizgiY);
    }


    // Arkaplanın çizdirilmesini sağlayan fonksiyon
    public void arkaplanCizdir(Graphics2D g2) {
        // Arkaplan resmi yüklenir
        Image backgroundImage = new ImageIcon("assets/background.png").getImage();

        // Arkaplan resmi karakterin konumuna göre çizdirilir
        g2.drawImage(backgroundImage,
                -karakter.getWorldX() + karakter.getScreenX(),
                -karakter.getWorldY() + karakter.getScreenY(),
                newWorldWidth, newWorldHeight,
                null);
    }


    public void sabitEngelleriCizdir(Graphics2D g2) {
        for (Engel engel : engelListesi) {
            Image engelImage = new ImageIcon(engel.getImagePath()).getImage();
            // Çizileceği Konumların Hesaplaması
            int engelWorldX = engel.getKonum().getYatay() * tileSize - karakter.getWorldX() + karakter.getScreenX();
            int engelWorldY = engel.getKonum().getDikey() * tileSize - karakter.getWorldY() + karakter.getScreenY();

            // Çizim
            g2.drawImage(engelImage,
                    engelWorldX,
                    engelWorldY,
                    engel.getYatay() * tileSize,
                    engel.getDikey() * tileSize,
                    null);
        }
    }

    public void hareketliEngelleriCizdir(Graphics2D g2) {
        // Dinamik Engellerin Çizimi
        for (HareketliEngel engel : hareketliEngelListesi) {

            hareketliEngelGuzergahCizdir(g2, engel);

            Image engelImage = new ImageIcon(engel.getImagePath()).getImage();
            // Çizileceği Konumların Hesaplaması
            int engelWorldX = engel.getIlerlenenKareX() * tileSize + engel.getKonum().getYatay() * tileSize -
                    karakter.getWorldX() + karakter.getScreenX();
            int engelWorldY = engel.getIlerlenenKareY() * tileSize + engel.getKonum().getDikey() * tileSize -
                    karakter.getWorldY() + karakter.getScreenY();

            // Çizim
            g2.drawImage(engelImage,
                    engelWorldX,
                    engelWorldY,
                    engel.getYatay() * tileSize,
                    engel.getDikey() * tileSize,
                    null);
        }
    }

    // Dinamik Engelin Güzergahının Çizdirilmesini Sağlar
    public void hareketliEngelGuzergahCizdir(Graphics2D g2, HareketliEngel engel) {

        // Güzergahın başlangıç konumu
        int guzergahBaslangicX = engel.getKonum().getYatay() * tileSize -
                karakter.getWorldX() + karakter.getScreenX();
        int guzergahBaslangicY = engel.getKonum().getDikey() * tileSize -
                karakter.getWorldY() + karakter.getScreenY();

        // Güzergahın Genişlikleri
        int guzergahWidth = tileSize * engel.getIleriMaxKareX() + engel.getYatay() * tileSize;
        int guzergahHeight = tileSize * engel.getIleriMaxKareY() + engel.getDikey() * tileSize;


        Rectangle2D guzergahArea = new Rectangle2D.Double(guzergahBaslangicX, guzergahBaslangicY,
                guzergahWidth, guzergahHeight);

        // Güzergahın Renginin Ayarlanması
        Color guzergahColor = new Color(1, 0, 0, 0.4f);
        g2.setColor(guzergahColor);

        // Güzergahın Çizdirilmesi
        g2.fill(guzergahArea);

    }

    // Sandıkların çizdirilmesini sağlayan fonksiyon
    public void sandiklariCizdir(Graphics2D g2) {
        for (Engel sandik : sandikListesi) {
            Image sandikImage = new ImageIcon(sandik.getImagePath()).getImage();
            // Çizileceği Konumların Hesaplaması
            int sandikWorldX = sandik.getKonum().getYatay() * tileSize - karakter.getWorldX() + karakter.getScreenX();
            int sandikWorldY = sandik.getKonum().getDikey() * tileSize - karakter.getWorldY() + karakter.getScreenY();

            // Çizim
            g2.drawImage(sandikImage,
                    sandikWorldX,
                    sandikWorldY,
                    sandik.getYatay() * tileSize,
                    sandik.getDikey() * tileSize,
                    null);
        }
    }

    // UPDATE Fonksiyonları
    // Dinamik Engellerin Update Fonksiyonu
    public void updateHareketliEngeller() {
        for(HareketliEngel dinamikEngel : hareketliEngelListesi) {
            dinamikEngel.update();
        }
    }



    public void zoomInOut(int zoomMiktari) {
        int oldWorldWidth = tileSize * worldYatayKareSayisi;
        tileSize += zoomMiktari;
        newWorldWidth = tileSize * worldYatayKareSayisi;
        newWorldHeight = tileSize * worldDikeyKareSayisi;

        double multiplier = (double)newWorldWidth / oldWorldWidth;
        karakter.worldX *= multiplier;
        karakter.worldY *= multiplier;
        playerSpeed *= multiplier;
    }

    public void start() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            isRunning = true;
            gameThread.start();
        }
    }

    public void stop() {
        isRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Consturctor metodu
    public GamePanel(int yatayKareSayisi, int dikeyKareSayisi,
                    Player karakter, ArrayList<Engel> engelListesi,
                    ArrayList<HareketliEngel> dinamikEngelListesi,
                    ArrayList<Engel> sandikListesi) {
        oyunAsamasi=asama.GIRIS;

        // Font Ayarları
        try {
            InputStream is = getClass().getResourceAsStream("Font/cooper.ttf");
            cooper = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Error" + e);
        }


        this.karakter = karakter;
        this.karakter.setGp(this);
        this.karakter.setKeyH(keyH);
        this.karakter.baslangicDegerleriOlustur();  // Kullanılacak Değerlerin atamasını yapan fonksiyon

        // 2 - Sabit Engel Nesnelerinin Atamaları
        this.engelListesi = engelListesi;

        // 3 - Dinamik Engel Nesnelerinin Atamaları
        this.hareketliEngelListesi = dinamikEngelListesi;
        for(HareketliEngel hareketliEngel : this.hareketliEngelListesi) {
            hareketliEngel.setGp(this);
        }


        this.sandikListesi = sandikListesi;

        // 5 - Dünya Nesnesinni Atamaları
        this.worldYatayKareSayisi = yatayKareSayisi;
        this.worldDikeyKareSayisi = dikeyKareSayisi;
        worldWidth = tileSize * worldYatayKareSayisi;
        worldHeight = tileSize * worldDikeyKareSayisi;
        newWorldWidth = worldWidth;
        newWorldHeight = worldHeight;


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // Tuş Dinleyici
        this.setFocusable(true); // Tuş algılamayı sağlar
    }
}