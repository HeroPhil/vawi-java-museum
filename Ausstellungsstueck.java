/**
 * Write a description of class Ausstellungsstück here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Ausstellungsstueck {
    public final String bezeichnung;
    public final String kuenstler;
    public final String jahr; // just information so can remain as string (data is a mess)
    public final Thema thema;
    public final int attraktivität;
    public final double hoehe;
    public final double breite;
    public final int eId;

   

    /**
     * Constructor for objects of class Ausstellungsstück
     */
    public Ausstellungsstueck(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId) {
        this.bezeichnung = bezeichnung;
        this.kuenstler = kuenstler;
        this.jahr = jahr;
        this.thema = thema;
        this.attraktivität = attraktivität;
        this.hoehe = hoehe;
        this.breite = breite;
        this.eId = eId;
    }

}
