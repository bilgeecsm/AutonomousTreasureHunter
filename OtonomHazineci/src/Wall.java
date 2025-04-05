import java.util.Random;

// DUVAR SINIFLARI
public class Wall extends Engel implements IsabitEngel {
    private int variant;

    // Get-Set Metotları
    // Get Metotları
    int getVariant() {
        return variant;
    }

    // Set Metotları
    void setVariant(int variant) {
        this.variant = variant;
    }

    // Constructor Metotları
    Wall() {}

    Wall(Location konum) {
        super(konum);

        // Rastgele
        Random randomizer = new Random();
        setVariant(randomizer.nextInt(0,2)); // 1 ise dikey, 0 ise yatay
        if (getVariant() == 1){
            setYatay(1);
            setDikey(10);

            setImagePath("textures/wall/WallDikey.png");
        }
        else {
            setYatay(10);
            setDikey(1);

            setImagePath("textures/wall/WallYatay.png");
        }

    }
    public class WallHorizontal extends Engel {
        public WallHorizontal(Location konum) {
            super(konum);

        }
    }

    public class WallVertical extends Engel {
        public WallVertical(Location konum) {
            super(konum);
        }
    }


}
