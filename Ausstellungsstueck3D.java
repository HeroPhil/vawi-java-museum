
/**
 * Write a description of class Ausstellungsstück3D here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Ausstellungsstueck3D extends Ausstellungsstueck
{
    final static double MINDEST_ABSTAND = 200;

    public final double laenge;
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
