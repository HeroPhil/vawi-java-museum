import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * VerwalterClass for PartnerMuseums
 * PartnerMuseums are unique by name and address
 *
 * @author Sven Brüggenbrock
 * @version (a version number or a date)
 */
public class PartnerMuseumsVerwalter
{

    /**
     * the singleton instance
     */
    private static PartnerMuseumsVerwalter INSTANCE;
    
    /**
     * @return the singleton instance
     */
    public static PartnerMuseumsVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PartnerMuseumsVerwalter();
        }
        
        return INSTANCE;
    }

    /**
     * the set of partnerMuseums
     */
    private final Set<PartnerMuseum> partnerMuseen;

    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private PartnerMuseumsVerwalter() {
        partnerMuseen = new HashSet<PartnerMuseum>();
    }


    /**
     * get a partnerMuseum by name and address if it exists
     * otherwise add it to the set and return it
     * @param name the name of the partnerMuseum
     * @param anschrift the address of the partnerMuseum
     * @return the partnerMuseum with the given name and address
     */
    public PartnerMuseum getOrAddPartnerMuseum(String name, String anschrift) {
        PartnerMuseum neuesPartnerMuseum = new PartnerMuseum(name, anschrift);
        
        if (!partnerMuseen.add(neuesPartnerMuseum)) {
            // set beinhaltet Partner bereits -> finde den partner
            for (Iterator<PartnerMuseum> it = partnerMuseen.iterator(); it.hasNext(); ) {
                PartnerMuseum parnterMuseum = it.next();
                if (parnterMuseum.equals(neuesPartnerMuseum))
                    return parnterMuseum;
            }
        }
        // ansonsten gib den neuen eingefuegten Partner zurueck
        return neuesPartnerMuseum;
    }
}
