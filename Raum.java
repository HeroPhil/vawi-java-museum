
/**
 * Write a description of class Raum here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Raum
{
    public final int id;
    public final String bezeichnung;
    public final int laenge;
    public final int breite;
    public final int hoehe;
    public final int tuerNord;
    public final int tuerOst;
    public final int tuerSued;
    public final int tuerWest;

    
    
    public Raum(int id, String bezeichnung, int laenge, int breite, int hoehe, int tuerNord, int tuerOst, int tuerSued,
            int tuerWest) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.laenge = laenge;
        this.breite = breite;
        this.hoehe = hoehe;
        this.tuerNord = tuerNord;
        this.tuerOst = tuerOst;
        this.tuerSued = tuerSued;
        this.tuerWest = tuerWest;
    }



    /**
     * Gibt die Wandlaenge abzueglich der tuerbreite zurueck.
     * @return
     */
    public Integer getNettoWandLaenge() {
        // TODO
        return null;
    }

}
