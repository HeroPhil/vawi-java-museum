
/**
 * Model class for a Kunstinstallation
 * @author Meike
 */
public class Kunstinstallation extends Ausstellungsstueck3D
{

    /**
     * minimum space between 3D Ausstellungsstücke
     * in cm
     */
    final static double MINDEST_ABSTAND = 200;

    /**
     * Constructor for objects of class Kunstinstallation
     */
    public Kunstinstallation(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {
        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId, laenge, gewicht);
    }

}
