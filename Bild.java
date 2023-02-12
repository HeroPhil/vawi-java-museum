
/**
 * Klasse "Bild"
 * 
 * Child-Klasse der Klasse "Ausstellungsstück"
 * Bilder haben zusätzliche Attribute: Tolerierte Temperatur (min/max), tolerierte Luftfeuchtigkeit (min/max), Mindestabstand
 *
 * @author Meike Ganzer
 */
public class Bild extends Ausstellungsstueck // "extends": Sorgt dafür, dass die Vererbung stattfindet.
{
    // Die Klasse "Bild" hat die folgenden Attribute:

    /**
     * Mindestabstand zwischen zwischen Bildern in cm
     */
    final static int MINDEST_ABSTAND = 100; // "static": Das Attribut ist ein sog. Klassenattribut: Der Mindestabstand gilt für alle Objekte der Klasse "Bild".

    /**
     * Minimaltemperatur in °C
     */
    public final int minTemp; // final: Das Attribut wird nur einmal initialisiert und ist nicht mehr veränderbar (auch nicht über Getter und Setter).

    /**
     * Minimale Luftfeuchtigkeit in %
     */
    public final int minFeuchtigkeit;

    /**
     * Maximaltemperatur in °C
     */
    public final int maxTemp;

    /**
     * Maximale Luftfeuchtigkeit in %
     */
    public final int maxFeuchtigkeit;

    /**
     * Konstruktor für Objekte der Klasse Bild
     */
    public Bild(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität, int hoehe,
    int breite, int eId, int minTemp, int minFeuchtigkeit, int maxTemp, int maxFeuchtigkeit) {

        super(bezeichnung, kuenstler, jahr, thema, attraktivität, hoehe, breite, eId); // "super": Zugriff auf den Konstruktor der Parentklasse "Ausstellungsstück"

        this.minTemp = minTemp; // Mit "this" unterscheidet man die Namen der Parameter von den Namen der Attribute. Ohne "this" würde man den Parameter mit sich selbst überschreiben
                                // und das Attribut nicht ansprechen.
        this.minFeuchtigkeit = minFeuchtigkeit;
        this.maxTemp = maxTemp;
        this.maxFeuchtigkeit = maxFeuchtigkeit;
    }

    /* Ausgabe der Bilder als Textbeschreibung 
     * 
     * @return Beschreibung
     */
    @Override // Es wird eine Methode aus der Klasse "Object" überschrieben.

    public String toString() { // toString-Methode: vordefinierte Methode der Klasse "Object", die eine Textdarstellung zurückgibt.
                               // Ohne diese Methode wird nur der Speicherort des Objekts (Hashcode) ausgegeben.

        return "Bild [minTemp=" + minTemp + ", minFeuchtigkeit=" + minFeuchtigkeit + ", maxTemp=" + maxTemp
                + ", maxFeuchtigkeit=" + maxFeuchtigkeit + ", bezeichnung=" + bezeichnung + ", kuenstler=" + kuenstler
                + ", jahr=" + jahr + ", thema=" + thema.bezeichnung + ", attraktivität=" + attraktivitaet + ", hoehe=" + hoehe
                + ", breite=" + breite + ", eId=" + eId + "]";
    }

}
