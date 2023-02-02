import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.Arrays;

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
        angebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        return angebote.toArray(new Angebot[angebote.size()]);
        
        //lambdas

        //streams, prädikat zu lambdas
        
        
    }

    /**
     * produces a subset of angebote where angebote.ausstellungsstueck.thema is a member of themen
     * sort by attraktivität
     * @param themen the themen to filter by
     * @return
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFilteredbyThema(Thema[] themen) {
        ArrayList<Angebot> themengefilterteAngebote = new ArrayList<Angebot>(); 
        
        for (Angebot angebot : angebote){
            for (Thema thema : themen ) {
                if (thema.equals(angebot.ausstellungsstueck.thema)) {
                    themengefilterteAngebote.add(angebot);
                }
            }
        }

        themengefilterteAngebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        
        return themengefilterteAngebote.toArray(new Angebot[themengefilterteAngebote.size()]) ;
        
        
        //angebote.stream().filter(angebotefilter -> angebotefilter.ausstellungsstueck.thema.equals(themen) );
        //Angebot[] angebots = (Angebot[]) angebote.stream().filter(angebot -> Arrays.stream(themen).anyMatch(thema -> angebot.ausstellungsstueck.thema == thema)).toArray();


        

    }
    
    /**
     * produces a subset of angebote where angebote.ausstellungsstueck is instance of a member of klassen
     * and angebote.ausstellungsstueck.thema is a member of themen
     * sort by attraktivität
     * @param themen the themen to filter by
     * @param klassen the classes to filter by
     * @return a subset of angebote where angebote.ausstellungsstueck is instance of a member of klassen
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFilteredbyThemaandWerk(Thema[] themen, Ausstellungsstueck[] ausstellungsstueckArten) {
        
        ArrayList<Angebot> themaundwerkgefilterteAngebote = new ArrayList<Angebot>(); 
        
        //Dritte for Schleife, die aus einer
        for (Angebot angebot : angebote){
            for (Thema thema : themen ) {
                //for (Ausstellungsstueck ausstellungsstueck : ausstellungsstueckArten){
                
                    if (thema.equals(angebot.ausstellungsstueck.thema)) {

                       // if(ausstellungsstueck.equals(angebot.ausstellungsstueck))
                        themaundwerkgefilterteAngebote.add(angebot);
                    }
                




            //    }
            }
        }

        themaundwerkgefilterteAngebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        
        return themaundwerkgefilterteAngebote.toArray(new Angebot[themaundwerkgefilterteAngebote.size()]) ;
        
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
    public void addAngebot(Angebot angebot) {
        angebote.add(angebot);
    }

    
     
}


