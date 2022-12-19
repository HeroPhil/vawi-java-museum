
/**
 * Write a description of class Raum here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Raum
{
    final public int id;
    final public String bezeichnung;
    final public int laenge;
    final public int breite;
    final public int hoehe;
    final public int tuerNord;
    final public int tuerOst;
    final public int tuerSued;
    final public int tuerWest;

    
    
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
