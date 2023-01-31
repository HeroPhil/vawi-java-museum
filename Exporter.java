import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to export data to multiple different CSV file
 *
 * @author Philip Herold
 */
public abstract class Exporter
{
    /**
     * Internal Method to write a CSV file
     * @param pfad String the path to the file
     * @param zeilen String[][] the lines to write
     */
    private static void writeFile(String pfad, String[][] zeilen) {
        FileWriter fileWriter = null;
        try {
            File file = new File(pfad);
            file.delete(); // delete old file
            file.createNewFile(); // create new file

            fileWriter = new FileWriter(file);

            String[] zeile;
            String spalte;
            for (int i = 0; i < zeilen.length; i++) {
                zeile = zeilen[i];

                for (int j = 0; j < zeile.length; j++) {
                    spalte = zeile[j];

                    if (j > 0) fileWriter.append(',');
                    fileWriter.append(spalte);
                }

                fileWriter.append("\n");

            }

            fileWriter.flush();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null)
                fileWriter.close();
            } catch (IOException e) {
                // ignore
            }

        }
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this is only an example format
     * @param Planung Planung mit Ausleihe[] 
     * @param pfad String
     */
    static void exportExample(Planung planung, String pfad) {

        // TODO add sorting comparators to Ausleihe
        // ...
        //

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[]{"Raum_Name", "Position", "Ausstellungsstueck_Name", "Thema", "Kosten", "Attraktivität"});

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {

            String[] zeile = new String[]{
                ausleihe.raum.bezeichnung,
                ausleihe.position.label,
                ausleihe.angebot.ausstellungsstueck.bezeichnung,
                ausleihe.angebot.ausstellungsstueck.thema.bezeichnung,
                Integer.toString(ausleihe.angebot.kosten),
                Integer.toString(ausleihe.angebot.ausstellungsstueck.attraktivität)
            };

            zeilen.add(zeile);
        }


        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Museumsfuehrer" format
     * @param planung Planung mit Ausleihe[]
     * @param pfad String
     */
    static void exportMuseumsFuehrer(Planung planung, String pfad) {
        
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Logistikuebersicht" format
     * @param planung Planung mit Ausleihe[]  
     * @param pfad String
     */
    static void exportLogistikUebersicht(Planung planung, String pfad) {
        
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Ausleihuebersicht" format
     * @param planung Planung mit Ausleihe[]
     * @param pfad String
     */
    static void exportAusleihUebersicht(Planung planung, String pfad) {
        
    }
}
