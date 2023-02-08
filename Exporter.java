import java.io.File; //Import der Java-Standardklasse "File" aus dem Paket "java.io". "io" ist ein Unterpaket des Pakets "java" 
import java.io.FileWriter; //Java-Standardklasse, die Daten in eine Datei schreibt ("Stift")
import java.io.IOException; //Ausnahme für alle Einlese- und Ausgabefehler
import java.util.ArrayList; //Arraylist = Sammlung von Elementen ohne Definition der Anzahl von
                            //Elementen. Nachdem die Klasse "ArrayList" importiert worden ist,
                            //können eigene Listen erstellt werden.

/**
 * Die Klasse "Exporter" ist für den Export von CSV-Dateien (Museumsführer, Logistikübersicht und Ausleihübersicht) zuständig. 
 * Die Methode "writeFile" schreibt die Daten in eine Datei an einem definierten Ort (Pfad).
 * Die Methode "export*" definiert, welche Daten in die Datei geschrieben werden.
 *
 * @author Meike Ganzer
 */
public abstract class Exporter //Öffentliche, abstrakte Klasse Exporter, von der keine Objekte erstellt werden können.

{
    /**
     * Diese Methode schreibt in eine CSV-Datei.
     * @param pfad String Dateipfad     //Eingabeparameter
     * @param zeilen String[][] Zu schreibende Zeilen (zweidimensionales Array)   //Eingabeparameter
     */
    private static void writeFile(String pfad, String[][] zeilen) { 
        //Diese Methode ist private, sie kann nicht von einem anderen Objekt aufgerufen werden.
        //Diese Methode ist statisch, sie bezieht sich nicht auf ein Objekt und wird aus der Klasse aufgerufen.

        FileWriter fileWriter = null; //Hier ist der filewriter nur deklariert, noch nicht initialisiert.
        try {
            File file = new File(pfad); //Hier wird ein neues Objekt erzeugt ("new").
            file.delete(); // Die alte Datei wird gelöscht.
            file.createNewFile(); // Eine neue Datei wird erstellt.

            fileWriter = new FileWriter(file); 
            //Hier wird ein FileWriter-Objekt erzeugt und jede Zeile des Arrays mit den Spalten in die Datei geschrieben. 
            
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

            fileWriter.flush(); //Der Puffer geflusht und die Datei geschlossen.

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


        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) {
            Ausleihe[] ausleihenImRaum = planung.getAllAusleihenForRoom(raum);

            // sort ausleihenImRaum by attraktivitaet by using an arraylist
            ArrayList<Ausleihe> ausleihenImRaumList = new ArrayList<Ausleihe>();
            for (Ausleihe ausleihe : ausleihenImRaum) {
                ausleihenImRaumList.add(ausleihe);
            }
            ausleihenImRaumList.sort((Ausleihe a1, Ausleihe a2) -> a2.angebot.ausstellungsstueck.attraktivitaet - a1.angebot.ausstellungsstueck.attraktivitaet);

            for (int i = 0; i < Math.min(2, ausleihenImRaumList.size()); i++) {
                Ausleihe ausleihe = ausleihenImRaumList.get(i);
    
                String[] zeile = new String[]{
                    ausleihe.raum.bezeichnung,
                    ausleihe.angebot.ausstellungsstueck.bezeichnung,
                    ausleihe.angebot.ausstellungsstueck.jahr,
                    ausleihe.angebot.ausstellungsstueck.kuenstler,
                    ausleihe.angebot.partnerMuseum.name,
                };
    
                zeilen.add(zeile);
            }
            
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
                                             
               };

            zeilen.add(zeile);
            }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

        // TODO add temparature and humidity for pictures
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

