import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Write a description of class Exporter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Exporter
{
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
}
