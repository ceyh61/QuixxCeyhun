package src.Quixx.Logik;

public class Wuerfel {
    private int punkte;

    public Wuerfel(){

    }

    public void wuerfeln(){
        int ergebnis = (int) (Math.random() * 6)+1;
        punkte=ergebnis;
    }

    public int getPunkte(){
        return this.punkte;
    }
}

