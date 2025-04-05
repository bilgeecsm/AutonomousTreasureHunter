import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
            System.out.println("W pressed");
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
            System.out.println("S pressed");

        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            System.out.println("A pressed");

        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
            System.out.println("D pressed");

        }

        // Zoom Tuşları
        if (code == KeyEvent.VK_X || code == KeyEvent.VK_Z) {
            if (code == KeyEvent.VK_X) {
                gp.zoomInOut(1);
            } else if (code == KeyEvent.VK_Z) {
                gp.zoomInOut(-1);
            }
        }



        // Oyun Haritası Oluşturma Tuşu
        if(code == KeyEvent.VK_ENTER && gp.oyunAsamasi.equals(GamePanel.asama.GIRIS)){
            gp.oyunAsamasi = GamePanel.asama.HARITA_OLUSTURMA;
            gp.fogSize = 9999;
            gp.karakter.baslangicDegerleriOlustur();
            gp.start();
            gp.repaint();

        }

        else if(code == KeyEvent.VK_ENTER && gp.oyunAsamasi.equals(GamePanel.asama.HARITA_OLUSTURMA)){
            gp.oyunAsamasi = GamePanel.asama.OYUN_EKRANI;
            gp.fogSize = 7;
            gp.karakter.baslangicDegerleriOlustur();
            gp.start();
            gp.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }

    }
}
