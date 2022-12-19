import java.util.ArrayList;

/**
 * Write a description of class Angebotsverwalter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AngebotVerwalter
{
    private static AngebotVerwalter INSTANCE;
    
    public static AngebotVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AngebotVerwalter();
        }
        
        return INSTANCE;
    }
    
    private ArrayList<Angebot> Angebote;
    
    private AngebotVerwalter() {
        Angebote = new ArrayList<Angebot>();
    }
    
    public ArrayList<Angebot> getAllAngebote() {
        return Angebote;
    }
    
    public Angebot getAngebotByID(int id) {
        for (Angebot a : Angebote) {
            if (a.id == id) return a;
        }
        return null;
    }
    
    public void addAngebot(Angebot Angebot) {
        Angebote.add(Angebot);
    }
}
