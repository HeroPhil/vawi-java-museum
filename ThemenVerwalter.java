
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Die Klasse Themenverwalter fungiert als Verwaltungsklasse für Objekte der Klasse Thema. 
 * Objekte der Klasse Themen sind einzigartig durch bezeichnung.
 *
 * @author Sven Brüggenbrock
 */
public class ThemenVerwalter
{
    /**
     * Deklaration einer Singleton Instanz mit der Funktion, dass nur ein Objekt der Klasse ThemenVerwalter existieren kann.
     */

    private static ThemenVerwalter INSTANCE;

    /**
     * Hier wird geprüft, ob bereits eine Instanz und damit ein Objekt der Klasse ThemenVerwalter
     * existiert. Falls es noch keine Instanz gibt, wird eine neue Instanz erzeugt.
     * @return die Singleton Instanz
     */
    public static ThemenVerwalter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThemenVerwalter();
        }

        return INSTANCE;
    }

    /**
     * Deklaration einer Map, welche als Elemente Objekte der Klasse Thema und Integer aufnehmen kann.
     */
    private final Map<Thema, Integer> themen;

    /**
     * Konstruktor der Klasse ThemenVerwalter. Dieser ist privat, damit diese nicht von "außen"
     * aufgerufen werden kann und verhindert dadurch eine mehrfache Instanzierung. Es soll lediglich die Singleton Instanz 
     * genutzt werden. Die Map themen wird als HashMap erzeugt.
     */
    private ThemenVerwalter() {
        themen = new HashMap<Thema, Integer>();
    }

    /**
     * Methode zur Rückgabe sämtlicher Themen in der Map themen. Die Map wird erst in ein Set gewandelt, um die Methoden .stream()
     * und .sorted() nutzen zu können. Hier wird nach dem count Wert sortiert, also der Häufigkeit mit der die einzelnen Themen
     * in der Liste der Kunstwerke enthalten sind. Die Rückgabe erfolgt als Array, welcher Objekte der Klasse Thema enthält. 
     * @return Array mit allen Themen
     */
    public Thema[] getAllThemen() {
        return themen.entrySet().stream().sorted((a, b) -> b.getValue() - a.getValue()).map(e -> e.getKey()).toArray(Thema[]::new);

    }

    /**
     * Methode zur Rückgabe sämtlicher Themen in der Map themen. Die Map wird erst in ein Set gewandelt, um die Methoden .stream()
     * und .sorted() nutzen zu können. Hier wird alphabetisch nach der Bezeichnung der Themen sortiert. Die Rückgabe erfolgt als 
     * Array, welcher Objekte der Klasse Thema enthält. 
     * @return Array mit allen Themen
     */

    public Thema[] getAllThemenbyAlpha() {
        return themen.entrySet().stream().sorted(Map.Entry.comparingByKey((a, b) -> a.bezeichnung.compareTo(b.bezeichnung)))
                .map(e -> e.getKey()).toArray(Thema[]::new);
    }

    /**
     * Diese Methode deklariert eine neue Integervariable count. Ihr wird der Wert zugewiesen, der aus der
     * HashMap themen zu dem zu dem Eingabeparameter zurgehörigen Schlüssel entnommen wird. Dieser Wert gibt an
     * wie häufig das übergebene Thema in der Liste der Kunstwerke vertreten ist. Falls das Thema nicht vertreten 
     * ist wird ausgegeben, dass das Thema nicht gefunden worden ist. 
     * @return Integerwert, welcher die Anzahl an Einträge eines bestimmenten Themas in der HashMap wiedergibt
     */
    public int getCountForThema(Thema thema) {
        Integer count = themen.get(thema);
        if (count == null)
        {
            System.out.println("Thema " + thema + " nicht gefunden");
            count = 0;
        }
        return count;
    }

    /**
     * Erst wird ein neues Objekt der Klasse Thema mit den Eingabeparametern der Methode deklariert und erzeugt. 
     * Mit dem Ausdruck in der Bedingung der if Schleife wird zum einen geprüft, ob die HashMap das Thema bereits 
     * enthält bzw. dieses, wenn es nicht enthalten ist hinzugefügt. Die compute() Methode berechnet einen neuen Wert 
     * für den gegebenen Schlüssel (in diesem Fall neuesThema. Wenn der Wert für den gegebenen Schlüssel bereits 
     * vorhanden ist, wird er um 1 erhöht. Wenn er nicht vorhanden ist, wird ein neuer Wert von 1 eingefügt.
     * Wenn der Wert count größer als 1 ist, bedeutet das, dass das Thema bereits vorhanden ist. 
     * In diesem Fall wird eine Schleife ausgeführt, um das bereits vorhandene Thema zu finden und zurückzugeben. Wenn 
     * der Wert nicht größer als 1 ist wird die Schleife nicht durchlaufen und das Thema neuesThema zurückgegeben.
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
