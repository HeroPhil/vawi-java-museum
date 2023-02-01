import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A VerwalterClass for Themen
 * Themen are unique by bezeichnung
 *
 * @author Sven Brüggenbrock
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
        if (INSTANCE == null) {
            INSTANCE = new ThemenVerwalter();
        }

        return INSTANCE;
    }

    /**
     * the set of themen
     */
    private final Map<Thema, Integer> themen;

    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private ThemenVerwalter() {
        themen = new HashMap<Thema, Integer>();
    }

    /**
     * get all known themen
     * 
     * @return the array of all themen
     */
    public Thema[] getAllThemen() {
        return themen.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).map(e -> e.getKey()).toArray(Thema[]::new);
    }

    /**
     * get all known themen with the count of how often they are used
     * 
     * @return the map of all themen with the count of how often they are used
     */
    public int getCountForThema(Thema thema) {
        Integer count = themen.get(thema);
        if (count == null)
        {
            System.out.println("Thema " + thema + " not found");
            count = 0;
        }
        return count;
    }

    /**
     * get a thema by bezeichnung if it exists
     * otherwise add it to the set and return it
     * 
     * @param bezeichnung the bezeichnung of the thema
     * @return the thema with the given bezeichnung
     */
    public Thema getOrAddThema(String bezeichnung) {
        Thema neuesThema = new Thema(bezeichnung);

        if (themen.compute(neuesThema, (thema, count) -> count != null ? count + 1 : 1) > 1) {
            // set beinhaltet Thema bereits -> finde das Thema
            for (Iterator<Thema> it = themen.keySet().iterator(); it.hasNext();) {
                Thema thema = it.next();
                if (thema.equals(neuesThema))
                    return thema;
            }
        }

        // ansonsten gib das neu eingefuegte Thema zurueck
        return neuesThema;
    }
}
