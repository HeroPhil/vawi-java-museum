import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Die Klasse PartnerMuseumVerwalter fungiert als Verwaltungsklasse für Objekte der Klasse PartnerMuseum. 
 * Objekte der Klasse PartnerMuseums sind einzigartig durch name and anschrift
 *
 * @author Sven Brüggenbrock
 */

public class PartnerMuseumsVerwalter
{
//
    /**
     * Deklaration einer Singleton Instanz mit der Funktion, dass nur ein Objekt der Klasse PartnerMuseumVerwalter existieren kann.
     */

    private static PartnerMuseumsVerwalter INSTANCE;
    
    /**
     * hier wird geprüft, ob bereits eine Instanz und damit ein Objekt der Klasse PartnerMuseumVerwalter
     * existiert. Falls es noch keine Instanz gibt, wird eine neue Instanz erzeugt.
     * @return die Singleton Instanz
     */
    
    public static PartnerMuseumsVerwalter getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PartnerMuseumsVerwalter();
        }
        
        return INSTANCE;
    }

    /**
     * Deklaration eines Set, welches als Elemente Objekte der Klasse PartnerMuseum aufnehmen kann.
     */
    private final Set<PartnerMuseum> partnerMuseen;

    /**
     * Konstruktor der Klasse PartnerMuseumVerwalter. Dieser ist privat, damit diese nicht von "außen"
     * aufgerufen werden kann und verhindert dadurch eine mehrfache Instanzierung. Es soll lediglich die Singleton Instanz 
     * genutzt werden. Das Set partnerMuseen wird als HashSet erzeugt. 
     */
    private PartnerMuseumsVerwalter() {
        partnerMuseen = new HashSet<PartnerMuseum>();
    }

    public PartnerMuseum[] getAllPartnerMuseen() {
        return partnerMuseen.toArray(PartnerMuseum[]::new);
    }


    /**
     * Methode, die ein Partnermuseum zurückgibt, falls dieses bereits existiert. Erst wird ein neues PartnerMusuem mit den 
     * Eingabeparametern der Methode deklariert und erzeugt. Mit dem negierten Ausdruck in der if Schleife wird zum einen geprüft, 
     * ob das Set das PartnerMueseum bereits enthält bzw. dieses, wenn es nicht enthalten ist hinzugefügt. Die add() Methode von
     * Sets fügt lediglich Elemente hinzu, wenn diese noch nicht enthalten sind, gibt in diesem Fall auch den Boolschen Wert True
     * zurück, und im anderen Fall den Boolschen Wert False. Falls der Wert False ist, wird durch die Negierung der Ausdruck True und
     * es wird druch das Set iteriert, um das Partnermuseum zu finden. Falls dieses gefunden worden ist, wird das Partnermuseum aus 
     * dem Set partnerMuseen zurückgegeben. Andernfalls wird ein neues Partnermuseen in das HashSet partnerMuseen hinzugefügt.
     * 
     * @param name Name des Partnermuseums
     * @param anschrift Adresse des Partnermuseums
     * @return Das Partnermuseum mit Namen und Adresse
     */
    public PartnerMuseum getOrAddPartnerMuseum(String name, String anschrift) {
        PartnerMuseum neuesPartnerMuseum = new PartnerMuseum(name, anschrift);
        
        if (!partnerMuseen.add(neuesPartnerMuseum)) {
            for (Iterator<PartnerMuseum> it = partnerMuseen.iterator(); it.hasNext(); ) {
                PartnerMuseum partnerMuseum = it.next();
                if (partnerMuseum.equals(neuesPartnerMuseum))
                    return partnerMuseum;
            }
        }
        return neuesPartnerMuseum;
    }
}
