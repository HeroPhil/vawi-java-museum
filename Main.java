
/**
 * Entry point for the application.
 *
 * @author Philip Herold
 */
public class Main
{
    /**
     * Application entry point.
     * Runs the Importer and the Exporter.
     * Creates a Planung and performs it.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO put config somewhere nice
        String basePfad = args[0];

        String raeumePfad = basePfad + "/input/datensatz_01/raeume.csv";
        String angebotePfad = basePfad + "/input/datensatz_01/kunstwerke.csv";

        String exportPfad = basePfad + "/output/example.csv";
        

        try {
            
            Importer.importRaeume(raeumePfad);
            Importer.importAngebote(angebotePfad);
            
            // Themenauswahl
            
            Planung planung = new Planung(new Thema("Themen werden noch nicht beruecksichtigt"));
            planung.planungDurchfuehren();

            Exporter.exportExample(planung.getAllAusleihen(), exportPfad);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
