/** 
 * 
 * Klasse "Angebot"
 * 
 * Die Klasse beinhaltet alle Informationen eines Angebots und 
 * referenziert die Klassen "Ausstellungsstueck" und "PartnerMuseum"
 * 
 * @author Meike Ganzer
 */
public class Angebot {

    // Die Klasse hat die nachfolgenden Attribute: 

    /**
     * ID des Angebots
     */
    public final int id; // final: Das Attribut wird nur einmal initialisiert und ist nicht mehr veränderbar (auch nicht über Getter und Setter).

    /**
     * Ausleihkosten für das Ausstellungsstueck
     */
    public final int kosten;

    /**
     * Ausstellungsstueck des Angebots
     */
    public final Ausstellungsstueck ausstellungsstueck;

    /**
     * PartnerMuseum des Angebots
     */
    public final PartnerMuseum partnerMuseum;

    /**
     * Konstruktor für Objekte der Klasse "Angebot"
     */
    public Angebot(int id, int kosten, Ausstellungsstueck ausstellungsstueck, PartnerMuseum partnerMuseum) {

        this.id = id; // Mit "this" unterscheidet man die Namen der Parameter von den Namen der Attribute. Ohne "this" würde man den Parameter mit sich selbst überschreiben
                      // und das Attribut nicht ansprechen.
        this.kosten = kosten;
        this.ausstellungsstueck = ausstellungsstueck;
        this.partnerMuseum = partnerMuseum;
    }
    /**
     * Ausgabe der Angebote als Text
     * 
     * @see java.lang.Object#toString()
     */
    @Override // Es wird eine Methode aus der Klasse "Object" überschrieben.
    
    public String toString() { // toString-Methode: vordefinierte Methode der Klasse "Object", die eine Textdarstellung zurückgibt.
                               // Ohne diese Methode wird nur der Speicherort des Objekts (Hashcode) ausgegeben.
        return "Angebot [id=" + id + ", kosten=" + kosten +", Ausstellungsstueck=" + ausstellungsstueck +", PartnerMuseum=" + partnerMuseum +"]";
    }

}
