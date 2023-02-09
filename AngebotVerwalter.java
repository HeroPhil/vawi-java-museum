import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.function.Predicate;
//import java.util.Arrays;

/**
 * Die Klasse AngebotVerwalter fungiert als Verwaltungsklasse für Objekte der Klasse Angebot. 
 *
 * @author Sven Brüggenbrock
 */

public class AngebotVerwalter {
    
    /**
     * Deklaration einer Singleton Instanz mit der Funktion, dass nur ein Objekt der Klasse AngebotVerwalter existieren kann. 
     */
    private static AngebotVerwalter INSTANCE;

    /**
     *  hier wird geprüft, ob bereits eine Instanz und damit ein Objekt der Klasse AngebotVerwalter
     * existiert. Falls es noch keine Instanz gibt, wird eine neue Instanz erzeugt.
     * @return die Singleton Instanz
     */
    public static AngebotVerwalter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AngebotVerwalter();
        }

        return INSTANCE;
    }

    /**
     * Deklaration einer ArrayList, die als Elemente Objekte der Klasse Angebote aufnehmen kann. 
     */
    private final ArrayList<Angebot> angebote;

    /**
     * Konstruktor der Klasse AngebotVerwalter. Dieser ist privat, damit diese nicht von "außen"
     * aufgerufen werden kann und verhindert dadurch eine mehrfache Instanzierung. Es soll lediglich die Singleton Instanz 
     * genutzt werden. Die ArrayList angebote wird erzeugt. 
     */
    private AngebotVerwalter() {
        angebote = new ArrayList<Angebot>();
    }

    /**
     * Methode zur Rückgabe sämtlicher Angebote in der ArrayList angebote. Die Ausgabe erfolgt in einem Array, welcher die 
     * Länge der ArrayList angebote hat. 
     * @return all angebote
     */
    public Angebot[] getAllAngebote() {
        return angebote.toArray(new Angebot[angebote.size()]);
    }
    
    /**
     * Methode zur Rückgabe eines Arrays, in dem sämtliche Angebote aus der ArrayList angebote nach dem Wert der Attraktivität
     * sortiert werden. Dies erfolgt mit Hilfe der .sort() Methode der Klasse ArrayList und unter Nutzung eines Lambda Ausdrucks. Hierdurch
     * wird vermieden eine innere anonyme Klasse lediglich fürs sortieren zu schreiben. 
     * @return Alle Angebote sortiert nach dem Wert der Attraktivität
     */
    public Angebot[] getAllAngeboteSortedByAttraktivitaet() {
        angebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        return angebote.toArray(new Angebot[angebote.size()]);       
    }

    /**
     * Methode zur Rückgabe eines Arrays, in dem sämtliche Angebote aus der ArrayList angeobte nach dem Wert der Attraktivität
     * sortiert sind und gleichzeitig nach Themen, die über das Array themen vorgegeben werden, gefiltert werden. Hier werden for und if 
     * Schleifen genutzt. Zuerst wird eine neue ArrayList themengefilterteAngebote, mit Elementen der Klasse Angebot, deklariert 
     * und erzeugt. Über die for Schleifen werden alle Angebote aus der ArrayList angebote und alle Themen aus der ArrayList themen 
     * durchgegangen und für den Fall, dass das Thema aus themen gleich mit dem Thema des Angebots bzw. hierinterstehenden
     * Ausstellungsstücks ist, zu der ArrayList themengefilterteAngebote hinzugefügt. Anschließend wird die ArrayList wie oben auch nach 
     * Attraktivität sortiert und als Array zurückgegeben. 
     * @param themen Die Themen nach denen gefiltert werden soll.
     * @return Array mit den Angeboten gefiltert nach Themen und sortiert nach Attraktivität. 
     */
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFilteredbyThema(Thema[] themen) {
        ArrayList<Angebot> themengefilterteAngebote = new ArrayList<Angebot>(); 
        
        for (Angebot angebot : angebote){
            for (Thema thema : themen ) {
                if (thema.equals(angebot.ausstellungsstueck.thema)) {
                    themengefilterteAngebote.add(angebot);
                }
            }
        }

        themengefilterteAngebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        
        return themengefilterteAngebote.toArray(new Angebot[themengefilterteAngebote.size()]) ;
        
    }
    
    /**
     * Methode zur Rückgabe eines Arrays, in dem sämtliche Angebote aus der ArrayList angebote nach dem Wert der Attraktivität
     * sortiert sind und gleichzeitig nach Themen und Art des Kunstwerks gefiltert werden. Hier werden for und if 
     * Schleifen genutzt. Zuerst wird eine neue ArrayList themaundwerkgefilterteAngebote, mit Elementen der Klasse Angebot, deklariert 
     * und erzeugt. Über die for Schleifen werden alle Angebote aus der ArrayList angebote und Ausstellungsstückarten aus dem Array 
     * ausstellungsstueckArten durchgegangen. Besonderheit hier, dass ausstellungsstueckArten ein Array mit Klassen, die child-Klassen
     * der Klasse Ausstellungsstueck sind, als Elemente enthält.
     * Danach werden alle Themen aus der ArrayList themen durchgegangen und für den Fall, dass das Thema aus themen gleich mit dem Thema
     * des Angebots bzw. hierinterstehenden Ausstellungsstücks ist, zu der ArrayList themengefilterteAngebote hinzugefügt. 
     * Anschließend wird die ArrayList wie oben auch nach Attraktivität sortiert und als Array zurückgegeben. 
     * @param themen Die Themen nach denen gefiltert werden soll
     * @param klassen Die Klassen nach denen gefiltert werden soll. 
     * @return Array mit den Angeboten gefiltert nach Themen und Ausstellungsstückarten bzw. deren Klassen und sortiert nach Attraktivität. 
     * ausstellungsstueck is instance of a member of klassen
     */
    @Deprecated()
    public Angebot[] getAngeboteSortedByAttraktivitaetAndFilteredbyThemaandWerk(Thema[] themen, Class<? extends Ausstellungsstueck>[] ausstellungsstueckArten) {
        
        ArrayList<Angebot> themaundwerkgefilterteAngebote = new ArrayList<Angebot>(); 
        
        for (Angebot angebot : angebote){
            for (Class<? extends Ausstellungsstueck> ausstellungsstueck : ausstellungsstueckArten){
                if(ausstellungsstueck.isInstance(angebot.ausstellungsstueck))
                    for (Thema thema : themen ) {
                        if (thema.equals(angebot.ausstellungsstueck.thema)) {
                    
                        themaundwerkgefilterteAngebote.add(angebot);
                    }
              
                }
            }
        }
        themaundwerkgefilterteAngebote.sort((Angebot angebotA, Angebot angebotB) -> angebotB.ausstellungsstueck.attraktivitaet - angebotA.ausstellungsstueck.attraktivitaet);
        
        return themaundwerkgefilterteAngebote.toArray(new Angebot[themaundwerkgefilterteAngebote.size()]) ;
        
        }

    /**
     * RAUSWERFEN?
     * internal method to get angebote sorted and filtered by a comparator and a predicate
     * @param comp
     * @param filter
     * @return angebote sorted and filtered by a comparator and a predicate
     
    private Angebot[] getAngeboteSortedAndFiltered(Comparator<Angebot> comp, Predicate<Angebot> filter) {
        return null;
    }
    */
    /**
     * Methode zur Rückgabe eines Angebots anhand einer eindeutigen ID. Mit der for Schleife wird die ArrayList angebote durchgegangen.
     * Mit der if Methode wird geprüft, ob das derzeit in der Schleife befindliche Angebot die gleiche ID hat wie die als 
     * Eingabeparameter gegebene ID. Wenn die ID gleich ist wird das treffende Angebot zurückgegeben. Wenn die ganze for Schleife über die
     * Angebote in der ArrayList angebote durchgelaufen ist und keine Angebots-ID mit der des Eingabeparameters übereinstimmt wird 
     * null zurückgegeben. 
     * @param id Die ID des Angebots
     * @return Das Angebot mit der gegebenen ID. 
     */
    public Angebot getAngebotByID(int id) {
        for (Angebot a : angebote) {
            if (a.id == id)
                return a;
        }
        return null;
    }
    
    /**
     * Mit dieser Methode werden Angebote zu der ArrayList angebote hinzugefügt. 
     * @param Angebot Das Angebot, welches zu der ArrayList angebote hinzugefügt werden soll. 
     */
    public void addAngebot(Angebot angebot) {
        angebote.add(angebot);
    }

    
     
}


