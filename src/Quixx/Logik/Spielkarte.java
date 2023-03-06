package src.Quixx.Logik;

public class Spielkarte{
    // Anfang Attribute
    private int fehlwuerfe;
    private int[] gruen;
    private int[] rot;
    private int[] gelb;
    private int[] blau;
    private int[] vglroge = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private int[] vglgrbl = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private String[] farben = {"rot", "gelb", "grün", "blau"};
    private boolean[] lock;
    private int[] zaehler;
    // Ende Attribute

    public Spielkarte() {
        this.fehlwuerfe = 0;
        this.gruen = new int[11];
        this.rot = new int[11];
        this.gelb = new int[11];
        this.blau = new int[11];
        this.lock = new boolean[] {false, false, false, false};
        this.zaehler = new int[]{0,0,0,0};
    }



    // Anfang Methoden
    public int getFehlwuerfe() {
        return fehlwuerfe;
    }

    public void setFehlwuerfe(int fehlwuerfeNeu) {
        fehlwuerfe = fehlwuerfeNeu;
    }

    public int[] getGruen() {
        return gruen;
    }

    public int[] getRot() {
        return rot;
    }

    public int[] getGelb() {
        return gelb;
    }

    public int[] getBlau() {
        return blau;
    }


    public int[] getVerfugbareZahlenGrBl(int[] eingabe) {
        int index = 0;
        for (int i = 0; i < vglgrbl.length; i++) {
            if (eingabe[i] == 1) {
                index = i+1;
            } // end of if
        }

        int[] ausgabe = new int[11-index];
        for (int i = 0; i < (vglroge.length-index); i++) {
            ausgabe[i] = vglgrbl[index+i];
        }


        return ausgabe;
    }


    public int[] getVerfugbareZahlenRoGe(int[] eingabe) {
        int index = 0;
        for (int i = 0; i < vglroge.length; i++) {
            if (eingabe[i] == 1) {
                index = i+1;
            } // end of if
        }

        int[] ausgabe = new int[11-index];
        for (int i = 0; i < (vglroge.length-index); i++) {
            ausgabe[i] = vglroge[index+i];
        }


        return ausgabe;
    }

    public void setGruen(int[] gruenNeu) {
        gruen = gruenNeu;
    }

    public void setRot(int[] rotNeu) {
        rot = rotNeu;
    }

    public void setGelb(int[] gelbNeu) {
        gelb = gelbNeu;
    }

    public void setBlau(int[] blauNeu) {
        blau = blauNeu;
    }

    public void streicheZahlRot(int zahl) {
        int[] xy = getRot();
        int farbenIndex = 0;
        for (int j = 0; j < farben.length; j++) {
            if (farben[j]=="rot") {
                farbenIndex = j;
            }
        }


        if (1 < zahl && zahl < 13) {
            int index = 0;
            int zaehlerNeu = 0;
            if (zahl == vglroge[vglroge.length-1]) {
                if (zaehler[farbenIndex]>=5) {
                    xy[vglroge.length-1] +=1;
                    setRot(xy);
                    lock[farbenIndex]=true;
                    zaehlerNeu = zaehler[farbenIndex]+2;
                }
            } else {
                for (int i = 0; i < vglroge.length; i++) {
                    if (zahl == vglroge[i]) {
                        xy[i] +=1;
                        setRot(xy);
                    }

                    if (xy[i]==1) {
                        zaehlerNeu++;
                    }
                }
            }
            zaehler[farbenIndex] = zaehlerNeu;
        }
    }


    public void streicheZahlGelb(int zahl) {
        int[] xy = getGelb();
        int farbenIndex = 0;
        for (int j = 0; j < farben.length; j++) {
            if (farben[j]=="gelb") {
                farbenIndex = j;
            }
        }

        if (1 < zahl && zahl < 13) {
            int index = 0;
            int zaehlerNeu = 0;
            if (zahl == vglroge[vglroge.length-1]) {
                if (zaehler[farbenIndex]>=5) {
                    xy[vglroge.length-1] +=1;
                    setGelb(xy);
                    lock[farbenIndex]=true;
                    zaehlerNeu = zaehler[farbenIndex]+2;
                }
            } else {
                for (int i = 0; i < vglroge.length; i++) {
                    if (zahl == vglroge[i]) {
                        xy[i] +=1;
                        setGelb(xy);
                    }

                    if (xy[i]==1) {
                        zaehlerNeu++;
                    }
                }
            }
            zaehler[farbenIndex] = zaehlerNeu;
        }
    }

    public void streicheZahlGruen(int zahl) {
        int[] xy = getGruen();
        int farbenIndex = 0;
        for (int j = 0; j < farben.length; j++) {
            if (farben[j]=="grün") {
                farbenIndex = j;
            }
        }


        if (1 < zahl && zahl < 13) {
            int index = 0;
            int zaehlerNeu = 0;
            if (zahl == vglgrbl[vglgrbl.length-1]) {
                if (zaehler[farbenIndex]>=5) {
                    xy[vglgrbl.length-1] +=1;
                    setGruen(xy);
                    lock[farbenIndex]=true;
                    zaehlerNeu = zaehler[farbenIndex]+2;
                }
            } else {
                for (int i = 0; i < vglgrbl.length; i++) {
                    if (zahl == vglgrbl[i]) {
                        xy[i] +=1;
                        setGruen(xy);
                    }

                    if (xy[i]==1) {
                        zaehlerNeu++;
                    }
                }
            }
            zaehler[farbenIndex] = zaehlerNeu;
        }
    }

    public void streicheZahlBlau(int zahl) {
        int[] xy = getBlau();
        int farbenIndex = 0;
        for (int j = 0; j < farben.length; j++) {
            if (farben[j]=="blau") {
                farbenIndex = j;
            }
        }


        if (1 < zahl && zahl < 13) {
            int index = 0;
            int zaehlerNeu = 0;
            if (zahl == vglgrbl[vglgrbl.length-1]) {
                if (zaehler[farbenIndex]>=5) {
                    xy[vglgrbl.length-1] +=1;
                    setBlau(xy);
                    lock[farbenIndex]=true;
                    zaehlerNeu = zaehler[farbenIndex]+2;
                }
            } else {
                for (int i = 0; i < vglgrbl.length; i++) {
                    if (zahl == vglgrbl[i]) {
                        xy[i] +=1;
                        setBlau(xy);
                    }

                    if (xy[i]==1) {
                        zaehlerNeu++;
                    }
                }
            }
            zaehler[farbenIndex] = zaehlerNeu;
        }
    }

    public int plusFehlwurf() {
        fehlwuerfe = fehlwuerfe+1;
        return fehlwuerfe;
    }

    public int anzahlLocks() {
        int ergebnis = 0;
        for (int i = 0; i < lock.length; i++) {
            if (lock[i]) {
                ergebnis++;
            } // end of if
        }
        return ergebnis;
    }

    public void setLock(boolean[] lockNeu) {
        lock = lockNeu;
    }

    public boolean[] getLock() {
        return lock;
    }

    public int[] getZaehler() {
        return zaehler;
    }

    public String[] getFarben() {
        return farben;
    }

    public static void main(String[] args) {

    }

    // Ende Methoden
}
