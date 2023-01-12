
/**
 * Enumeration class Position
 * has 5 values: NORD, OST, SUED, WEST, MITTIG
 * refers to the position within a Raum
 * mittig means the floor
 * the other values refer to the walls (bilder only)
 *
 * @author Inken Dierichs
 * @version (version number or date here)
 */
public enum Position
{
    NORD("Nord"),
    OST("Ost"),
    SUED("Sued"),
    WEST("West"),
    BODEN("Boden"),
    VOLLKOMMEN("Vollkommen")
    ;

    public final String label;

    private Position(String label) {
        this.label = label;
    }

    // return all positions except mittig
    public static Position[] getWandPositionen() {
        // TODO randomize order
        return new Position[]{NORD, OST, SUED, WEST};
    }


}

