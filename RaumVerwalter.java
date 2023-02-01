import java.util.ArrayList;

/**
 * This VerwalterClass is used to manage all raeume
 *
 * @author Inken Dierichs
 */
public class RaumVerwalter
{
    /**
     * the singleton instance
     */
    private static RaumVerwalter INSTANCE;

    /**
     * @return the singleton instance
     */
    public static RaumVerwalter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RaumVerwalter();
        }

        return INSTANCE;
    }

    /**
     * check if a bild fits to a wall
     * considering the size of the bild and raum, as well as bestehendeBilder
     * @param raum the raum, in which the bild should be placed
     * @param position the position (wall), on which the bild should be placed
     * @param bild the bild to be placed
     * @param bestehendeBilder the other bilder, which are already placed in the raum
     * @return true, if all the bilder fit to the wall, false otherwise
     */
    public static boolean checkIfBildFitsToWall(Raum raum, Position position, Bild bild, Bild[] bestehendeBilder) {
        // 0 Pruefe ob Bildhoehe kleiner gleich Raumhoehe
        if (raum.hoehe <= bild.hoehe){
            //Fehlermeldung
            System.out.println("Achtung: Das Bild ist für den Raum zu hoch.");
            return false;
        }
        
        
        // 1 Berechnung Restwandlaenge (bei bestehenden Bildern)
        public int Restwandlaenge (Position position) {
            switch (position){
                case NORD:
                    return raum.getNettoWandLaenge(Position.NORD) - bestehendeBilder(Bild.NORD);
                case SUED:
                    return raum.getNettoWandLaenge(Position.SUED) - bestehendeBilder(Bild.SUED);
                case WEST:
                return raum.getNettoWandLaenge(Position.WEST) - bestehendeBilder(Bild.WEST);
                case OST:
                return raum.getNettoWandLaenge(Position.OST) - bestehendeBilder(Bild.OST);
                default:
                    return 0;      
            }  
        }



        // 2 Vergleich Restwandlaenge mit Breite von neuem Bild
        // 2a unter Beruecksichtigung Mindestabstand Bilder untereinander und zur Ecke/Tuer
        
        return false;
    }

    /**
     * check if a Kunstgegenstand or Kunstinstallation fits on the floor
     * @param raum the raum, in which the Kunstgegenstand or Kunstinstallation should be placed
     * @param ausstellungsstueck the Kunstgegenstand or Kunstinstallation to be placed
     * @param bestehendeAusstellungsstuecke the other Kunstgegenstaende or Kunstinstallationen, which are already placed in the raum
     * @return true, if all the Kunstgegenstaende or Kunstinstallationen fit on the floor, false otherwise
     */
    public static boolean checkIfGegenstandFitsOnFloor(Raum raum, Ausstellungsstueck3D ausstellungsstueck,
            Ausstellungsstueck3D[] bestehendeAusstellungsstuecke) {

        // 1 check height
        if (ausstellungsstueck.hoehe > raum.hoehe) {
            System.out.println("Ausstellungsstueck " + ausstellungsstueck.hoehe + " passt nicht in Raum wegen Hoehe " + raum.hoehe);
            return false;
        }

        // 2 add ausstellungsstueck to bestehendeAusstellungsstuecke in new arraylist
        ArrayList<Ausstellungsstueck3D> ausstellungsstuecke = new ArrayList<Ausstellungsstueck3D>();
        for (Ausstellungsstueck3D a : bestehendeAusstellungsstuecke) {
            ausstellungsstuecke.add(a);
        }
        ausstellungsstuecke.add(ausstellungsstueck);

        // 3 implement rectangle packing algorithm using breite and laenge. consider minium spacing of Ausstellungsstueck3D.MINDEST_ABSTAND
        // 3.1 sort by breite
        ausstellungsstuecke.sort((Ausstellungsstueck3D a1, Ausstellungsstueck3D a2) -> {
            return Double.compare(a1.breite, a2.breite);
        });

        // 3.2 sort by laenge
        ausstellungsstuecke.sort((Ausstellungsstueck3D a1, Ausstellungsstueck3D a2) -> {
            return Double.compare(a1.laenge, a2.laenge);
        });

        // 3.3 pack rectangles
        double x = 0;
        double y = 0;
        double maxY = 0;
        for (Ausstellungsstueck3D a : ausstellungsstuecke) {
            if (x + a.breite + Ausstellungsstueck3D.MINDEST_ABSTAND > raum.breite) {
                x = 0;
                y = maxY;
            }
            if (y + a.laenge + Ausstellungsstueck3D.MINDEST_ABSTAND > raum.laenge) {
                System.out.println("Ausstellungsstueck " + ausstellungsstueck.breite + " passt nicht in Raum wegen Laenge " + raum.laenge);
                return false;
            }
            x += a.breite + Ausstellungsstueck3D.MINDEST_ABSTAND;
            maxY = Math.max(maxY, y + a.laenge + Ausstellungsstueck3D.MINDEST_ABSTAND);
        }

        // if nor error occured, everything should fit
        return true;
    }

    /**
     * the list of raeume
     */
    private final ArrayList<Raum> raeume;

    /**
     * private constructor to prevent instantiation outside of this class
     * only the singleton instance can be used
     */
    private RaumVerwalter() {
        raeume = new ArrayList<Raum>();
    }

    /**
     * get all raeume
     * 
     * @return all raeume
     */
    public Raum[] getAllRaeume() {
        return raeume.toArray(new Raum[raeume.size()]);
    }

    /**
     * get raum by id
     * 
     * @param id the id of the raum
     * @return the raum with the given id
     */
    public Raum getRaumByID(int id) {
        for (Raum r : raeume) {
            if (r.id == id)
                return r;
        }
        return null;
    }

    /**
     * add a raum
     * only supposed to be used by the importer.
     * 
     * @param raum the raum to add
     */
    public void addRaum(Raum raum) {
        raeume.add(raum);
    }
}
