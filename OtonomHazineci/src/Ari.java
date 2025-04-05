// ARI SINIFI
public class Ari extends HareketliEngel implements IhareketliEngel {
    private int ileriMaxKareX = 5;
    private int ileriMaxKareY = 0;

    int getIleriMaxKareX() {
        return ileriMaxKareX;
    }

    int getIleriMaxKareY() {
        return ileriMaxKareY;
    }

    @Override
    public void update() {
        if (animationCounter > 6) {
            if (getYon() == 1) {
                setIlerlenenKareX(getIlerlenenKareX() + 1);
            }
            else {
                setIlerlenenKareX(getIlerlenenKareX() - 1);
            }

            if(getIlerlenenKareX() == getIleriMaxKareX()) {
                setYon(0);
            }
            else if(getIlerlenenKareX() == 0) {
                setYon(1);
            }
            animationCounter = 0;
        }
        else {
            animationCounter++;
        }


    }

    // Constructor Metotları
    Ari() {}

    Ari(Location konum) {
        super(konum);
        setImagePath("assets/bee/bee.png");

        // Her Zaman 2x2 olurlar
        setYatay(2);
        setDikey(2);

    }
}
