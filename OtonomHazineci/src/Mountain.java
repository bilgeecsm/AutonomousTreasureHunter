
public class Mountain extends Engel implements IsabitEngel {

    // Constructor Metotları
    Mountain() {}

    // Rastgele Boyut Seçen Constructor
    Mountain(Location konum) {
        super(konum);

        // Her Zaman 15x15 olurlar
        setYatay(15);
        setDikey(15);

    }
}

class MountainSummer extends Mountain {
    // Constructor Metotları
    MountainSummer() {}

    MountainSummer(Location konum) {
        super(konum);
        setImagePath("assets/mountain/yazDag1png.png");
    }

}


class MountainWinter extends Mountain {
    MountainWinter() {}

    MountainWinter(Location konum) {
        super(konum);
        setImagePath("assets/mountain/kisDag1.png");
    }

}
