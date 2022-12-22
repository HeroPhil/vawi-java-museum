/**
 * Model class for an Angebot
 * contains all information about an Angebot
 * references the Ausstellungsstueck and PartnerMuseum
 * @author Meike
 */
public class Angebot {
    /**
     * the id of the Angebot
     */
    public final int id;

    /**
     * the kosten of the Angebot
     */
    public final int kosten;

    /**
     * the Ausstellungsstueck of the Angebot
     */
    public final Ausstellungsstueck ausstellungsstueck;

    /**
     * the PartnerMuseum of the Angebot
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

}
