
/**
 * Model Class for a Raum
 *
 * @author Inken Dierichs
 */
public class Raum
{

    /**
     * the id of the Raum
     */
    public final int id;

    /**
     * the bezeichnung of the Raum
     */
    public final String bezeichnung;

    /**
     * the laenge of the Raum
     * (Nord-Sued)
     * in cm
     */
    public final int laenge;

    /**
     * the breite of the Raum
     * (Ost-West)
     * in cm
     */
    public final int breite;

    /**
     * the hoehe of the Raum
     * in cm
     */
    public final int hoehe;

    /**
     * the tuerNord of the Raum
     * in cm
     */
    public final int tuerNord;

    /**
     * the tuerOst of the Raum
     * in cm
     */
    public final int tuerOst;

    /**
     * the tuerSued of the Raum
     * in cm
     */
    public final int tuerSued;

    /**
     * the tuerWest of the Raum
     * in cm
     */
    public final int tuerWest;

    
    /**
     * Constructor for objects of class Raum
     * zweite Zeile
     */
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
     * Berechnet die Wandlaenge abzueglich der tuerbreite fuer eine bestimmte Wand.
     * @param position welche Position / Wand
     * @return die Wandlaenge abzueglich der tuerbreite
     */
    public int getNettoWandLaenge(Position position) {
        switch (position){
            case NORD:
                return this.laenge - this.tuerNord;
            case SUED:
                return this.laenge - this.tuerSued;
            case WEST:
                return this.breite - this.tuerWest;
            case OST:
                return this.breite - this.tuerOst;
            default:
                return 0;      
        }  
    }

}
