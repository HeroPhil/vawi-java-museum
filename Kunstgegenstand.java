
/**
 * Modelclass Kunstgegenstand - a piece of art
 *
 * @author Meike Ganzer
 */
public class Kunstgegenstand extends Ausstellungsstueck3D
{

    /**
     * Constructor for objects of class Kunstgegenstand
     */
    public Kunstgegenstand(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {
        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId, laenge, gewicht);
    }



/**
 * Beschreibung des Kunstgegenstands (Text)
 * 
 * @return Beschreibung
 */
@Override
    public String toString() 
    { 
        return "Bezeichnung: "+bezeichnung+", Künstler: "+kuenstler+", Jahr: "+jahr +", Thema: "+ thema +", Attraktivität: "+attraktivität+", Höhe: "+hoehe+", Breite: "+breite+", ID: "+bezeichnung+", Länge: "+laenge+", Gewicht: "+gewicht;
    }
}
