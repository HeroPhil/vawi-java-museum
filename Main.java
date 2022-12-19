
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args) {

        // String raeumePfad = "input/datensatz.01/raeume.csv";
        // String angebotePfad = "input/datensatz.01/kunstwerke.csv";
        String raeumePfad = args[0];
        String angebotePfad = args[1];

        try {
            Importer.importRaeume(raeumePfad);
            Importer.importAngebote(angebotePfad);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
