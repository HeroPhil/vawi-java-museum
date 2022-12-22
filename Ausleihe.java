
/**
 * Model class containing the mapping of an Angebot to a Raum and Position
 *
 * @author Inken Dierichs
 */
public class Ausleihe
{

    /**
     * the angebot of the Ausleihe
     */
    public final Angebot angebot;


    /**
     * the raum of the Ausleihe
     */
    public final Raum raum;

    /**
     * the position of the Ausleihe
     */
    public final Position position;

    /**
     * Constructor for objects of class Ausleihe
     */
    public Ausleihe(Angebot angebot, Raum raum, Position position) {
        this.angebot = angebot;
        this.raum = raum;
        this.position = position;
    }

}
