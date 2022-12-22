
/**
 * Thema Modelclass
 *
 * @author Sven
 */
public class Thema
{

    /**
     * the bezeichnung of the Thema
     */
    public final String bezeichnung;

    /**
     * Constructor for objects of class Thema
     */
    public Thema(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
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
        Thema other = (Thema) obj;
        if (bezeichnung == null) {
            if (other.bezeichnung != null)
                return false;
        } else if (!bezeichnung.equals(other.bezeichnung))
            return false;
        return true;
    }

}
