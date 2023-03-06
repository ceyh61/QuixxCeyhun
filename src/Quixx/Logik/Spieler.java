package src.Quixx.Logik;

import java.io.Serializable;

public class Spieler implements Serializable{
    private String name;
    public Spielkarte spielkarte;
    private int punktestand;

    public Spieler(String name) {
        this.name = name;
        this.spielkarte = new Spielkarte();
        this.punktestand = 0;
    }

    public String getName() {
        return name;
    }

    public int punkteHinzufügen(int punkte){
        punktestand+=punkte;
        return punktestand;
    }
    public int getPunktestand() {
        return punktestand;
    }

    public String toString(){
        return getName() + ", Punkte: " + getPunktestand();
    }
}
