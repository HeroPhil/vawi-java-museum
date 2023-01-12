import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Liest Dateien ein und gibt Zeilen weiter an Verwaltungsklassen
 *
 * @author Philip Herold
 */
public abstract class Importer {

   /**
    * the Delimiter used in the CSV files
    */
   public static final char delimiter = ',';

   /**
    * Indices Mappings for the Angebot CSV file
    */
   private static final int ANGEBOT_ID_INDEX = 0;
   private static final int ANGEBOT_ART_INDEX = 1;
   private static final int ANGEBOT_BEZEICHNUNG_INDEX = 2;
   private static final int ANGEBOT_KUENSTLER_INDEX = 3;
   private static final int ANGEBOT_JAHR_INDEX = 4;
   private static final int ANGEBOT_THEMA_INDEX = 5;
   private static final int ANGEBOT_ATTRAKTIVITÄT_INDEX = 6;
   private static final int ANGEBOT_KOSTEN_INDEX = 7;
   private static final int ANGEBOT_NAME_INDEX = 8;
   private static final int ANGEBOT_ANSCHRIFT_INDEX = 9;
   private static final int ANGEBOT_HOEHE_INDEX = 10;
   private static final int ANGEBOT_BREITE_INDEX = 11;
   private static final int ANGEBOT_LÄNGE_INDEX = 12;
   private static final int ANGEBOT_GEWICHT_INDEX = 13;
   private static final int ANGEBOT_TEMP_MIN_INDEX = 12;
   private static final int ANGEBOT_TEMP_MAX_INDEX = 13;
   private static final int ANGEBOT_LUFTFEUCHTIGKEIT_MIN_INDEX = 14;
   private static final int ANGEBOT_LUFTFEUCHTIGKEIT_MAX_INDEX = 15;

   /**
    * Indices Mappings for the Raum CSV file
    */
   private static final int RAUM_ID_INDEX = 0;
   private static final int RAUM_BEZEICHNUNG_INDEX = 1;
   private static final int RAUM_LÄNGE_INDEX = 2;
   private static final int RAUM_BREITE_INDEX = 3;
   private static final int RAUM_HOEHE_INDEX = 4;
   private static final int RAUM_TUER_NORD_INDEX = 5;
   private static final int RAUM_TUER_OST_INDEX = 6;
   private static final int RAUM_TUER_SUED_INDEX = 7;
   private static final int RAUM_TUER_WEST_INDEX = 8;

   /**
    * Internal Method to read a CSV file
    * @param pfad Pfad zur Datei
    * @return String[][] mit den Zeilen und Felder der Datei
    */
   public static String[][] readFile(String pfad) {
      BufferedReader bufferedReader = null;
      try {
         File file = new File(pfad);
         System.out.println(file.canRead());
         FileReader fileReader = new FileReader(file);
         bufferedReader = new BufferedReader(fileReader);

         ArrayList<String[]> lines = new ArrayList<>();

         String line;
         String[] splittedLine;

         while ((line = bufferedReader.readLine()) != null) {
            int count = (int) line.chars().filter(ch -> ch == delimiter).count() + 1;
            splittedLine = line.split("" + delimiter, count);
            lines.add(splittedLine);

            // optionale Ausgabe:
            for (String tempStr : splittedLine) {
               System.out.print(tempStr + " ");
            }
            System.out.print(splittedLine.length);
            System.out.println();
         }

         String[][] result = new String[lines.size()][lines.get(0).length];
         result = lines.toArray(result);
         return result;
      } catch (IOException e) {
         System.out.println(e);
         e.printStackTrace();
      } finally {
         if (bufferedReader != null) {
            try {
               if (bufferedReader != null)
               bufferedReader.close();
            } catch (IOException e) {
               // ignore
            }
         }
      }
      return null;
   }

   /**
    * Importiert die Räume aus der CSV Datei
    * @param pfad Pfad zur Datei
    */
   public static void importRaeume(String pfad) {
      for (String[] zeile : readFile(pfad)) {
         Raum neuerRaum = new Raum(
               Integer.parseInt(zeile[RAUM_ID_INDEX]),
               zeile[Importer.RAUM_BEZEICHNUNG_INDEX],
               Integer.parseInt(zeile[RAUM_LÄNGE_INDEX]),
               Integer.parseInt(zeile[RAUM_BREITE_INDEX]),
               Integer.parseInt(zeile[RAUM_HOEHE_INDEX]),
               Integer.parseInt(zeile[RAUM_TUER_NORD_INDEX]),
               Integer.parseInt(zeile[RAUM_TUER_OST_INDEX]),
               Integer.parseInt(zeile[RAUM_TUER_SUED_INDEX]),
               Integer.parseInt(zeile[RAUM_TUER_WEST_INDEX]));

         RaumVerwalter.getInstance().addRaum(neuerRaum);
      }
   }

   /**
    * Importiert die Angebote aus der CSV Datei
    * @param pfad Pfad zur Datei
    * @throws Exception wenn das Angebot ein unbekanntes Kunstwerk beinhaltet
    */
   public static void importAngebote(String pfad) throws Exception{
      for (String[] zeile : readFile(pfad)) {

         Ausstellungsstueck neuesAusstellungsstueck;

         Thema thema = ThemenVerwalter.getInstance().getOrAddThema(zeile[ANGEBOT_THEMA_INDEX]);

         switch (zeile[ANGEBOT_ART_INDEX]) {
            case "B":
               neuesAusstellungsstueck = new Bild(
                  zeile[ANGEBOT_BEZEICHNUNG_INDEX],
                  zeile[ANGEBOT_KUENSTLER_INDEX],
                  zeile[ANGEBOT_JAHR_INDEX],
                  thema,
                  Integer.parseInt(zeile[ANGEBOT_ATTRAKTIVITÄT_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_HOEHE_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_BREITE_INDEX]),
                  Integer.parseInt(zeile[zeile.length -1]),
                  Integer.parseInt(zeile[ANGEBOT_TEMP_MIN_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_LUFTFEUCHTIGKEIT_MIN_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_TEMP_MAX_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_LUFTFEUCHTIGKEIT_MAX_INDEX])
               );
               break;
            case "G":
               neuesAusstellungsstueck = new Kunstgegenstand(
                  zeile[ANGEBOT_BEZEICHNUNG_INDEX],
                  zeile[ANGEBOT_KUENSTLER_INDEX],
                  zeile[ANGEBOT_JAHR_INDEX],
                  thema,
                  Integer.parseInt(zeile[ANGEBOT_ATTRAKTIVITÄT_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_HOEHE_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_BREITE_INDEX]),
                  Integer.parseInt(zeile[zeile.length -1]),
                  Integer.parseInt(zeile[ANGEBOT_LÄNGE_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_GEWICHT_INDEX])
               );
               break;
            case "I":
               neuesAusstellungsstueck = new Kunstinstallation(
                  zeile[ANGEBOT_BEZEICHNUNG_INDEX],
                  zeile[ANGEBOT_KUENSTLER_INDEX],
                  zeile[ANGEBOT_JAHR_INDEX],
                  thema,
                  Integer.parseInt(zeile[ANGEBOT_ATTRAKTIVITÄT_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_HOEHE_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_BREITE_INDEX]),
                  Integer.parseInt(zeile[zeile.length -1]),
                  Integer.parseInt(zeile[ANGEBOT_LÄNGE_INDEX]),
                  Integer.parseInt(zeile[ANGEBOT_GEWICHT_INDEX])
               );
               break;
               default:
                throw new Exception("Unbekanntes Kunstobjekt");
               
         }

         PartnerMuseum partnerMuseum = PartnerMuseumsVerwalter.getInstance().getOrAddPartnerMuseum(
            zeile[ANGEBOT_NAME_INDEX], zeile[ANGEBOT_ANSCHRIFT_INDEX]
            );

         Angebot neuesAngebot = new Angebot(
            Integer.parseInt(zeile[ANGEBOT_ID_INDEX]),
            Integer.parseInt(zeile[ANGEBOT_KOSTEN_INDEX]),
            neuesAusstellungsstueck,
            partnerMuseum
         );

         AngebotVerwalter.getInstance().addAngebot(neuesAngebot);
      }
   }
}
