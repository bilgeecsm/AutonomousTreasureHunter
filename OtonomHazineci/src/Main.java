import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner girdiAlici = new Scanner(System.in);

        System.out.print("Yataydaki kare sayısını giriniz: ");
        int yatayKareSayisi = girdiAlici.nextInt();

        System.out.print("Dikeydeki kare sayısını giriniz: ");
        int dikeyKareSayisi = girdiAlici.nextInt();

        int haritaAlan = yatayKareSayisi * dikeyKareSayisi;

        int kisSiniri = yatayKareSayisi / 2 - 1;


        // Random Olayların gerçekleşmesini sağlayan random seed
        randomSeed seedGenerator = new randomSeed();

        // İstenilen Minimum Sabit(sayı) Engel Sayıları
        int minAgacSayi = 5 + ( haritaAlan / 400 );
        int minKayaSayi = 5 + ( haritaAlan / 400 );
        int minDagSayi = 4 + ( haritaAlan / 4000 );
        int minDuvarSayi = 4 + ( haritaAlan / 800 );
        int minKusSayi = 2 + ( haritaAlan / 1600 );
        int minAriSayi = 2 + ( haritaAlan / 1600 );

        // İstenilen Minimum Sandık Sayısı
        int minAltinSandikSayi = 5;
        int minGumusSandikSayi = 5;
        int minZumrutSandikSayi = 5;
        int minBakirSandikSayi = 5;

        Location[][] haritaMatrisi = new Location[yatayKareSayisi][dikeyKareSayisi];

        // Karelerin lokasyonlarını oluşturup hashtable içerisinde saklayan kısım
        for (int yatay = 0; yatay < yatayKareSayisi; yatay++) {
            for (int dikey = 0; dikey < dikeyKareSayisi; dikey++) {
                String mevsim;
                if (yatay <= kisSiniri) {
                    mevsim = "winter";
                } else {
                    mevsim = "summer";
                }
                haritaMatrisi[yatay][dikey] = new Location(yatay, dikey, mevsim);
            }
        }

        ArrayList<Engel> engelListesi = new ArrayList<>();

        ArrayList<HareketliEngel> hareketliEngelListesi = new ArrayList<>();

        ArrayList<Engel> sandikListesi = new ArrayList<>();




        int sabitAgacSayi = 0;
        while (sabitAgacSayi < minAgacSayi) {
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;
                // Engelin atanacağı konum
                Location engelLokasyon = haritaMatrisi[seedX][seedY];

                // Kontrol edilecek konumlar
                Location kontrolLokasyon1 = (seedX + 2 < yatayKareSayisi) ? haritaMatrisi[seedX + 2][seedY] : null;
                Location kontrolLokasyon2 = (seedX + 3 < yatayKareSayisi) ? haritaMatrisi[seedX + 3][seedY] : null;
                Location kontrolLokasyon3 = (seedX + 4 < yatayKareSayisi) ? haritaMatrisi[seedX + 4][seedY] : null;
                Location kontrolLokasyon4 = (seedY + 1 < dikeyKareSayisi) ? haritaMatrisi[seedX][seedY + 1] : null;
                Location kontrolLokasyon5 = (seedY + 2 < dikeyKareSayisi) ? haritaMatrisi[seedX][seedY + 2] : null;
                Location kontrolLokasyon6 = (seedX + 1 < yatayKareSayisi && seedY + 2 < dikeyKareSayisi) ? haritaMatrisi[seedX + 1][seedY + 2] : null;
                Location kontrolLokasyon7 = (seedX + 2 < yatayKareSayisi && seedY + 2 < dikeyKareSayisi) ? haritaMatrisi[seedX + 2][seedY + 2] : null;
                Location kontrolLokasyon8 = (seedX + 3 < yatayKareSayisi && seedY + 2 < dikeyKareSayisi) ? haritaMatrisi[seedX + 3][seedY + 2] : null;
                Location kontrolLokasyon9 = (seedX + 4 < yatayKareSayisi && seedY + 2 < dikeyKareSayisi) ? haritaMatrisi[seedX + 4][seedY + 2] : null;
                Location kontrolLokasyon10 = (seedY + 3 < dikeyKareSayisi) ? haritaMatrisi[seedX][seedY + 3] : null;
                Location kontrolLokasyon11 = (seedY + 4 < dikeyKareSayisi) ? haritaMatrisi[seedX][seedY + 4] : null;
                Location kontrolLokasyon12 = (seedX + 1 < yatayKareSayisi && seedY + 4 < dikeyKareSayisi) ? haritaMatrisi[seedX + 1][seedY + 4] : null;
                Location kontrolLokasyon13 = (seedX + 2 < yatayKareSayisi && seedY + 4 < dikeyKareSayisi) ? haritaMatrisi[seedX + 2][seedY + 4] : null;
                Location kontrolLokasyon14 = (seedX + 3 < yatayKareSayisi && seedY + 4 < dikeyKareSayisi) ? haritaMatrisi[seedX + 3][seedY + 4] : null;
                Location kontrolLokasyon15 = (seedX + 4 < yatayKareSayisi && seedY + 4 < dikeyKareSayisi) ? haritaMatrisi[seedX + 4][seedY + 4] : null;

                // Ağacın oluşabileceği bölgede engel yoksa oluşturulması
                if (engelLokasyon != null && !engelLokasyon.getEngelDurum()
                        && (kontrolLokasyon1 == null || !kontrolLokasyon1.getEngelDurum())
                        && (kontrolLokasyon2 == null || !kontrolLokasyon2.getEngelDurum())
                        && (kontrolLokasyon3 == null || !kontrolLokasyon3.getEngelDurum())
                        && (kontrolLokasyon4 == null || !kontrolLokasyon4.getEngelDurum())
                        && (kontrolLokasyon5 == null || !kontrolLokasyon5.getEngelDurum())
                        && (kontrolLokasyon6 == null || !kontrolLokasyon6.getEngelDurum())
                        && (kontrolLokasyon7 == null || !kontrolLokasyon7.getEngelDurum())
                        && (kontrolLokasyon8 == null || !kontrolLokasyon8.getEngelDurum())
                        && (kontrolLokasyon9 == null || !kontrolLokasyon9.getEngelDurum())
                        && (kontrolLokasyon10 == null || !kontrolLokasyon10.getEngelDurum())
                        && (kontrolLokasyon11 == null || !kontrolLokasyon11.getEngelDurum())
                        && (kontrolLokasyon12 == null || !kontrolLokasyon12.getEngelDurum())
                        && (kontrolLokasyon13 == null || !kontrolLokasyon13.getEngelDurum())
                        && (kontrolLokasyon14 == null || !kontrolLokasyon14.getEngelDurum())
                        && (kontrolLokasyon15 == null || !kontrolLokasyon15.getEngelDurum())) {

                    // Lokasyonun Bölgesini bulan kısım
                    String mevsim = (seedX <= kisSiniri) ? "winter" : "summer";
                    Engel yeniAgac = (mevsim.equals("winter")) ? new TreeWinter(engelLokasyon) : new TreeSummer(engelLokasyon);
                    engelListesi.add(yeniAgac);

                    // Engelin bulunduğu konum ve çevresindeki konumları işaretleme
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            int x = seedX + i;
                            int y = seedY + j;
                            if (x < yatayKareSayisi && y < dikeyKareSayisi) {
                                haritaMatrisi[x][y].setEngelDurum(true);
                            }
                        }
                    }

                    basariliOlusturma = true;
                }
            }

            sabitAgacSayi++;
        }


        for (int sabitKayaSayi = 0; sabitKayaSayi < minKayaSayi; sabitKayaSayi++) {
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Engelin atanacağı konum
                Location engelLokasyon = haritaMatrisi[seedX][seedY];

                // Kontrol edilecek konumlar
                Location kontrolLokasyon1 = null;
                Location kontrolLokasyon2 = null;
                Location kontrolLokasyon3 = null;
                Location kontrolLokasyon4 = null;
                Location kontrolLokasyon5 = null;
                Location kontrolLokasyon6 = null;
                Location kontrolLokasyon7 = null;
                Location kontrolLokasyon8 = null;

                // Kontrol edilecek konumların sınırları dahilinde olup olmadığını kontrol etme
                if (seedX + 2 < yatayKareSayisi && seedY + 2 < dikeyKareSayisi) {
                    kontrolLokasyon1 = haritaMatrisi[seedX + 1][seedY];
                    kontrolLokasyon2 = haritaMatrisi[seedX + 2][seedY];
                    kontrolLokasyon3 = haritaMatrisi[seedX][seedY + 1];
                    kontrolLokasyon4 = haritaMatrisi[seedX + 1][seedY + 1];
                    kontrolLokasyon5 = haritaMatrisi[seedX + 2][seedY + 1];
                    kontrolLokasyon6 = haritaMatrisi[seedX][seedY + 2];
                    kontrolLokasyon7 = haritaMatrisi[seedX + 1][seedY + 2];
                    kontrolLokasyon8 = haritaMatrisi[seedX + 2][seedY + 2];
                }

                // Kayanın oluşabileceği bölgede engel yoksa oluşturulması
                if (engelLokasyon != null && !engelLokasyon.getEngelDurum()
                        && (kontrolLokasyon1 == null || !kontrolLokasyon1.getEngelDurum())
                        && (kontrolLokasyon2 == null || !kontrolLokasyon2.getEngelDurum())
                        && (kontrolLokasyon3 == null || !kontrolLokasyon3.getEngelDurum())
                        && (kontrolLokasyon4 == null || !kontrolLokasyon4.getEngelDurum())
                        && (kontrolLokasyon5 == null || !kontrolLokasyon5.getEngelDurum())
                        && (kontrolLokasyon6 == null || !kontrolLokasyon6.getEngelDurum())
                        && (kontrolLokasyon7 == null || !kontrolLokasyon7.getEngelDurum())
                        && (kontrolLokasyon8 == null || !kontrolLokasyon8.getEngelDurum())) {

                    // Lokasyonun Bölgesini bulan kısım
                    String mevsim = (seedX <= kisSiniri - 3) ? "winter" : "summer";
                    Engel yeniKaya = (mevsim.equals("winter")) ? new RockWinter(engelLokasyon) : new RockSummer(engelLokasyon);
                    engelListesi.add(yeniKaya);

                    // Engelin bulunduğu konum ve çevresindeki konumları işaretleme
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            int x = seedX + i;
                            int y = seedY + j;
                            if (x < yatayKareSayisi && y < dikeyKareSayisi) {
                                haritaMatrisi[x][y].setEngelDurum(true);
                            }
                        }
                    }

                    basariliOlusturma = true;
                }
                sabitKayaSayi++;
            }

        }



        // 1 - 3 Dağ Engellerinin Oluşturulması
        int sabitDagSayi = 0;
        while (sabitDagSayi < minDagSayi) {
            // Dağa uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumların sınırları dahilinde olup olmadığını kontrol etme
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 15; yatay++) {
                    for (int dikey = 0; dikey < 15; dikey++) {
                        int x = seedX + yatay;
                        int y = seedY + dikey;
                        if (x >= yatayKareSayisi || y >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        } else if (haritaMatrisi[x][y].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) break;
                }

                // Dağın oluşabileceği bölgede engel yoksa oluşturulması
                if (lokasyonUygun) {
                    // Lokasyonun Bölgesini bulan kısım
                    String mevsim = (seedX <= kisSiniri - 7) ? "winter" : "summer";
                    Engel yeniDag = (mevsim.equals("winter")) ? new MountainWinter(haritaMatrisi[seedX][seedY]) : new MountainSummer(haritaMatrisi[seedX][seedY]);
                    engelListesi.add(yeniDag);

                    // Dağın bulunduğu bölgeyi engel var olarak ayarlayan kısım
                    for (int yatay = 0; yatay < yeniDag.getYatay(); yatay++) {
                        for (int dikey = 0; dikey < yeniDag.getDikey(); dikey++) {
                            haritaMatrisi[seedX + yatay][seedY + dikey].setEngelDurum(true);
                        }
                    }

                    basariliOlusturma = true;
                }
            }

            sabitDagSayi++;
        }


        // 1 - 4 Duvar Engellerinin Oluşturulması
        int sabitDuvarSayi = 0;
        while (sabitDuvarSayi < minDuvarSayi) {
            // Duvara uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumların sınırları dahilinde olup olmadığını kontrol etme
                boolean lokasyonUygun = true;
                for (int yatay = -1; yatay < 11; yatay++) {
                    for (int dikey = -1; dikey < 2; dikey++) {
                        int x = seedX + yatay;
                        int y = seedY + dikey;
                        if (x < 0 || x >= yatayKareSayisi || y < 0 || y >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        } else if (haritaMatrisi[x][y].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) break;
                }

                for (int dikey = -1; dikey < 11; dikey++) {
                    for (int yatay = -1; yatay < 2; yatay++) {
                        int x = seedX + yatay;
                        int y = seedY + dikey;
                        if (x < 0 || x >= yatayKareSayisi || y < 0 || y >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        } else if (haritaMatrisi[x][y].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) break;
                }

                if (lokasyonUygun) {
                    // Duvarın boyutuna göre duvarı oluştur ve matrise işaretle
                    Engel duvar;
                    if (haritaMatrisi[seedX][seedY].getYatay() == 10) {
                        duvar = new WallHorizontal(haritaMatrisi[seedX][seedY]);
                        for (int i = 0; i < 10; i++) {
                            haritaMatrisi[seedX + i][seedY].setEngelDurum(true);
                        }
                    } else {
                        duvar = new WallVertical(haritaMatrisi[seedX][seedY]);
                        for (int i = 0; i < 10; i++) {
                            haritaMatrisi[seedX][seedY + i].setEngelDurum(true);
                        }
                    }
                    engelListesi.add(duvar);
                    basariliOlusturma = true;
                }
            }
            sabitDuvarSayi++;
        }


        int sabitKusSayi = 0;
        while (sabitKusSayi < minKusSayi) {
            // Kuşa uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 2; yatay++) {
                    for (int dikey = 0; dikey < 9; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                if (lokasyonUygun) {
                    HareketliEngel kus = new Kus(haritaMatrisi[seedX][seedY]);
                    hareketliEngelListesi.add(kus);

                    // Kuşun kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 2; yatay++) {
                        for (int dikey = 0; dikey < 9; dikey++) {
                            haritaMatrisi[seedX + yatay][seedY + dikey].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }
            // Sabit kuş sayısını arttır
            sabitKusSayi++;
        }



        int sabitAriSayi = 0;
        while (sabitAriSayi < minAriSayi) {
            // Arıya uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Arının boyutuna uygun bir konum kontrolü
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 7; yatay++) {
                    for (int dikey = 0; dikey < 2; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa arıyı oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    HareketliEngel ari = new Ari(haritaMatrisi[seedX][seedY]);
                    hareketliEngelListesi.add(ari);

                    // Arının kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 7; yatay++) {
                        for (int dikey = 0; dikey < 2; dikey++) {
                            haritaMatrisi[seedX + yatay][seedY + dikey].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }
            // Sabit arı sayısını arttır
            sabitAriSayi++;
        }


        // Rastgele Sandık Yerleştirme
        // 3 - 1 Altın Sandıkların Oluşturulması
        int sabitAltinSandik = 0;
        while (sabitAltinSandik < minAltinSandikSayi) {
            // Altın Sandığına uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumlar
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 4; yatay++) {
                    for (int dikey = 0; dikey < 4; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa altın sandığı oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    Engel altinSandik = new Altin_sandik(haritaMatrisi[seedX + 1][seedY + 1]);
                    sandikListesi.add(altinSandik);

                    // Sandığın kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 2; yatay++) {
                        for (int dikey = 0; dikey < 2; dikey++) {
                            haritaMatrisi[seedX + yatay + 1][seedY + dikey + 1].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }

            // Sabit altın sandık sayısını arttır
            sabitAltinSandik++;
        }

// Gümüş Sandıkların Oluşturulması
        int sabitGumusSandik = 0;
        while (sabitGumusSandik < minGumusSandikSayi) {
            // Gümüş Sandığına uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumlar
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 4; yatay++) {
                    for (int dikey = 0; dikey < 4; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa gümüş sandığı oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    Engel gumusSandik = new Gumus_sandik(haritaMatrisi[seedX + 1][seedY + 1]);
                    sandikListesi.add(gumusSandik);

                    // Sandığın kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 2; yatay++) {
                        for (int dikey = 0; dikey < 2; dikey++) {
                            haritaMatrisi[seedX + yatay + 1][seedY + dikey + 1].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }

            // Sabit gümüş sandık sayısını arttır
            sabitGumusSandik++;
        }


        // 3 - 3 Zümrüt Sandıkların Oluşturulması
        int sabitZumrutSandik = 0;
        while (sabitZumrutSandik < minZumrutSandikSayi) {
            // Zümrüt Sandığına uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumlar
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 4; yatay++) {
                    for (int dikey = 0; dikey < 4; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa zümrüt sandığı oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    Engel zumrutSandik = new Zumrut_sandik(haritaMatrisi[seedX + 1][seedY + 1]);
                    sandikListesi.add(zumrutSandik);

                    // Sandığın kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 2; yatay++) {
                        for (int dikey = 0; dikey < 2; dikey++) {
                            haritaMatrisi[seedX + yatay + 1][seedY + dikey + 1].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }

            // Sabit zümrüt sandık sayısını arttır
            sabitZumrutSandik++;
        }

// Bakır Sandıkların Oluşturulması
        int sabitBakirSandik = 0;
        while (sabitBakirSandik < minBakirSandikSayi) {
            // Bakır Sandığına uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumlar
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 4; yatay++) {
                    for (int dikey = 0; dikey < 4; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa bakır sandığı oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    Engel bakirSandik = new Bakir_sandik(haritaMatrisi[seedX + 1][seedY + 1]);
                    sandikListesi.add(bakirSandik);

                    // Sandığın kapladığı konumları işaretle
                    for (int yatay = 0; yatay < 2; yatay++) {
                        for (int dikey = 0; dikey < 2; dikey++) {
                            haritaMatrisi[seedX + yatay + 1][seedY + dikey + 1].setEngelDurum(true);
                        }
                    }

                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }

            // Sabit bakır sandık sayısını arttır
            sabitBakirSandik++;
        }


        // Karakter Nesnesinin Oluşturulması
        Player karakter = new Player();
        boolean karakterOlusumu = false;
        while (!karakterOlusumu) {
            // Karaktere uygun oluşturma yeri bulana kadar deneyen kısım
            boolean basariliOlusturma = false;
            while (!basariliOlusturma) {
                int seedX = seedGenerator.randomSeedGenerator() % yatayKareSayisi;
                int seedY = seedGenerator.randomSeedGenerator() % dikeyKareSayisi;

                // Kontrol edilecek konumlar
                boolean lokasyonUygun = true;
                for (int yatay = 0; yatay < 3; yatay++) {
                    for (int dikey = 0; dikey < 3; dikey++) {
                        // Matrisin sınırlarını kontrol et
                        if (seedX + yatay >= yatayKareSayisi || seedY + dikey >= dikeyKareSayisi) {
                            lokasyonUygun = false;
                            break;
                        }
                        // Konumun uygunluğunu kontrol et
                        if (haritaMatrisi[seedX + yatay][seedY + dikey].getEngelDurum()) {
                            lokasyonUygun = false;
                            break;
                        }
                    }
                    if (!lokasyonUygun) {
                        break;
                    }
                }

                // Eğer uygun bir konum bulunduysa karakter oluştur ve matrise işaretle
                if (lokasyonUygun) {
                    karakter = new Player("25", "bilge", haritaMatrisi[seedX + 1][seedY + 1]);
                    haritaMatrisi[seedX + 1][seedY + 1].setEngelDurum(true);
                    // Oluşturma işlemi başarılı oldu
                    basariliOlusturma = true;
                }
            }
            karakterOlusumu = true;
        }



        // Arayüz
        // new MyFrame(yatayKareSayisi, dikeyKareSayisi, karakter, engelListesi);
        new Game(yatayKareSayisi, dikeyKareSayisi, karakter, engelListesi, hareketliEngelListesi, sandikListesi);

        }
    }
