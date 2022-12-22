/**
 * Write a description of class Angebot here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Angebot {
    public final int id;
    public final int kosten;
    public final Ausstellungsstueck ausstellungsstueck;
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
