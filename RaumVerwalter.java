import java.util.ArrayList;

/**
 * This VerwalterClass is used to manage all raeume
 *
 * @author Inken Dierichs
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
        if (INSTANCE == null) {
            INSTANCE = new RaumVerwalter();
        }

        return INSTANCE;
    }

    public static boolean checkIfBildFitsToWall(Raum raum, Position position, Bild bild, Bild[] bestehendeBilder) {
       return false;
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
     * 
     * @return all raeume
     */
    public Raum[] getAllRaeume() {
        return raeume.toArray(new Raum[raeume.size()]);
    }

    /**
     * get raum by id
     * 
     * @param id the id of the raum
     * @return the raum with the given id
     */
    public Raum getRaumByID(int id) {
        for (Raum r : raeume) {
            if (r.id == id)
                return r;
        }
        return null;
    }

    /**
     * add a raum
     * only supposed to be used by the importer.
     * 
     * @param raum the raum to add
     */
    public void addRaum(Raum raum) {
        raeume.add(raum);
    }
}
