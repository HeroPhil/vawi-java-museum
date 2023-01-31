import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * This VerwalterClass is used to manage all angebote.
 *
 * @author Sven Brüggenbrock
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
        // sort by attraktivität
        angebote.sort((a, b) -> b.ausstellungsstueck.attraktivität - a.ausstellungsstueck.attraktivität);
        return angebote.toArray(new Angebot[angebote.size()]);
        
    }

    /**
     * produces a subset of angebote where angebote.ausstellungsstueck.thema is a member of themen
     * sort by attraktivität
     * @param themen the themen to filter by
     * @return
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFiltered(Thema[] themen) {
        // use internal method to get angebote sorted and filtered by a comparator and a predicate
        return getAngeboteSortedAndFiltered(
                // use a comparator to sort by attraktivität
                (a, b) -> b.ausstellungsstueck.attraktivität - a.ausstellungsstueck.attraktivität,
                // use a predicate to filter by thema
                new Predicate<Angebot>() {
                    @Override
                    public boolean test(Angebot angebot) {
                        for (Thema thema : themen) {
                            if (angebot.ausstellungsstueck.thema == thema) {
                                return true;
                            }
                        }
                        return false;
                    }
                }
        );

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
        // use internal method to get angebote sorted and filtered by a comparator and a predicate
        return getAngeboteSortedAndFiltered(
                // use a comparator to sort by attraktivität
                (a, b) -> b.ausstellungsstueck.attraktivität - a.ausstellungsstueck.attraktivität
                ,
                // use a predicate to filter by themen and klassen
                angebot -> {
                    // check if angebot.ausstellungsstueck is instance of a member of klassen
                    for (Class<? extends Ausstellungsstueck> klass : ausstellungsstueckArten) {
                        if (klass.isInstance(angebot.ausstellungsstueck)) {
                            // check if angebot.ausstellungsstueck.thema is a member of themen
                            for (Thema thema : themen) {
                                if (thema == angebot.ausstellungsstueck.thema) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
        );
    }

    /**
     * internal method to get angebote sorted and filtered by a comparator and a predicate
     * @param comp
     * @param filter
     * @return angebote sorted and filtered by a comparator and a predicate
     */
    private Angebot[] getAngeboteSortedAndFiltered(Comparator<Angebot> comp, Predicate<Angebot> filter) {
        // perform sorting and filtering using a stream
        return angebote.stream()
                .filter(filter)
                .sorted(comp)
                .toArray(Angebot[]::new);
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
