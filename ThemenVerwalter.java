import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A VerwalterClass for Themen
 * Themen are unique by bezeichnung
 *
 * @author Sven
 */
public class ThemenVerwalter
{
    /**
     * the singleton instance
     */
    private static ThemenVerwalter INSTANCE;
    

    /**
     * @return the singleton instance
     */
    public static ThemenVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ThemenVerwalter();
        }
        
        return INSTANCE;
    }

    /**
     * the set of themen
     */
    private final Set<Thema> themen;

    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private ThemenVerwalter() {
        themen = new HashSet<Thema>();
    }

    /**
     * get all known themen
     * @return the array of all themen
     */
    public Thema[] getAllThemen() {
        return themen.toArray(new Thema[]{});
    }

    /**
     * get a thema by bezeichnung if it exists
     * otherwise add it to the set and return it
     * @param bezeichnung the bezeichnung of the thema
     * @return the thema with the given bezeichnung
     */
    public Thema getOrAddThema(String bezeichnung) {
        Thema neuesThema = new Thema(bezeichnung);
        
        if (!themen.add(neuesThema)) {
            // set beinhaltet Thema bereits -> finde das Thema
            for (Iterator<Thema> it = themen.iterator(); it.hasNext(); ) {
                Thema thema = it.next();
                if (thema.equals(neuesThema))
                    return thema;
            }
        }
        // ansonsten gib das neu eingefuegte Thema zurueck
        return neuesThema;
    }
}
