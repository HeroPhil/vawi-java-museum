
/**
 * Model class for a Kunstinstallation
 * @author Meike Ganzer
 */
public class Kunstinstallation extends Ausstellungsstueck3D
{

/**
* Constructor for objects of class Kunstinstallation
*/
public Kunstinstallation(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
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
        return "Bezeichnung: "+bezeichnung+", Künstler: "+kuenstler+", Jahr: "+jahr +", Thema: "+ thema +", Attraktivität: "+attraktivitaet+", Höhe: "+hoehe+", Breite: "+breite+", externe ID: "+eId+", Länge: "+laenge+", Gewicht: "+gewicht;
    }
}

