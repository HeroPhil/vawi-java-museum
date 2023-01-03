
/**
 * Entry point for the application.
 *
 * @author Philip Herold
 */
public abstract class Main
{
    /**
     * Application entry point.
     * Runs the Importer and the Exporter.
     * Creates a Planung and performs it.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO put config somewhere nice

        String basePfad = System.getProperty("user.dir");

        String raeumePfad = basePfad + "/input/datensatz_01/raeume.csv";
        String angebotePfad = basePfad + "/input/datensatz_01/kunstwerke.csv";

        String exportPfad = basePfad + "/output/example.csv";
        

        try {
            
            // 1 Import
            Importer.importRaeume(raeumePfad);
            Importer.importAngebote(angebotePfad);


            // 1b DEBUG
            
            // Beispiel für Raumverwalter:
            System.out.print(RaumVerwalter.getInstance().getRaumByID(1).bezeichnung);
            // ...

            //
            
            
            // 2 Themenauswahl
            Planung planung = new Planung(new Thema("Themen werden noch nicht beruecksichtigt"));

            // 3 Planung
            planung.planungDurchfuehren();

            // 4 Export
            Exporter.exportExample(planung.getAllAusleihen(), exportPfad);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
