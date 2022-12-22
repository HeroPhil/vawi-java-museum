import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a description of class ThemenVerwalter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ThemenVerwalter
{
    private static ThemenVerwalter INSTANCE;
    
    public static ThemenVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ThemenVerwalter();
        }
        
        return INSTANCE;
    }

    private final Set<Thema> themen;

    private ThemenVerwalter() {
        themen = new HashSet<Thema>();
    }

    public Thema[] getAllThemen() {
        return themen.toArray(new Thema[]{});
    }



    /**
     * @param bezeichnung
     * @return
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
