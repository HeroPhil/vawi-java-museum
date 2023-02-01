/**
 * Abstract class Ausstellungsstück
 * Parent class of Bild and Ausstellungsstück3D
 *
 * @author Meike Ganzer
 */
public abstract class Ausstellungsstueck {
    /**
     * the bezeichnung of the Ausstellungsstück
     */
    public final String bezeichnung;

    /**
     * the kuenstler of the Ausstellungsstück
     */
    public final String kuenstler;

    /**
     * the jahr of the Ausstellungsstück
     * just information so can remain as string (data is a mess)
     */
    public final String jahr;

    /**
     * the thema of the Ausstellungsstück
     */
    public final Thema thema;

    /**
     * the attraktivität of the Ausstellungsstück
     * valued betweeen 0 and 100
     */
    public final int attraktivitaet;

    /**
     * the hoehe of the Ausstellungsstück
     * in cm
     */
    public final double hoehe;

    /**
     * the breite of the Ausstellungsstück
     * in cm
     */
    public final double breite;

    /**
     * the external Id of the Ausstellungsstück
     */
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
        this.attraktivitaet = attraktivität;
        this.hoehe = hoehe;
        this.breite = breite;
        this.eId = eId;
    }

}
