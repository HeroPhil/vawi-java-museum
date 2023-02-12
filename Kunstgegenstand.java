
/**
 * Klasse "Kunstgegenstand"
 * 
 * Diese Klasse ist die Child-Klasse der Klasse "Ausstellungsstueck3D"
 *
 * @author Meike Ganzer
 */
public class Kunstgegenstand extends Ausstellungsstueck3D // "extends": Sorgt dafür, dass die Vererbung stattfindet.
{

    /**
     * Konstruktor für Objekte der Klasse "Kunstgegenstand"
     */
    public Kunstgegenstand(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {

        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId, laenge, gewicht); // "super": Zugriff auf den Konstruktor der Parentklasse "Ausstellungsstück"
    }



/**
 * Ausgabe der Kunstgegenstände als Textbeschreibung des Kunstgegenstands
 * 
 * @return Beschreibung
 */
@Override // Es wird eine Methode aus der Klasse "Object" überschrieben.
    public String toString() // toString-Methode: vordefinierte Methode der Klasse "Object", die eine Textdarstellung zurückgibt.
                             // Ohne diese Methode wird nur der Speicherort des Objekts (Hashcode) ausgegeben.
    { 
        return "Bezeichnung: "+bezeichnung+", Künstler: "+kuenstler+", Jahr: "+jahr +", Thema: "+ thema.bezeichnung +", Attraktivität: "+attraktivitaet+", Höhe: "+hoehe+", Breite: "+breite+", ID: "+bezeichnung+", Länge: "+laenge+", Gewicht: "+gewicht;
    }
}
