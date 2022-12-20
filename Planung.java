import java.util.ArrayList;

/**
 * Write a description of class Planung here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Planung
{
    public Thema thema;
    public boolean geplant;

    public ArrayList<Ausleihe> ausleihen;
    
    public Planung(Thema thema) {
        this.thema = thema;
        this.geplant = false;
        this.ausleihen = new ArrayList<Ausleihe>();
    }

    /**
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
        for (Raum raum : RaumVerwalter.getInstance().getAllRäume()) {

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
