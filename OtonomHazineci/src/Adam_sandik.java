public class Adam_sandik extends Engel implements IsabitEngel {
    protected boolean bulunmaDurum = false;
    boolean getBulunmaDurum() {
        return bulunmaDurum;
    }

    void setBulunmaDurum(boolean bulunmaDurum) {
        this.bulunmaDurum = bulunmaDurum;
    }
    Adam_sandik() {}

    Adam_sandik(Location konum) {
        super(konum);
        setYatay(2);
        setDikey(2);

    }
}

class Altin_sandik extends Adam_sandik {
    // Constructor Metotları
    Altin_sandik() {}

    Altin_sandik(Location konum) {
        super(konum);
        setImagePath("assets/chest/chestGold.png");
    }
}

class Gumus_sandik extends Adam_sandik {
    Gumus_sandik() {}

    Gumus_sandik(Location konum) {
        super(konum);
        setImagePath("assets/chest/chestSilver.png");
    }
}

class Zumrut_sandik extends Adam_sandik {
    Zumrut_sandik() {}

    Zumrut_sandik(Location konum) {
        super(konum);
        setImagePath("assets/chest/chestEmerald.png");
    }
}

class Bakir_sandik extends Adam_sandik {
    Bakir_sandik() {}

    Bakir_sandik(Location konum) {
        super(konum);
        setImagePath("assets/chest/chestCopper.png");
    }
}
