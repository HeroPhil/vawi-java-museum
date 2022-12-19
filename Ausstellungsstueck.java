/**
 * Write a description of class Ausstellungsstück here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Ausstellungsstueck {
    public String bezeichnung;
    public String kuenstler;
    public String jahr; // just information so can remain as string (data is a mess)
    public String thema;
    public int attraktivität;
    public double hoehe;
    public double breite;
    public int eId;

   

    /**
     * Constructor for objects of class Ausstellungsstück
     */
    public Ausstellungsstueck(String bezeichnung, String kuenstler, String jahr, String thema, int attraktivität,
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
