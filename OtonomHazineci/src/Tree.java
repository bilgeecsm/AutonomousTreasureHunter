import java.util.Random;

// AĞAÇ SINIFLARI
public class Tree extends Engel implements IsabitEngel {
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
    Tree() {}

    // Rastgele Boyut Seçen Constructor
    Tree(Location konum) {
        super(konum);

        // Rastgele
        Random randomizer = new Random();
        int boyut = randomizer.nextInt(2,6);
        setYatay(boyut);
        setDikey(boyut);

        int varyant = randomizer.nextInt(1,4);
        setVariant(varyant);

    }

    // Hazır Boyutlu Constructor
    Tree(Location konum, int boyut) {
        super(konum);
        try {
            // Boyut geçerli aralıklarda mı kontrol eden kısım
            if ( (5 >= boyut) && (boyut >= 2)) {
                setYatay(boyut);
                setDikey(boyut);

                Random randomizer = new Random();
                int varyant = randomizer.nextInt(1,4);
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

// YAZ SINIFINA AİT AĞAÇ SINIFI
class TreeSummer extends Tree {
    // Constructor Metotları
    TreeSummer() {}


    // Rastgele Boyut Seçen Constructor
    TreeSummer(Location konum) {
        super(konum);

        switch (getVariant()){
            case 1:
                setImagePath("assets/tree/summerTree.png");
                break;
            case 2:
                setImagePath("assets/tree/summerTree2.png");
                break;
            case 3:
                setImagePath("assets/tree/summerTree3.png");
                break;
        }

    }


    // Hazır Boyutlu Constructor
    TreeSummer(Location konum, int boyut) {
        super(konum, boyut);

        switch (getVariant()){
            case 1:
                setImagePath("assets/tree/summerTree1.png");
                break;
            case 2:
                setImagePath("assets/tree/summer/summerTree2.png");
                break;
            case 3:
                setImagePath("assets/tree/summer/summerTree3.png");
                break;
        }

    }

}


// KIŞ SINIFINA AİT AĞAÇ SINIFI
class TreeWinter extends Tree {
    // Constructor Metotları
    TreeWinter() {}


    // Rastgele Boyut Seçen Constructor
    TreeWinter(Location konum) {
        super(konum);

        switch (getVariant()){
            case 1:
                setImagePath("assets/tree/winterTree1.png");
                break;
            case 2:
                setImagePath("assets/tree/winterTree2.png");
                break;
            case 3:
                setImagePath("assets/tree/winterTree3.png");
                break;
        }
    }


    // Hazır Boyutlu Constructor
    TreeWinter(Location konum, int boyut) {
        super(konum, boyut);

        switch (getVariant()){
            case 1:
                setImagePath("assets/tree/winterTree1.png");
                break;
            case 2:
                setImagePath("assets/tree/winterTree2.png");
                break;
            case 3:
                setImagePath("assets/tree/winterTree3.png");
                break;
        }

    }

}

