import java.util.ArrayList;

/**
 * Write a description of class RaumVerwalter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RaumVerwalter
{
    private static RaumVerwalter INSTANCE;
    
    public static RaumVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RaumVerwalter();
        }
        
        return INSTANCE;
    }
    
    private ArrayList<Raum> raeume;
    
    private RaumVerwalter() {
        raeume = new ArrayList<Raum>();
    }
    
    /**
     * @return
     */
    public ArrayList<Raum> getAllRäume() {
        return raeume;
    }
    
    /**
     * @param id
     * @return
     */
    public Raum getRaumByID(int id) {
        for (Raum r : raeume) {
            if (r.id == id) return r;
        }
        return null;
    }
    
    /**
     * @param raum
     */
    public void addRaum(Raum raum) {
        raeume.add(raum);
    }
}
