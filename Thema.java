
/**
 * Write a description of class Thema here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thema
{
   String bezeichnung;

public Thema(String bezeichnung) {
    this.bezeichnung = bezeichnung;
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
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
    Thema other = (Thema) obj;
    if (bezeichnung == null) {
        if (other.bezeichnung != null)
            return false;
    } else if (!bezeichnung.equals(other.bezeichnung))
        return false;
    return true;
}

   
}
