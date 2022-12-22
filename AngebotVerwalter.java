import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * This VerwalterClass is used to manage all angebote.
 *
 * @author Sven
 */
public class AngebotVerwalter {
    /**
     * the singleton instance
     */
    private static AngebotVerwalter INSTANCE;

    /**
     * @return the singleton instance
     */
    public static AngebotVerwalter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AngebotVerwalter();
        }

        return INSTANCE;
    }

    /**
     * the list of angebote
     */
    private final ArrayList<Angebot> angebote;

    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private AngebotVerwalter() {
        angebote = new ArrayList<Angebot>();
    }

    /**
     * get all angebote
     * @return all angebote
     */
    public Angebot[] getAllAngebote() {
        return angebote.toArray(new Angebot[angebote.size()]);
    }

    /**
     * return all angebote sorted by Attraktivität
     * sort by attraktivität
     * @return all angebote sorted by Attraktivität
     */
    public Angebot[] getAllAngeboteSortedByAttraktivitaet() {
        return null;
    }

    /**
     * produces a subset of angebote where angebote.ausstellungsstueck.thema is a member of themen
     * sort by attraktivität
     * @param themen the themen to filter by
     * @return
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFiltered(Thema[] themen) {
        return null;
    }
    
    /**
     * produces a subset of angebote where angebote.ausstellungsstueck is instance of a member of klassen
     * and angebote.ausstellungsstueck.thema is a member of themen
     * sort by attraktivität
     * @param themen the themen to filter by
     * @param klassen the classes to filter by
     * @return a subset of angebote where angebote.ausstellungsstueck is instance of a member of klassen
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFiltered(Thema[] themen, Class<? extends Ausstellungsstueck>[] ausstellungsstueckArten) {
        return null;
    }

    /**
     * internal method to get angebote sorted and filtered by a comparator and a predicate
     * @param comp
     * @param filter
     * @return angebote sorted and filtered by a comparator and a predicate
     */
    private Angebot[] getAngeboteSortedAndFiltered(Comparator<Angebot> comp, Predicate<Angebot> filter) {
        return null;
    }

    /**
     * use this method to get an angebot by an id
     * @param id the id of the angebot
     * @return the angebot with the given id
     */
    public Angebot getAngebotByID(int id) {
        for (Angebot a : angebote) {
            if (a.id == id)
                return a;
        }
        return null;
    }

    /**
     * add an angebot to the angebote.
     * supposed to be used by the importer.
     * @param Angebot the angebot to add
     */
    public void addAngebot(Angebot Angebot) {
        angebote.add(Angebot);
    }
}
