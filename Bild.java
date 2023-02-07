
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
    final static int MINDEST_ABSTAND = 200;

    /**
     * the minimum temperature for the Bild
     * in °C
     */
    public final int minTemp;

    /**
     * the minimum humidity for the Bild
     * in %
     */
    public final int minFeuchtigkeit;

    /**
     * the maximum temperature for the Bild
     * in °C
     */
    public final int maxTemp;

    /**
     * the maximum humidity for the Bild
     * in %
     */
    public final int maxFeuchtigkeit;

    /**
     * Constructor for objects of class Bild
     */
    public Bild(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität, int hoehe,
    int breite, int eId, int minTemp, int minFeuchtigkeit, int maxTemp, int maxFeuchtigkeit) {
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
                + ", jahr=" + jahr + ", thema=" + thema + ", attraktivität=" + attraktivitaet + ", hoehe=" + hoehe
                + ", breite=" + breite + ", eId=" + eId + "]";
    }

}
