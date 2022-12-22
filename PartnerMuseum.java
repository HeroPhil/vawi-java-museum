
/**
 * Model class for a PartnerMuseum
 *
 * @author Sven
 */
public class PartnerMuseum
{
    
    /**
     * the name of the PartnerMuseum
     */
    public final String name;

    /**
     * the anschrift of the PartnerMuseum
     */
    public final String anschrift;

    
    /**
     * Constructor for objects of class PartnerMuseum
     */
    public PartnerMuseum(String name, String anschrift) {
        this.name = name;
        this.anschrift = anschrift;
    }
    

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((anschrift == null) ? 0 : anschrift.hashCode());
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
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
    
    
}
