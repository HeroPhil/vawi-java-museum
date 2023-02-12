
/**
 * Modellklasse für ein Thema
 *
 * @author Sven Brüggenbrock
 */
public class Thema
{

    /**
     * Die Bezeichnung des Themas
     */
    public final String bezeichnung;

    /**
     * Konstruktur für Objekte der Klasse Thema
     */
    public Thema(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /* 
     * Methode, um  einen Hashwert für Objekte zu erhalten. Der Hashwert wird anhand des Werts der Variable
     * bezeichnung berechnet.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
        return result;
    }

    /* 
     * Methode zum Abgleich, ob zwei Objekte der Klasse Thema gleich sind.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Thema other = (Thema) obj;
        if (bezeichnung == null) {
            if (other.bezeichnung != null)
                return false;
        } else if (!bezeichnung.equals(other.bezeichnung))
            return false;
        return true;
    }
    /*
     * Methode zur Rückgabe einer Beschreibung eines Objekt der Klasse Thema als String.
    */
    @Override
    public String toString() {
        return "Thema [bezeichnung=" + bezeichnung + "]";
    }

    

}
