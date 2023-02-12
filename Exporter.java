import java.io.File; //Import der Java-Standardklasse "File" aus dem Paket "java.io". "io" ist ein Unterpaket des Pakets "java" 
import java.io.FileWriter; //Java-Standardklasse, die Daten in eine Datei schreibt ("Stift")
import java.io.IOException; //Ausnahme für alle Einlese- und Ausgabefehler
import java.net.URI;
import java.util.ArrayList; //Arraylist = Sammlung von Elementen ohne Definition der Anzahl von
                            //Elementen. Nachdem die Klasse "ArrayList" importiert worden ist,
                            //können eigene Listen erstellt werden.

/**
 * Die Klasse "Exporter" ist für den Export von CSV-Dateien (Museumsführer,
 * Logistikübersicht und Ausleihübersicht) zuständig.
 * 
 * Die Methode "writeFile" schreibt die Daten in eine Datei an einem definierten Ort.
 * 
 * Die Methode "export*" definiert, welche Daten in die Datei geschrieben werden.
 *
 * @author Meike Ganzer
 */
public abstract class Exporter // Öffentliche, abstrakte Klasse Exporter, von der keine Objekte erstellt werden können.
                          
{
    /**
     * Diese Methode schreibt in eine CSV-Datei.
     * 
     * @param pfad   String Dateipfad //Eingabeparameter
     * @param zeilen String[][] Zu schreibende Zeilen (zweidimensionales Array) //Eingabeparameter
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

                    if (j > 0)
                        fileWriter.append(','); // Die Werte werden durch Komma getrennt. (Append: "Stift" fügt Komma ein.)
                    fileWriter.append(spalte);    // Hier wird eine Spalte eingefügt.
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
     * @param planung Planung mit Ausleihe[]
     * @param pfad    String
     */
    public static void exportMuseumsFuehrer(Planung planung, String pfad) { 
        ArrayList<String[]> zeilen = new ArrayList<String[]>(); //Array-List, die Strings aufnimmt.

        zeilen.add(new String[] { "Raumbezeichnung", "Bezeichnung des Kunstwerks", "Jahr", "Künstlername",
                "Leihgabe von" }); // Dem Array "zeilen" wird eine Kopfzeile hinzugefügt.

        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) { // Foreach-Schleife. Singleton/Einzelstück stellt sicher, dass von einer Klasse nur ein Objekt existiert.
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

        }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));

    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * This produces the "Logistikuebersicht" format: Room and place of installation
     * of the artwork with information on room temperature and humidity
     * 
     * @param planung Planung mit Ausleihe[]
     * @param pfad    String
     */
    static void exportLogistikUebersicht(Planung planung, String pfad) {
        exportLogistikAusleihenUebersicht(planung, pfad.replace(".csv", ".ausleihen.csv"));
        exportLogistikRaumUebersicht(planung, pfad.replace(".csv", ".raum.csv"));
    }

    private static void exportLogistikRaumUebersicht(Planung planung, String pfad) {
        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[] { "Raumbezeichnung",
                "Minimaltemperatur", "Maximaltemperatur", "Minimale Luftfeuchtigkeit", "Maximale Luftfeuchtigkeit" });

        // To-do: Temperatur und Luftfeuchtigkeit nur für Bilder

        for (Raum raum : RaumVerwalter.getInstance().getAllRaeume()) {
            // get airrequirements for current room from Planung
            double[] airRequirements = planung.getCurrentAirRequirementForRoom(raum);

            String[] zeile = new String[] {
                    raum.bezeichnung,
                    String.format("%.2f", airRequirements[0]),
                    String.format("%.2f", Math.min(999, airRequirements[1])), // limit to 999
                    String.format("%.2f", airRequirements[2]),
                    String.format("%.2f", Math.min(100, airRequirements[3])) // limit to 100
            };

            zeilen.add(zeile);
        }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
    }

    private static void exportLogistikAusleihenUebersicht(Planung planung, String pfad) {
        ArrayList<String[]> zeilen = new ArrayList<String[]>();

        // Kopfzeile
        zeilen.add(new String[] { "Raumbezeichnung", "Position", "Bezeichnung des Kunstwerks", "Art des Kunstwerks",});

        for (Ausleihe ausleihe : planung.getAllAusleihen()) {
            String[] zeile = new String[] {
                    ausleihe.raum.bezeichnung,
                    ausleihe.position.label,
                    ausleihe.angebot.ausstellungsstueck.bezeichnung,
                    ausleihe.angebot.ausstellungsstueck.getClass().getName(),

            };

            zeilen.add(zeile);
        }

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
    }

    /**
     * Export the given Ausleihe[] to a CSV file
     * This produces the "Ausleihuebersicht" format: Sorted by source: Which
     * artworks are borrowed where, what are the costs.
     * 
     * @param planung Planung mit Ausleihe[]
     * @param pfad    String
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

        // write to output file
        writeFile(pfad, zeilen.toArray(new String[zeilen.size()][]));
    }

}
