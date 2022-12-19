
/**
 * Write a description of class ParnterMuseum here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PartnerMuseum
{
    public final String name;
    public final String anschrift;

    
    public PartnerMuseum(String name, String anschrift) {
        this.name = name;
        this.anschrift = anschrift;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((anschrift == null) ? 0 : anschrift.hashCode());
        return result;
    }
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
    
    
}
