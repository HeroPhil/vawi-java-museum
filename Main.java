import java.io.File;
import java.lang.management.PlatformManagedObject;
import java.util.ArrayList;
import java.util.Date;

/**
 * Entry point for the application.
 *
 * @author Philip Herold
 */
public abstract class Main
{
    private static final String basePfad = System.getProperty("user.dir");

    private static String raeumePfad = basePfad + "/input/Datensatz 1/raeume.csv";
    private static String angebotePfad = basePfad + "/input/Datensatz 1/kunstwerke.csv";

    private static Thema fokusThema;
    
    private static int kostenGrenze = Integer.MAX_VALUE;

    private static final ArrayList<Planung> planungen = new ArrayList<Planung>();

    private static String exportDirPfad = basePfad + "/output/";

    /**
     * Application entry point.
     * Runs the Importer and the Exporter.
     * Creates a Planung and performs it.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        UI.main(new String[]{}); // start the UI
        
        System.out.println("UI loaded.");

    }

    public static void runImport() {
        try {
            Importer.importRaeume(raeumePfad);
            Importer.importAngebote(angebotePfad);
        } catch (Exception e) {
            //TODO UI error message
            e.printStackTrace();
        }
    }

    public static void runPlanung(String bezeichnung) {
        Planung planung = new Planung(bezeichnung, fokusThema, kostenGrenze);
        planung.planungDurchfuehren();
        planungen.add(planung);
    }

    public static void runExport() {
        runExport(planungen.get(planungen.size() - 1));
    }

    public static void runExport(Planung planung) {
        try {
            String exportPfad = exportDirPfad + "/" + planung.bezeichnung + "/";
            new File(exportPfad).mkdirs();
            Exporter.exportExample(planung, exportPfad + "example.csv");
        } catch (Exception e) {
            //TODO UI error message
            e.printStackTrace();
        }
    }

    public static String getRaeumePfad() {
        return raeumePfad;
    }
    
    public static void setRaeumePfad(String raeumePfad) {
        Main.raeumePfad = raeumePfad;
        System.out.println("Räume Pfad updated: " + raeumePfad);
    }
    
    public static String getAngebotePfad() {
        return angebotePfad;
    }
    
    public static void setAngebotePfad(String angebotePfad) {
        Main.angebotePfad = angebotePfad;
        System.out.println("Angebote Pfad updated: " + angebotePfad);
    }
    
    public static String getExportDirPfad() {
        return exportDirPfad;
    }
    
    public static void setExportDirPfad(String exportPfad) {
        Main.exportDirPfad = exportPfad;
        System.out.println("Export Pfad updated: " + exportPfad);
    }
    
    public static Planung[] getAllPlanungen() {
        return planungen.toArray(new Planung[planungen.size()]);
    }

    public static void setFokusThema(Thema fokusThema) {
        Main.fokusThema = fokusThema;
        System.out.println("Fokus Thema updated: " + fokusThema.bezeichnung);
    }

    public static void setKostenGrenze(int kostenGrenze) {
        Main.kostenGrenze = kostenGrenze;
        System.out.println("Kosten Grenze updated: " + kostenGrenze);
    }

    
    public static void mainDebug() throws Exception {
        // 1 Import
        Importer.importRaeume(raeumePfad);
        Importer.importAngebote(angebotePfad);
    
    
        // 1b DEBUG
        
        // Beispiel für Raumverwalter:
        System.out.print(RaumVerwalter.getInstance().getRaumByID(1).bezeichnung);
        // ...
    
        //
        
        
        // 2 Themenauswahl
        Planung planung = new Planung("DEBUG", ThemenVerwalter.getInstance().getAllThemen()[0], kostenGrenze);
    
        // 3 Planung
        planung.planungDurchfuehren();
    
        // 4 Export
        Exporter.exportExample(planung, exportDirPfad);
        
    }
}
