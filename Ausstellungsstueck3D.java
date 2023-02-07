
/**
 * Ausstellungsstück3D is the parent class of Kunstgegenstand and Kunstinstallation.
 *
 * @author Meike Ganzer
 */
public abstract class Ausstellungsstueck3D extends Ausstellungsstueck
{

    /**
     * minimum space between 3D Ausstellungsstücke
     * in cm
     */
    final static double MINDEST_ABSTAND = 200;

    /**
     * the laenge of the Ausstellungsstück3D
     * in cm
     */
    public final double laenge;

    /**
     * the gewicht of the Ausstellungsstück3D
     * in kg
     */
    public final double gewicht;

    /**
     * Constructor for objects of class Ausstellungsstück3D
     */
    public Ausstellungsstueck3D(char art, String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {
        super(art, bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId);
        this.laenge = laenge;
        this.gewicht = gewicht;
    }

}
