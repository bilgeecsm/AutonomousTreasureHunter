import java.util.Random;

// KAYA SINIFLARI
public class Rock extends Engel implements IsabitEngel {
    private int variant;
    private String variantPath;

    // Get-Set Metotları
    // Get Metotları
    int getVariant() {
        return variant;
    }

    String getVariantPath() {
        return variantPath;
    }

    // Set Metotları
    void setVariant(int variant) {
        this.variant = variant;
    }

    void setVariantPath(String variantPath) {
        this.variantPath = variantPath;
    }

    // Constructor Metotları
    Rock() {}

    // Rastgele Boyut Seçen Constructor
    Rock(Location konum) {
        super(konum);

        // Rastgele
        Random randomizer = new Random();
        int size = randomizer.nextInt(2,4);
        setYatay(size);
        setDikey(size);

        int varyant = randomizer.nextInt(1,8);
        setVariant(varyant);

    }

    // Hazır Boyutlu Constructor
    Rock(Location konum, int boyut) {
        super(konum);
        try {
            // Boyut geçerli aralıklarda mı kontrol eden kısım
            if ( (3 >= boyut) && (boyut >= 2)) {
                setYatay(boyut);
                setDikey(boyut);

                Random randomizer = new Random();
                int varyant = randomizer.nextInt(1,8);
                setVariant(varyant);

            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Hatalı aralıkta boyut girildi!");
            System.exit(0);
        }
    }

}

// YAZ SINIFINA AİT KAYA SINIFI
class RockSummer extends Rock {
    // Consturctor Metotları
    RockSummer() {}


    // Rastgele Boyut Seçen Constructor
    RockSummer(Location konum) {
        super(konum);

        switch (getVariant()){
            case 1:
                setVariantPath("assets/rock/summer/colTasi.png");
                break;
            case 2:
                setVariantPath("assets/rock/summer/tas1.png");
                break;
            case 3:
                setVariantPath("assets/rock/summer/tas2.png");
                break;
            case 4:
                setVariantPath("assets/rock/summer/otlutas.png");
                break;
            case 5:
                setVariantPath("assets/rock/summer/tas3.png");
                break;
            case 6:
                setVariantPath("assets/rock/summer/tas4.png");
                break;
            case 7:
                setVariantPath("assets/rock/summer/tas5.png");
                break;
        }

        switch (getYatay()) {
            case 2:
                setImagePath(getVariantPath() + "2.png");
                break;

            case 3:
                setImagePath(getVariantPath() + "3.png");
                break;

        }
    }


    RockSummer(Location konum, int boyut) {
        super(konum, boyut);

        switch (getVariant()){
            case 1:
                setVariantPath("assets/rock/summer/colTasi.png");
                break;
            case 2:
                setVariantPath("assets/rock/summer/tas1.png");
                break;
            case 3:
                setVariantPath("assets/rock/summer/tas2.png");
                break;
            case 4:
                setVariantPath("assets/rock/summer/otlutas.png");
                break;
            case 5:
                setVariantPath("assets/rock/summer/tas3.png");
                break;
            case 6:
                setVariantPath("assets/rock/summer/tas4.png");
                break;
            case 7:
                setVariantPath("assets/rock/summer/tas5.png");
                break;
        }

        switch (getYatay()) {
            case 2:
                setImagePath(getVariantPath() + "2.png");
                break;

            case 3:
                setImagePath(getVariantPath() + "3.png");
                break;

        }
    }

}


// KIŞ SINIFINA AİT KAYA SINIFI
class RockWinter extends Rock {
    // Consturctor Metotları
    RockWinter() {}


    // Rastgele Boyut Seçen Constructor
    RockWinter(Location konum) {
        super(konum);

        switch (getVariant()){
            case 1:
                setVariantPath("assets/rock/winter/kistas8.png");
                break;
            case 2:
                setVariantPath("assets/rock/winter/kistas2.png");
                break;
            case 3:
                setVariantPath("assets/rock/winter/kistas3.png");
                break;
            case 4:
                setVariantPath("assets/rock/winter/kistas4.png");
                break;
            case 5:
                setVariantPath("assets/rock/winter/kistas5.png");
                break;
            case 6:
                setVariantPath("assets/rock/winter/kistas6.png");
                break;
            case 7:
                setVariantPath("assets/rock/winter/kistas7.png");
                break;
        }

        switch (getYatay()) {
            case 2:
                setImagePath(getVariantPath() + "2.png");
                break;

            case 3:
                setImagePath(getVariantPath() + "3.png");
                break;

        }
    }


    // Hazır Boyutlu Constructor
    RockWinter(Location konum, int boyut) {
        super(konum, boyut);

        switch (getVariant()){
            case 1:
                setVariantPath("assets/rock/winter/kistas8.png");
                break;
            case 2:
                setVariantPath("assets/rock/winter/kistas1.png");
                break;
            case 3:
                setVariantPath("assets/rock/winter/kistas3.png");
                break;
            case 4:
                setVariantPath("assets/rock/winter/kistas4.png");
                break;
            case 5:
                setVariantPath("assets/rock/winter/kistas5.png");
                break;
            case 6:
                setVariantPath("assets/rock/winter/kistas6.png");
                break;
            case 7:
                setVariantPath("assets/rock/winter/kistas7.png");
                break;
        }

        switch (getYatay()) {
            case 2:
                setImagePath(getVariantPath() + "2.png");
                break;

            case 3:
                setImagePath(getVariantPath() + "3.png");
                break;

        }
    }

}

