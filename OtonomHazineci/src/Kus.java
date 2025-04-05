public class Kus extends HareketliEngel implements IhareketliEngel {
    private int ileriMaxKareX = 0;
    private int ileriMaxKareY = 7;
    private int animationNum = 1;

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
                setIlerlenenKareY(getIlerlenenKareY() + 1);
            }
            else {
                setIlerlenenKareY(getIlerlenenKareY() - 1);
            }

            if(getIlerlenenKareY() == getIleriMaxKareY()) {
                setYon(0);
            }
            else if(getIlerlenenKareY() == 0) {
                setYon(1);
            }
            animationCounter = 0;

            if( getYon() == 1) {
                if (animationNum == 1) {
                    setImagePath("assets/bird/bird2.png");
                    animationNum = 2;
                } else if (animationNum == 2) {
                    setImagePath("assets/bird/bird3.png");
                    animationNum = 3;
                } else if (animationNum == 3) {
                    setImagePath("assets/bird/bird4.png");
                    animationNum = 4;
                } else if (animationNum == 4) {
                    setImagePath("assets/bird/bird5.png");
                    animationNum = 5;
                } else if (animationNum == 5) {
                    setImagePath("assets/bird/bird6.png");
                    animationNum = 6;
                } else if (animationNum == 6) {
                    setImagePath("assets/bird/bird7.png");
                    animationNum = 7;
                } else if (animationNum == 7) {
                    setImagePath("assets/bird/bird8.png");
                    animationNum = 8;
                } else if (animationNum == 8) {
                    setImagePath("assets/bird/bird1.png");
                    animationNum = 1;
                }
            } else {
                if (animationNum == 1) {
                    setImagePath("assets/bird/birdMirror2.png");
                    animationNum = 2;
                }
                else if (animationNum == 2) {
                    setImagePath("assets/bird/birdMirror3.png");
                    animationNum = 3;
                }
                else if (animationNum == 3) {
                    setImagePath("assets/bird/birdMirror4.png");
                    animationNum = 4;
                }
                else if (animationNum == 4) {
                    setImagePath("assets/bird/birdMirror5.png");
                    animationNum = 5;
                }
                else if (animationNum == 5) {
                    setImagePath("assets/bird/birdMirror6.png");
                    animationNum = 6;
                }
                else if (animationNum == 6) {
                    setImagePath("assets/bird/birdMirror7.png");
                    animationNum = 7;
                }
                else if (animationNum == 7) {
                    setImagePath("assets/bird/birdMirror8.png");
                    animationNum = 8;
                }
                else if (animationNum == 8) {
                    setImagePath("assets/bird/birdMirror1.png");
                    animationNum = 1;
                }

            }

        }
        else {
            animationCounter++;
        }
    }

    Kus() {}

    Kus(Location konum) {
        super(konum);
        setImagePath("assets/bird/bird1.png");  // GEÇİCİ RESİM

        setYatay(2);
        setDikey(2);

    }
}