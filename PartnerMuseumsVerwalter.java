import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Write a description of class PartnerMuseumsVerwalter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PartnerMuseumsVerwalter
{
    private static PartnerMuseumsVerwalter INSTANCE;
    
    public static PartnerMuseumsVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PartnerMuseumsVerwalter();
        }
        
        return INSTANCE;
    }

    private final Set<PartnerMuseum> partnerMuseen;

    private PartnerMuseumsVerwalter() {
        partnerMuseen = new HashSet<PartnerMuseum>();
    }


    /**
     * @param name
     * @param anschrift
     * @return
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
