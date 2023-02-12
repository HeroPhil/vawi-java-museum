
/**
 * Klasse "Ausstellungsstueck3D"
 * 
 * Diese Klasse ist die Child-Klasse der Klasse "Ausstellungsstück" und die Parent-Klasse der Klassen "Kunstgegenstand" und "Kunstinstallation".
 *
 * @author Meike Ganzer
 */
public abstract class Ausstellungsstueck3D extends Ausstellungsstueck // "extends": Sorgt dafür, dass die Vererbung stattfindet.
{
    // Attribute der Klasse "Ausstellungsstueck3D":

    /**
     * Mindestabstand zwischen 3D-Ausstellungsstücken in cm
     * in cm
     */
    final static double MINDEST_ABSTAND = 200; // "static": Das Attribut ist ein sog. Klassenattribut: Der Mindestabstand gilt für alle Objekte der Klasse "Bild".

    /**
     * Länge des Ausstellungsstück3D in cm
     */
    public final double laenge;

    /**
     * Gewicht des Ausstellungsstück3D in kg
     */
    public final double gewicht;

    /**
     * Konstruktor für Objekte der Klasse "Ausstellungsstück3D"
     */
    public Ausstellungsstueck3D(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId, double laenge, double gewicht) {

        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId); // "super": Zugriff auf den Konstruktor der Parentklasse "Ausstellungsstück"

        this.laenge = laenge;  // Mit "this" unterscheidet man die Namen der Parameter von den Namen der Attribute. Ohne "this" würde man den Parameter mit sich selbst überschreiben
                               // und das Attribut nicht ansprechen.
        this.gewicht = gewicht;
    }

}
