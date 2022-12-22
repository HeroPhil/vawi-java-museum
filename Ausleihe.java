
/**
 * Write a description of class Ausleihe here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ausleihe
{
    public final Angebot angebot;
    public final Raum raum;
    public final Position position;

    public Ausleihe(Angebot angebot, Raum raum, Position position) {
        this.angebot = angebot;
        this.raum = raum;
        this.position = position;
    }

}
