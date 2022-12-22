import java.util.ArrayList;

/**
 * This VerwalterClass is used to manage all raeume
 *
 * @author Inken
 */
public class RaumVerwalter
{
    /**
     * the singleton instance
     */
    private static RaumVerwalter INSTANCE;
    
    /**
     * @return the singleton instance
     */
    public static RaumVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RaumVerwalter();
        }
        
        return INSTANCE;
    }
    
    /**
     * the list of raeume
     */
    private final ArrayList<Raum> raeume;
    
    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private RaumVerwalter() {
        raeume = new ArrayList<Raum>();
    }
    
    /**
     * get all raeume
     * @return all raeume
     */
    public Raum[] getAllRaeume() {
        return raeume.toArray(new Raum[raeume.size()]);
    }
    
    /**
     * get raum by id
     * @param id the id of the raum
     * @return the raum with the given id
     */
    public Raum getRaumByID(int id) {
        for (Raum r : raeume) {
            if (r.id == id) return r;
        }
        return null;
    }
    
    /**
     * get all raeume with at least half of all raeume
     * @return all raeume with at least half of all raeume
     */
    public Raum[] getRaeumeWithAtLeastHalfOfAllRaeume() {
        return null;
    }
    
    /**
     * add a raum
     * only supposed to be used by the importer.
     * @param raum the raum to add
     */
    public void addRaum(Raum raum) {
        raeume.add(raum);
    }
}
