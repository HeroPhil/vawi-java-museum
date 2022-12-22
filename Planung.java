import java.util.ArrayList;

/**
 * Central class for the planning of the exhibition.
 *
 * @author Philip Herold
 */
public class Planung
{
    
    /**
     * The thema to plan for.
     */
    public final Thema thema;

    /**
     * Flag if the planung is already done.
     */
    public boolean geplant;

    /**
     * The list of all ausleihen.
     * This list is only filled if the planung is already done.
     * Otherwise it is empty.
     * This represents the result of the planung.
     */
    private final ArrayList<Ausleihe> ausleihen;
    
    /**
     * Constructor
     * @param thema the thema to plan for
     */
    public Planung(Thema thema) {
        this.thema = thema;
        this.geplant = false;
        this.ausleihen = new ArrayList<Ausleihe>();
    }

    /**
     * Gibt alle geplanten Ausleihen zurück.
     * @return alle geplanten Ausleihen oder null wenn noch nicht geplant wurde.
     */
    public Ausleihe[] getAllAusleihen() {
        if (!geplant) {
            return null;
        }

        return ausleihen.toArray(new Ausleihe[ausleihen.size()]) ;
    }

    private void addAusleihe(Angebot angebot, Raum raum, Position position) {
        ausleihen.add(new Ausleihe(angebot, raum, position));
    }


    /**
     * Dies ist die primaere Logik-Methode.
     * Diese soll von der main Klasse aufgerufen werden um die Planung zu starte.
     */
    public void planungDurchfuehren() {

        // Beispiele Planung:
        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) {

            // Zentrale Fragen:
            // Welches Ausstellungstueck?
            Angebot angebot = AngebotVerwalter.getInstance().getAngebotByID(raum.id);
            // Wo in den Raum?
            Position position = Position.NORD;

            addAusleihe(angebot, raum, position);

        }


        this.geplant = true;
    }

}
