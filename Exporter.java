import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to export data to multiple different CSV file
 *
 * @author Philip
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
            file.createNewFile(); // ensure file exists

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
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                // ignore
            }

        }
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this is only an example format
     * @param ausleihen Ausleihe[] 
     * @param pfad String
     */
    static void exportExample(Ausleihe[] ausleihen, String pfad) {

        // TODO add sorting comparators to Ausleihe
        // ...
        //

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[]{"Raum_Name", "Ausstellungsstueck_Name", "Kosten"});

        for (Ausleihe ausleihe : ausleihen) {

            String[] zeile = new String[]{
                ausleihe.raum.bezeichnung,
                ausleihe.angebot.ausstellungsstueck.bezeichnung,
                Integer.toString(ausleihe.angebot.kosten),
            };

            zeilen.add(zeile);
        }


        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Museumsfuehrer" format
     * @param ausleihen Ausleihe[]
     * @param pfad String
     */
    static void exportMuseumsFuehrer(Ausleihe[] ausleihen, String pfad) {
        
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Logistikuebersicht" format
     * @param ausleihen Ausleihe[]  
     * @param pfad String
     */
    static void exportLogistikUebersicht(Ausleihe[] ausleihen, String pfad) {
        
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * this produces the "Ausleihuebersicht" format
     * @param ausleihen
     * @param pfad
     */
    static void exportAusleihUebersicht(Ausleihe[] ausleihen, String pfad) {
        
    }
}
