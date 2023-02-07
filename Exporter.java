import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to export data to multiple different CSV file
 *
 * @author Meike Ganzer
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
     * Exports the given Ausleihe[] to a CSV file
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
                Integer.toString(ausleihe.angebot.ausstellungsstueck.attraktivitaet)
            };

            zeilen.add(zeile);
        }


        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * This produces the "Museumsfuehrer" format: Overview of the rooms with the most important works of art located in them 
     * @param planung Planung mit Ausleihe[]
     * @param pfad String
     */
    static void exportMuseumsFuehrer(Planung planung, String pfad) {
    
        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[]{"Raumbezeichnung", "Bezeichnung des Kunstwerks", "Jahr", "Künstlername", "Leihgabe von"});

        //To-do: Attraktivität (= Wichtigkeit) definieren und als Selektionskriterium verwenden

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {

            String[] zeile = new String[]{
                ausleihe.raum.bezeichnung,
                ausleihe.angebot.ausstellungsstueck.bezeichnung,
                ausleihe.angebot.ausstellungsstueck.jahr,
                ausleihe.angebot.ausstellungsstueck.kuenstler,
                ausleihe.angebot.partnerMuseum.name,
            };

            zeilen.add(zeile);
        }


        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

    
        
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * This produces the "Logistikuebersicht" format: Room and place of installation of the artwork with information on room temperature and humidity 
     * @param planung Planung mit Ausleihe[]  
     * @param pfad String
     */
    static void exportLogistikUebersicht(Planung planung, String pfad) {
                
        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[]{"Raumbezeichnung", "Position", "Bezeichnung des Kunstwerks", "Art des Kunstwerks", "Minimaltemperatur", "Maximaltemperatur", "Minimale Luftfeuchtigkeit", "Maximale Luftfeuchtigkeit"});
        
        //To-do: Temperatur und Luftfeuchtigkeit nur für Bilder

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {
            String[] zeile = new String[]{
                "", //ausleihe.raum.bezeichnung,
                "", //ausleihe.position.label,
                "", //ausleihe.angebot.ausstellungsstueck.bezeichnung,
                "", //Character.toString(ausleihe.angebot.ausstellungsstueck.art),
                "", //Integer.toString(ausleihe.angebot.ausstellungsstueck.bild.minFeuchtigkeit),
                "", //Integer.toString(ausleihe.angebot.ausstellungsstueck.bild.maxTemp),
                "", //Integer.toString(ausleihe.angebot.ausstellungsstueck.bild.minFeuchtigkeit),
                "", //Integer.toString(ausleihe.angebot.ausstellungsstueck.bild.maxFeuchtigkeit),
                };

            zeilen.add(zeile);
            }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
    }
    



    /**
     * Export the given Ausleihe[] to a CSV file
     * This produces the "Ausleihuebersicht" format: Sorted by source: Which artworks are borrowed where, what are the costs.
     * @param planung Planung mit Ausleihe[]
     * @param pfad String
     */
    static void exportAusleihUebersicht(Planung planung, String pfad) {
        
        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[]{"Leihgabe von", "Bezeichnung des Kunstwerks", "Ausleihkosten"});
        for (Ausleihe ausleihe : planung.getAllAusleihen()) {
            String[] zeile = new String[]{
                ausleihe.angebot.partnerMuseum.name,
                ausleihe.angebot.ausstellungsstueck.bezeichnung,
                Integer.toString(ausleihe.angebot.kosten),
                };

            zeilen.add(zeile);
            }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
    }

}

