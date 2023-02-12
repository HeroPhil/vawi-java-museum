
/**
 * Modellklasse für ein PartnerMuseum
 *
 * @author Sven Brüggenbrock
 */
public class PartnerMuseum
{
    
    /**
     * Name des Partnermuseums
     */
    public final String name;

    /**
     * Anschrift desPartnermuseums
     */
    public final String anschrift;

    
    /**
     * Konstruktor für Objekte der Klasse PartnerMuseum. 
     */
    public PartnerMuseum(String name, String anschrift) {
        this.name = name;
        this.anschrift = anschrift;
    }
    

    /* 
     * Methode, um  einen Hashwert für Objekte zu erhalten. Der Hashwert wird anhand der Werte der Variablen
     * name und anschrift berechnet.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((anschrift == null) ? 0 : anschrift.hashCode());
        return result;
    }
    
    /* 
     * Methode zum Abgleich, ob zwei Objekte der Klasse PartnerMuseum gleich sind.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PartnerMuseum other = (PartnerMuseum) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (anschrift == null) {
            if (other.anschrift != null)
                return false;
        } else if (!anschrift.equals(other.anschrift))
            return false;
        return true;
    }

    /*
     * Methode zur Rückgabe einer Beschreibung eines Objekt der Klasse PartnerMuseum als String.
     */
    @Override
    public String toString() {
        return "PartnerMuseum [name=" + name + ", anschrift=" + anschrift + "]";
    }
    
    
}
