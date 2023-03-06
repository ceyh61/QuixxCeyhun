package src.Quixx.Logik;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Spiel{
    // Anfang Attribute
    private int gemerktePunkte;
    private int punkt;
    private ArrayList<Spieler> spieler;
    private int[] wuerfelErgebnis;
    private String[] farben;
    private String[] farbenKarte;
    private String[] farbenWuerfel;
    private int anzahlWuerfel;
    // Ende Attribute

    public Spiel() {
        this.gemerktePunkte = gemerktePunkte;
        this.punkt = punkt;
        this.spieler = new ArrayList<Spieler>();
        this.farben = new String[]{"rot", "gelb", "grün", "blau"};
        this.farbenKarte = new String[]{"rot", "gelb", "grün", "blau"};
        this.farbenWuerfel = new String[]{"weiß", "weiß", "rot", "gelb", "grün", "blau"};
        this.anzahlWuerfel = 6;
    }

    // Anfang Methoden
    public ArrayList<Spieler> getSpieler() {
        return this.spieler;
    }

    public void addSpieler(Spieler newPlayer) {
        if (this.spieler.contains(newPlayer)) {
            throw new IllegalArgumentException("Dieser Spieler nimmt bereits teil!");
        }

        if (spieler.size()==5) {
            throw new IllegalArgumentException("Es dürfen maximal 5 Spieler teilnehmen!");
        }
        this.spieler.add(newPlayer);
    }

    public String[] getFarben() {
        return farben;
    }

    public String[] getFarbenWuerfel() {
        return farbenWuerfel;
    }

    public int inputInt(int typ){
        Scanner input = new Scanner(System.in);
        int eingabe = 0;
        boolean end = false;
        if (typ == 1) {
            while (end==false) {
                try {
                    eingabe = input.nextInt();
                    while (eingabe>farben.length || eingabe<0) {
                        System.out.println("Zahl zwischen 0 - " + farben.length + " eingeben!");
                        eingabe = input.nextInt();
                    }
                    end = true;
                } catch(java.util.InputMismatchException e) {
                    System.out.println("Zahl eingeben");
                    input.nextLine();
                }
            } // end of while
        }

        if (typ != 1) {
            while (end==false) {
                try {
                    eingabe = input.nextInt();
                    while (eingabe>(farben.length*2) || eingabe<0) {
                        System.out.println("Zahl zwischen 0 - " + (farben.length*2) + " eingeben!");
                        eingabe = input.nextInt();
                    }
                    end = true;
                } catch(java.util.InputMismatchException e) {
                    System.out.println("Zahl eingeben");
                    input.nextLine();
                }
            } // end of while
        } // end of if
        return eingabe;
    }

    public void sechsWuerfeln(Wuerfel wuerfel){
        //1. & 2. Ergebnis weißer Würfel, 3. grün, 4. gelb, 5. blau, 6. rot
        int [] ergebnis = new int[anzahlWuerfel];
        for (int i = 0; i < anzahlWuerfel; i++) {
            wuerfel.wuerfeln();
            ergebnis[i] = wuerfel.getPunkte();
        }
        wuerfelErgebnis = ergebnis;
    }

    public void entferneWuerfel(int farbe){
        anzahlWuerfel-=1;
        String[] copyFarben = new String[anzahlWuerfel-2];
        String[] copyFarbenWuerfel = new String[anzahlWuerfel];
        int j = 0;
        for (int i = 0; i < copyFarben.length; i++) {
            if (i==farbe) {
                j++;
            } // end of if
            copyFarben[i] = farben[j];
            j++;
        }

        farben = copyFarben;

        j = 0;
        farbe+=2;
        for (int i = 0; i < copyFarbenWuerfel.length; i++) {
            if (i==farbe) {
                j++;
            } // end of if
            copyFarbenWuerfel[i] = farbenWuerfel[j];
            j++;
        }

        farbenWuerfel = copyFarbenWuerfel;
    }
    public int[][] getKarte(Spieler player){
        int[][] karte = new int[4][12];
        karte[0] = player.spielkarte.getVerfugbareZahlenRoGe(player.spielkarte.getRot());
        karte[1] = player.spielkarte.getVerfugbareZahlenRoGe(player.spielkarte.getGelb());
        karte[2] = player.spielkarte.getVerfugbareZahlenGrBl(player.spielkarte.getGruen());
        karte[3] = player.spielkarte.getVerfugbareZahlenGrBl(player.spielkarte.getBlau());

        return karte;
    }


    public void getZahlen(Spieler player){

        int[][] karte = getKarte(player);

        for (int i = 0; i < karte.length; i++) {
            System.out.println(farbenKarte[i]);
            for (int j = 0; j < karte[i].length; j++) {
                System.out.print(karte[i][j] + ", " );
            }
            int[] zaehler = player.spielkarte.getZaehler() ;
            System.out.println(" Striche: " + zaehler[i]);
        }
        System.out.println("\nFehlwürfe: " + player.spielkarte.getFehlwuerfe());
    }

    public boolean weissStreichenAktiv(Spieler aktiverSpieler){
        boolean gestrichen = false;
        System.out.println(aktiverSpieler.getName() + " dein Zug!\n");
        int weisseZahlen = wuerfelErgebnis[0]+wuerfelErgebnis[1];

        System.out.println("Deine Würfelergebnisse:");
        for (int i = 0; i < wuerfelErgebnis.length; i++) {
            System.out.print(farbenWuerfel[i] + ": ");
            System.out.println(wuerfelErgebnis[i]);
        }

        System.out.println("\nDeine Spielerkarte:");
        getZahlen(aktiverSpieler);

        String ausgabeStreichen = "\nMöchten Sie mit den weißen Würfelergebnissen eine Zahl in Ihrer Karte streichen?\nDie Zahl " + weisseZahlen + " wird gestrichen!\n0: keine Zahl streichen, ";
        for (int i = 0; i < farben.length; i++) {
            ausgabeStreichen+= (i+1) + ": " + farben[i] + " streichen, ";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println(ausgabeStreichen);
        int eingabe = inputInt(1);

        int[][] karte = getKarte(aktiverSpieler);
        boolean end = false;

        while (end == false) {
            for (int i = 0; i < farben.length; i++) {
                if (eingabe==i+1) {
                    gestrichen = true;
                    if (farben[i]=="rot") {
                        if (weisseZahlen<karte[0][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(1);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlRot(weisseZahlen);
                            end = true;
                        }
                    }
                    if (farben[i]=="gelb") {
                        if (weisseZahlen<karte[1][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(1);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlGelb(weisseZahlen);
                            end = true;
                        }
                    }
                    if (farben[i]=="grün") {
                        if (weisseZahlen>karte[2][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(1);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlGruen(weisseZahlen);
                            end = true;
                        }
                    }
                    if (farben[i]=="blau") {
                        if (weisseZahlen>karte[3][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(1);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlBlau(weisseZahlen);
                            end = true;
                        }
                    }
                } // end of if-else
            }
            if (eingabe == 0) {
                gestrichen = false;
                end = true;
            } // end of if
        }
        return gestrichen;
    }

    public void weissStreichenPassiv(Spieler aktiverSpieler){
        ArrayList<Spieler> spieler = getSpieler();
        int weisseZahlen = wuerfelErgebnis[0]+wuerfelErgebnis[1];
        //Ausgabe
        String ausgabeStreichen = "\nMöchten Sie mit den weißen Würfelergebnissen eine Zahl in Ihrer Karte streichen?\nDie Zahl " + weisseZahlen + " wird gestrichen!\n0: keine Zahl streichen, ";
        for (int i = 0; i < farben.length; i++) {
            ausgabeStreichen+= (i+1) + ": " + farben[i] + " streichen, ";
        }
        //Scanner erstellen für Eingabe
        Scanner scanner = new Scanner(System.in);
        for (Spieler player: spieler) {
            if (player!=aktiverSpieler) {
                System.out.println("\n" + player.getName() + " du agierst nun!\n");
                System.out.println("\nDeine Spielerkarte:");
                getZahlen(player);
                System.out.println(ausgabeStreichen);
                int eingabe = inputInt(1);

                int[][] karte = getKarte(player);
                boolean end = false;

                while (end == false) {
                    for (int i = 0; i < farben.length; i++) {
                        if (eingabe==i+1) {
                            if (farben[i]=="rot") {
                                if (weisseZahlen<karte[0][0]) {
                                    System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                                    eingabe = inputInt(1);
                                } else {
                                    player.spielkarte.streicheZahlRot(weisseZahlen);
                                    end = true;
                                }
                            }
                            if (farben[i]=="gelb") {
                                if (weisseZahlen<karte[1][0]) {
                                    System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                                    eingabe = inputInt(1);
                                } else {
                                    player.spielkarte.streicheZahlGelb(weisseZahlen);
                                    end = true;
                                }
                            }
                            if (farben[i]=="grün") {
                                if (weisseZahlen>karte[2][0]) {
                                    System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                                    eingabe = inputInt(1);
                                } else {
                                    player.spielkarte.streicheZahlGruen(weisseZahlen);
                                    end = true;
                                }
                            }
                            if (farben[i]=="blau") {
                                if (weisseZahlen>karte[3][0]) {
                                    System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                                    eingabe = inputInt(1);
                                } else {
                                    player.spielkarte.streicheZahlBlau(weisseZahlen);
                                    end = true;
                                }
                            }
                        } // end of if-else
                    }
                    if (eingabe == 0) {
                        end = true;
                    }
                }
            }
        }
    }


    public boolean buntStreichenAktiv(Spieler aktiverSpieler){
        boolean gestrichen = false;
        System.out.println(aktiverSpieler.getName() + " nun kannst du mit den bunten Würfeln zahlen streichen!\n");

        System.out.println("\nDeine Spielerkarte:");
        getZahlen(aktiverSpieler);

        int zaehler = 1;
        System.out.println("\nDie Kombinationen:\n\n0: Keine Zahl streichen");
        for (int i = 2; i < farbenWuerfel.length; i++) {
            System.out.println("\n" + farbenWuerfel[i] + ": ");
            int ergebnis1 = wuerfelErgebnis[0] + wuerfelErgebnis[i];
            int ergebnis2 = wuerfelErgebnis[1] + wuerfelErgebnis[i];
            String ausgabe = zaehler + " für: " + ergebnis1;
            String ausgabe2 = zaehler+1 + " für: " + ergebnis2;
            zaehler+=2;

            System.out.println(ausgabe);
            System.out.println(ausgabe2);
        }


        String ausgabeStreichen = "Geben Sie eine Zahl zwischen 0-" + (farben.length*2) + " ein";
        Scanner scanner = new Scanner(System.in);
        System.out.println(ausgabeStreichen);
        int eingabe = inputInt(2);

        int[][] karte = getKarte(aktiverSpieler);
        boolean end = false;

        while (end == false) {
            for (int i = 0; i < (2*farben.length); i++) {
                if (eingabe==i+1) {
                    gestrichen = true;
                    int zahl = wuerfelErgebnis[i%2] + wuerfelErgebnis[(i/2+2)];
                    if (farben[(i/2)]=="rot") {
                        if (zahl<karte[0][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(2);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlRot(zahl);
                            end = true;
                        }
                    }
                    if (farben[(i/2)]=="gelb") {
                        if (zahl<karte[1][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(2);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlGelb(zahl);
                            end = true;
                        }
                    }
                    if (farben[(i/2)]=="grün") {
                        if (zahl>karte[2][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(2);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlGruen(zahl);
                            end = true;
                        }
                    }
                    if (farben[(i/2)]=="blau") {
                        if (zahl>karte[3][0]) {
                            System.out.println("Zahl kann nicht gestrichen werden! Eine andere Eingabe tätigen!");
                            eingabe = inputInt(2);
                        } else {
                            aktiverSpieler.spielkarte.streicheZahlBlau(zahl);
                            end = true;
                        }
                    }
                }
            }
            if (eingabe == 0) {
                gestrichen = false;
                end = true;
            } // end of if
        }
        return gestrichen;
    }

    public void eineRunde(Spieler aktiverSpieler){

        Wuerfel wuerfel = new Wuerfel();
        sechsWuerfeln(wuerfel);
        boolean eins = weissStreichenAktiv(aktiverSpieler);
        checkLock();
        weissStreichenPassiv(aktiverSpieler);
        checkLock();
        boolean zwei = buntStreichenAktiv(aktiverSpieler);
        checkLock();
        if (eins == false && zwei==false) {
            aktiverSpieler.spielkarte.plusFehlwurf();
            System.out.println("\n\nFehlwurf!!\n");
            System.out.println(aktiverSpieler.spielkarte.getFehlwuerfe());
        }
        System.out.println("\nRunde beendet! Ihre aktuellen Spielkarten:\n");
        for (Spieler player: spieler) {
            System.out.println(player.getName() + ":");
            getZahlen(player);
            System.out.println();
        }
    }

    public void checkLock(){
        for (Spieler player: spieler) {
            boolean[] locks = player.spielkarte.getLock();
            for (int i = 0; i < locks.length; i++) {
                if (locks[i]==true) {
                    locks[i]=false;
                    for (int j = 0; j < farben.length; j++) {
                        if (farben[j] == farbenKarte[i]) {
                            entferneWuerfel(j);
                        } // end of if
                    }
                    System.out.println("Würfel entfernt!!");
                }
            }
        }
    }


    public void ausfuehren2(){
        do {
            for (Spieler player: spieler) {
                eineRunde(player);
                if (spielBeendet()==true) {
                    break;
                } // end of if
            }
        } while (spielBeendet()==false); // end of do-while
    }

    public boolean spielBeendet(){
        boolean ende = false;
        for (Spieler player: spieler) {
            if (player.spielkarte.getFehlwuerfe()>=4) {
                ende = true;
            }
            if (anzahlWuerfel<=4) {
                ende = true;
            } // end of if
        }
        return ende;
    }

    public int zaehlePunkte(Spieler player){
        int[] zaehler = player.spielkarte.getZaehler();
        int punkte = 0;
        int[] punktFuerKreuze = {1,3,6,10,15,21,28,36,45,55,66,78};
        for (int i = 0; i < zaehler.length; i++) {
            punkte += punktFuerKreuze[zaehler[i]];
        }
        punkte-=(player.spielkarte.getFehlwuerfe()*5);
        player.punkteHinzufügen(punkte);
        return punkte;
    }

    public Spieler komplettesSpiel(){
        Spieler gewinner = null;
        if (spieler.size()<2) {
            throw new IllegalArgumentException("Es müssen mindestens 2 Spieler teilnehmen!");
        }
        ausfuehren2();
        int meistePunkte = 0;
        for (Spieler player: spieler) {
            int points = zaehlePunkte(player);
            System.out.println(player.getName() + " Punkte: " + points);
            if (points>meistePunkte) {
                gewinner = player;
            }
        }

        System.out.println("\nHURRAAAAAA DER GEWINNER IST: " + gewinner);
        return gewinner;
    }

    public String toString(){
        return punkt + ", " + ", " ;
    }

    public static void main(String[] args) {
        Spiel hihi = new Spiel();


        hihi.addSpieler(new Spieler("Ceyhun"));
        hihi.addSpieler(new Spieler("Arziz"));
        //hihi.addSpieler(new Spieler("Cam/Fenster"));

        //hihi.addSpieler(new Spieler("Ja"));
        //hihi.addSpieler(new Spieler("Man"));
        //hihi.addSpieler(new Spieler("x"));

        hihi.komplettesSpiel();
    }

    // Ende Methoden
}
