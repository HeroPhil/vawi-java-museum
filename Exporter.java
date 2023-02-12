import java.io.File; //Import der Java-Standardklasse "File" aus dem Paket "java.io". "io" ist ein Unterpaket des Pakets "java" 
import java.io.FileWriter; //Java-Standardklasse, die Daten in eine Datei schreibt ("Stift")
import java.io.IOException; //Ausnahme für alle Einlese- und Ausgabefehler
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList; //Arraylist = Sammlung von Elementen ohne Definition der Anzahl von
                            //Elementen. Nachdem die Klasse "ArrayList" importiert worden ist,
                            //können eigene Listen erstellt werden.

/**
 * Klasse "Exporter"
 * 
 * Die Klasse ist für den Export von CSV-Dateien (Museumsführer,
 * Logistikübersicht und Ausleihübersicht) zuständig.
 * 
 * Die Methode "writeFile" schreibt die Daten in eine Datei an einem definierten Ort.
 * 
 * Die Methode "export*" definiert, welche Daten in die Datei geschrieben werden.
 *
 * @author Philip Herold und Meike Ganzer
 */
public abstract class Exporter // Öffentliche, abstrakte Klasse Exporter, von der keine Objekte erstellt werden können.
                          
{
    /**
     * Diese Methode schreibt in eine CSV-Datei.
     * 
     * @param pfad   String Dateipfad // Eingabeparameter
     * @param zeilen String[][] Zu schreibende Zeilen (zweidimensionales Array) // Eingabeparameter
     */
    private static void writeFile(String pfad, String[][] zeilen) {
        // Diese Methode erstellt eine Datei uns speichert sie unter einem Dateipfad ab (String, der den Pfad zur Datei angibt).
        // Diese Methode ist private, sie kann nicht von außerhalb aufgerufen werden.
        // Diese Methode ist statisch, sie bezieht sich nicht auf ein Objekt und wird aus der Klasse aufgerufen.

        FileWriter fileWriter = null; // Hier ist der filewriter nur deklariert, noch nicht initialisiert. Für den Fall, dass es später ein Problem gibt, muss der Filewriter hier schon deklariert sein.
        try { // Mit try-catch-Anweisungen werden Programmfehler (Exceptions/Ausnahmen) abgefangen. Im Try-Block steht die Anweisung, die zu Fehlern führen kann.
            File file = new File(pfad); // Konstruktor ("new"), der ein neues Objekt mit dem Namen "file" (CSV-Datei) der Klasse "File" erzeugt.
            file.delete(); // Die alte CSV-Datei wird an dem angebenenen Pfad gelöscht.
            file.createNewFile(); // Eine neue Datei wird erstellt.

            fileWriter = new FileWriter(file);
            // Hier wird ein FileWriter-Objekt erzeugt ("Stift", jede Zeile des Arrays mit den Spalten in die Datei schreibt).

                                          
            String[] zeile; // Deklaration eines String-Arrays mit dem Namen "zeile"
            String spalte;  // Deklaration einer Variablen "spalte" vom Typ String
            for (int i = 0; i < zeilen.length; i++) { // Äußere For-Schleife für den Zeilenindex: Zeile für Zeile wird in die Datei geschrieben. 
                                                      // Initialisierung der Zählervariablen "i", Prüfung der Bedingung, "i" wird um 1 erhöht.
                                                      // Wenn die Bedingung "i < Länge der Array/Gesamtanzahl der Zeilen" erfüllt ist, wird 1 addiert.
                                                      // "length" ist ein Attribut der Klasse String, das die maximale Länge des Arrays ausgibt.
                                                      
                zeile = zeilen[i];                    // Speichert den Wert des Elements an der Stelle "i" im Array "zeilen" in einer Variablen namens "zeile".

                for (int j = 0; j < zeile.length; j++) { // Innere For-Schleife für den Spaltenindex
                    spalte = zeile[j];
                    byte[] bytes =spalte.getBytes(); // Der String spalte wird in ein Byte Array konvertiert.
                    String spalteencoded = new String(bytes, StandardCharsets.UTF_8);   //Es wird ein neuer String spalteencoded erzeugt, indem das Byte Array in einen String umgewandelt wird, 
                                                                                        //der UTF-8 zur Codierung als Argument erhält. 

                    if (j > 0)
                        fileWriter.append(','); // Die Werte werden durch Komma getrennt. (Append: "Stift" fügt Komma ein.)
                    fileWriter.append(spalteencoded);    // Hier wird eine Spalte eingefügt.
                }

                fileWriter.append("\n"); // Am Ende jeder Zeile wird ein Zeilenumbruch eingefügt.

            }

            fileWriter.flush(); // Der Puffer (Zwischenspeicher) wird geleert und die Datei geschlossen.

        } catch (Exception e) { // Exception-Ausnahme führt zum Programmabsturz
                                // io-Exception: Beispiel: Keine Schreibrechte für Datei, Datei kann nicht gelöscht werden etc.
                                // Im catch-block wird die Ausnahme abgefangen.
            // TO-dO: handle exception
            System.out.println(e);
            e.printStackTrace();
        } finally { // Der finally-Block wird auf jeden Fall ausgeführt (Aufräumarbeiten).
            try {   // 
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
     * 
     * @param Planung Planung mit Ausleihe[]
     * @param pfad    String
     */
    static void exportExample(Planung planung, String pfad) {

        // TO-do: Add sorting comparators to Ausleihe
        // ...
        //

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[] { "Raum_Name", "Position", "Ausstellungsstueck_Name", "Thema", "Kosten",
                "Attraktivität" });

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {

            String[] zeile = new String[] {
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
     * Diese Methode definiert, welche Daten in die CSV-Datei "Museumsfuehrer" geschrieben werden sollen. Dieser stellt eine Übersicht der Räume und den darin ausgestellten
     * wichtigsten Kunstwerken dar. Datengrundlage ist die Planung. Die Wichtigkeit eines Kunstwerks bemisst sich an dem Grad seiner Attraktivität.
     * 
     * @param planung Planung mit Ausleihe[] // Eingabeparameter: Die Daten werden beim Methodenaufruf an die Methode übergeben.
     * @param pfad    String
     */
    public static void exportMuseumsFuehrer(Planung planung, String pfad) { 

        // In der Methode "exportMuseumsFuehrer" wird eine neue ArrayList mit dem Namen "zeilen" erstellt, die eine Liste von String-Arrays enthält. 
        // Ein neues String-Array wird erstellt und in die "zeilen"-Liste eingefügt.
        // Dann wird eine Schleife durchlaufen, die alle Räume im "RaumVerwalter" durchläuft und für jeden Raum eine Liste der Ausleihen aus der übergebenen "Planung" erhält. 
        // Diese Liste wird in eine ArrayList umgewandelt und nach der Attraktivität sortiert. 
        // Für die ersten beiden Ausleihen in der sortierten Liste wird ein neues String-Array erstellt, das Informationen über den Raum, das ausgestellte Kunstwerk 
        // und das Museum enthält, das das Kunstwerk ausleiht. Dieses String-Array wird der "zeilen"-Liste hinzugefügt.

        ArrayList<String[]> zeilen = new ArrayList<String[]>(); // Array-List, die Strings aufnimmt.

        zeilen.add(new String[] { "Raumbezeichnung", "Bezeichnung des Kunstwerks", "Jahr", "Künstlername",
                "Leihgabe von" }); // Dem Array "zeilen" wird eine Kopfzeile hinzugefügt.

        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) { // Die Liste wird mit einer Foreach-Schleife durchlaufen. Singleton/Einzelstück stellt sicher, dass von einer Klasse nur ein Objekt existiert.
            Ausleihe[] ausleihenImRaum = planung.getAllAusleihenForRoom(raum); 

            // sort ausleihenImRaum by attraktivitaet by using an arraylist
            ArrayList<Ausleihe> ausleihenImRaumList = new ArrayList<Ausleihe>();
            for (Ausleihe ausleihe : ausleihenImRaum) {
                ausleihenImRaumList.add(ausleihe);
            }
            ausleihenImRaumList.sort((Ausleihe a1, Ausleihe a2) -> a2.angebot.ausstellungsstueck.attraktivitaet
                    - a1.angebot.ausstellungsstueck.attraktivitaet);

            for (int i = 0; i < Math.min(2, ausleihenImRaumList.size()); i++) {
                Ausleihe ausleihe = ausleihenImRaumList.get(i);

                String[] zeile = new String[] {
                        ausleihe.raum.bezeichnung,
                        ausleihe.angebot.ausstellungsstueck.bezeichnung,
                        ausleihe.angebot.ausstellungsstueck.jahr,
                        ausleihe.angebot.ausstellungsstueck.kuenstler,
                        ausleihe.angebot.partnerMuseum.name,
                };
                
                zeilen.add(zeile);
            }

        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][])); }
        // Die Methode "writeFile" schreibt die Inhalte der "zeilen"-Liste in eine Datei, die durch den "pfad"-Parameter angegeben wird.
        

    }

    /**
     * 
     * Diese Methode definiert, welche Daten in die CSV-Dateien "LogistikRaumUebersicht" und "LogistikAusleihenUebersicht" geschrieben werden sollen. 
     * Die Logistikübersicht enthält die Räume und den Aufstellort der Ausstellungsstücke inklusive der Informationen zu Raumtemperatur und Luftfeuchtigkeit.
     * 
     * @param planung Planung
     * @param pfad    Pfad zum Speicherort
     */
    static void exportLogistikUebersicht(Planung planung, String pfad) {

        // Die Methode hat zwei Parameter: planung vom Typ Planung, die die Planungsdaten enthält, und pfad vom Typ String, der den Dateipfad enthält, 
        // an dem die CSV-Datei gespeichert werden soll.
        // In der Methode wird exportLogistikAusleihenUebersicht mit planung und einem modifizierten pfad aufgerufen, um eine Ausleihübersicht in eine separate 
        // CSV-Datei zu exportieren. 
        // Anschließend wird exportLogistikRaumUebersicht mit planung und einem weiteren modifizierten pfad aufgerufen, um eine Raumübersicht 
        // in eine separate CSV-Datei zu exportieren.

        exportLogistikAusleihenUebersicht(planung, pfad.replace(".csv", ".ausleihen.csv"));
        exportLogistikRaumUebersicht(planung, pfad.replace(".csv", ".raum.csv"));
    }


    /**
     * Diese Methode definiert, welche Daten in die CSV-Datei "LogistikRaumübrsicht" geschrieben werden sollen (Raumbezeichnung, Min- und Max-Temperatur, Min- und Max-Luftfeuchtigkeit),
     * damit die Klimatisierung der Räume passend eingestellt werden kann.
     * 
     * @param planung Planung
     * @param pfad Pfad zum Speicherort
     */
    private static void exportLogistikRaumUebersicht(Planung planung, String pfad) {

        
        // Die Methode erstellt eine ArrayList "zeilen", die später die CSV-Daten aufnehmen wird.        
        // Dann werden die Spaltenüberschriften hinzugefügt: "Raumbezeichnung", "Minimaltemperatur", "Maximaltemperatur", "Minimale Luftfeuchtigkeit" und "Maximale Luftfeuchtigkeit"
        // Anschließend wird eine Schleife durchlaufen, in der für jeden Raum in der Raumverwaltung die Luftanforderungen abgerufen und zusammen mit dem Raumnamen als Zeile 
        // hinzugefügt werden.
        

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        
        zeilen.add(new String[] { "Raumbezeichnung",
                "Minimaltemperatur", "Maximaltemperatur", "Minimale Luftfeuchtigkeit", "Maximale Luftfeuchtigkeit" });
                // Dem Array "zeilen" wird eine Kopfzeile hinzugefügt.

        
        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) {
            
            double[] airRequirements = planung.getCurrentAirRequirementForRoom(raum);
            // Der Bedarf an Lufteigenschaften wird aus der aktuellen Planung geholt.

            String[] zeile = new String[] {
                    raum.bezeichnung,
                    String.format("%.2f", airRequirements[0]),
                    String.format("%.2f", Math.min(999, airRequirements[1])), // limit to 999
                    String.format("%.2f", airRequirements[2]),
                    String.format("%.2f", Math.min(100, airRequirements[3])) // limit to 100
            };

            zeilen.add(zeile);
        }

            writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
            //  Die Methode "writeFile" schreibt die Inhalte der "zeilen"-Liste in eine Datei, die durch den "pfad"-Parameter angegeben wird.
    }

    /**
     * Diese Methode definiert, welche Daten in die CSV-Datei  "LogistikAusleihenUebersicht" geschrieben werden sollen (Raumbezeichnung, Position, Bezeichnung und Art des Kunstwerks), 
     * damit die Mitarbeiter die Ausstellungsstücke in den richtigen Räumen aufstellen.
     * 
     * @param planung Planung
     * @param pfad    Dateipfad zum Speicherort
     */
    private static void exportLogistikAusleihenUebersicht(Planung planung, String pfad) {

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        zeilen.add(new String[] { "Raumbezeichnung", "Position", "Bezeichnung des Kunstwerks", "Art des Kunstwerks",});
        // Dem Array "Zeilen wird eine Kopfzeile hinzugefügt.

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {
            String[] zeile = new String[] {
                    ausleihe.raum.bezeichnung,
                    ausleihe.position.label,
                    ausleihe.angebot.ausstellungsstueck.bezeichnung,
                    ausleihe.angebot.ausstellungsstueck.getClass().getName(),

            };

            zeilen.add(zeile);
        }

        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
        //  Die Methode "writeFile" schreibt die Inhalte der "zeilen"-Liste in eine Datei, die durch den "pfad"-Parameter angegeben wird.
    }

    /**
     * Diese Methode definiert, welche Daten in die CSV-Datei  "Ausleihuebersicht" geschrieben werden sollen (Name des Partnermuseums, Bezeichnuns des Ausstellungsstücks, Kosten der Ausleihe).
     * 
     * @param planung Planung
     * @param pfad    Dateipfad mit Speicherort
     */
    static void exportAusleihUebersicht(Planung planung, String pfad) {

        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[] { "Leihgabe von", "Bezeichnung des Kunstwerks", "Ausleihkosten" });
        for (Ausleihe ausleihe : planung.getAllAusleihen()) {
            String[] zeile = new String[] {
                    ausleihe.angebot.partnerMuseum.name,
                    ausleihe.angebot.ausstellungsstueck.bezeichnung,
                    Integer.toString(ausleihe.angebot.kosten),
            };

            zeilen.add(zeile);
        }

        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
        //  Die Methode "writeFile" schreibt die Inhalte der "zeilen"-Liste in eine Datei, die durch den "pfad"-Parameter angegeben wird.
    }

}
