
/**
 * Ausstellungsstück3D is the parent class of Kunstgegenstand and Kunstinstallation.
 *
 * @author Meike Ganzer
 */
public abstract class Ausstellungsstueck3D extends Ausstellungsstueck
{
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
    public Ausstellungsstueck3D(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {
        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId);
        this.laenge = laenge;
        this.gewicht = gewicht;
    }

}
