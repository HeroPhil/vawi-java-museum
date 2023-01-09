
/**
 * Model class for a picture
 *
 * @author Meike Ganzer
 */
public class Bild extends Ausstellungsstueck
{
    
    /**
     * the minimum sepperation between bilder
     * in cm
     */
    final static double MINDEST_ABSTAND = 200;

    /**
     * the minimum temperature for the Bild
     * in °C
     */
    public final double minTemp;

    /**
     * the minimum humidity for the Bild
     * in %
     */
    public final double minFeuchtigkeit;

    /**
     * the maximum temperature for the Bild
     * in °C
     */
    public final double maxTemp;

    /**
     * the maximum humidity for the Bild
     * in %
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Bild [minTemp=" + minTemp + ", minFeuchtigkeit=" + minFeuchtigkeit + ", maxTemp=" + maxTemp
                + ", maxFeuchtigkeit=" + maxFeuchtigkeit + ", bezeichnung=" + bezeichnung + ", kuenstler=" + kuenstler
                + ", jahr=" + jahr + ", thema=" + thema + ", attraktivität=" + attraktivität + ", hoehe=" + hoehe
                + ", breite=" + breite + ", eId=" + eId + "]";
    }

}
