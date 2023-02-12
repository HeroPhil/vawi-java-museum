/**
 * Abstrakte Klasse "Ausstellungsstück"
 * 
 * Parentklasse der Klassen "Bild" und "Ausstellungsstück3D"
 *
 * @author Meike Ganzer
 */
public abstract class Ausstellungsstueck { // Abstrakte Klasse: Von dieser Klasse können (und sollen) keine Objekte erzeugt werden.

    // Die Klasse Ausstellungsstück hat die nachfolgenden Attribute:

    /**
     * Bezeichnung des Ausstellungsstücks
     */
    public final String bezeichnung; // final: Das Attribut wird nur einmal initialisiert und ist nicht mehr veränderbar (auch nicht über Getter und Setter).

    /**
     * Name des Künstlers des Ausstellungsstücks
     */
    public final String kuenstler;

    /**
     * Das Fertigstellungsjahr des Ausstellungsstücks
     * (dient nur der Information, daher String) 
     */
    public final String jahr;

    /**
     * Das Thema des Ausstellungsstücks
     */
    public final Thema thema;

    /**
     * Die Attraktivität des Ausstellungsstücks (Wert zwischen 0 und 100)
     * 
     */
    public final int attraktivitaet; //Optimierungspotential: Datentyp "byte" verwenden, um Speicherplatz zu sparen.

    /**
     * Höhe des Ausstellungsstücks
     * in cm
     */
    public final double hoehe;

    /**
     * Breite des Ausstellungsstück
     * in cm
     */
    public final double breite;

    /**
     * Externe Id des Ausstellungsstücks
     */
    public final int eId;

   

    /**
     * Konstruktor für Objekte der Klasse "Ausstellungsstück" 
     */
    public Ausstellungsstueck(String bezeichnung, String kuenstler, String jahr, Thema thema, int attraktivität,
            double hoehe, double breite, int eId) {
                
        this.bezeichnung = bezeichnung; // Mit "this" unterscheidet man die Namen der Parameter von den Namen der Attribute. 
                                        // Ohne "this" würde man den Parameter mit sich selbst überschreiben und das Attribut nicht ansprechen.
        this.kuenstler = kuenstler;
        this.jahr = jahr;
        this.thema = thema;
        this.attraktivitaet = attraktivität;
        this.hoehe = hoehe;
        this.breite = breite;
        this.eId = eId;
    }

}
