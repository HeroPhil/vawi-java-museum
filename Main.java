
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
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
