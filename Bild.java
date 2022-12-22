
/**
 * Write a description of class Bild here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bild extends Ausstellungsstueck
{
    final static double MINDEST_ABSTAND = 200;

    public final double minTemp;
    public final double minFeuchtigkeit;
    public final double maxTemp;
    public final double maxFeuchtigkeit;

    /**
     * Constructor for objects of class Bild
     */
    public Bild(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität, double hoehe,
            double breite, int eId, double minTemp, double minFeuchtigkeit, double maxTemp, double maxFeuchtigkeit) {
        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId);
        this.minTemp = minTemp;
        this.minFeuchtigkeit = minFeuchtigkeit;
        this.maxTemp = maxTemp;
        this.maxFeuchtigkeit = maxFeuchtigkeit;
    }

}
