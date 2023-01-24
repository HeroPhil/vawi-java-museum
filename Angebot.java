/**
 * Model class for an Angebot
 * Beinhaltet alle Informationen eines Angebots
 * Referenziert Ausstellungsstueck und PartnerMuseum
 * @author Meike Ganzer
 */
public class Angebot {
    /**
     * ID des Angebots
     */
    public final int id;

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
     * Constructor for objects of class Angebot
     */
    public Angebot(int id, int kosten, Ausstellungsstueck ausstellungsstueck, PartnerMuseum partnerMuseum) {
        this.id = id;
        this.kosten = kosten;
        this.ausstellungsstueck = ausstellungsstueck;
        this.partnerMuseum = partnerMuseum;
    }
    /**
     * Ausgabe der Angebote als Text
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Angebot [id=" + id + ", kosten=" + kosten +", Ausstellungsstueck=" + ausstellungsstueck +", PartnerMuseum=" + partnerMuseum +"]";
    }

}
