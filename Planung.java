import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Central class for the planning of the exhibition.
 *
 * @author Philip Herold
 */
public class Planung {

    /**
     * The thema to plan for.
     */
    public final Thema thema;

    /**
     * Flag if the planung is already done.
     */
    public boolean geplant;

    /**
     * The list of all ausleihen.
     * This list is only filled if the planung is already done.
     * Otherwise it is empty.
     * This represents the result of the planung.
     */
    private final ArrayList<Ausleihe> ausleihen;

    /**
     * Constructor
     * 
     * @param thema the thema to plan for
     */
    public Planung(Thema thema) {
        this.thema = thema;
        this.geplant = false;
        this.ausleihen = new ArrayList<Ausleihe>();
    }

    /**
     * Gibt alle geplanten Ausleihen zurück.
     * 
     * @return alle geplanten Ausleihen oder null wenn noch nicht geplant wurde.
     */
    public Ausleihe[] getAllAusleihen() {
        if (!geplant) {
            return null;
        }

        return ausleihen.toArray(new Ausleihe[ausleihen.size()]);
    }

    private void addAusleihe(Angebot angebot, Raum raum, Position position) {
        ausleihen.add(new Ausleihe(angebot, raum, position));
    }

    /**
     * Dies ist die primaere Logik-Methode.
     * Diese soll von der main Klasse aufgerufen werden um die Planung zu starte.
     */
    public void planungDurchfuehren() {

        // 1 Ensure all rooms are empty
        ausleihen.clear();

        Raum[] raeume = RaumVerwalter.getInstance().getAllRaeume();

        // 2 Go through all rooms, until half are at least filled with one exhibit which
        // satisfies the thema
        int erfolgreichGefuellteRaeume = 0;
        for (int i = 0; i < raeume.length; i++) {
            Raum raum = raeume[i];

            // 2.1 Find an exhibit which satisfies the thema
            Thema[] themaFilter = new Thema[] { thema };
            Angebot[] alleAngebote = AngebotVerwalter.getInstance()
                    .getAngeboteSortedByAttraktivitaetAndFiltered(themaFilter);
            ArrayList<Angebot> moeglicheAngebote = filterGebrauchteAngebote(alleAngebote);

            for (Angebot angebot : moeglicheAngebote) {
                if (versucheAusstellungsstueckZuPlatzieren(raum, angebot)) {
                    erfolgreichGefuellteRaeume++;
                    break;
                }
                ;
            }

            if (erfolgreichGefuellteRaeume >= raeume.length / 2.0) {
                break;
            }
            // TODO throw error / warning if after all rooms were tried no half of the rooms
            // are filled
        }

        // 3 Go through all rooms, try to fit as many ausstellungsstuecke as possible
        Angebot[] alleAngebote = AngebotVerwalter.getInstance().getAllAngeboteSortedByAttraktivitaet();
        ArrayList<Angebot> moeglicheAngebote = filterGebrauchteAngebote(alleAngebote);

        for (Raum raum : raeume) {

            if (checkIfRaumIsFilledWithKunstinstallation(raum)) {
                continue;
            }

            for (int i = moeglicheAngebote.size() - 1; i >= 0; i--) {
                Angebot angebot = moeglicheAngebote.get(i);
                if (versucheAusstellungsstueckZuPlatzieren(raum, angebot)) {
                    moeglicheAngebote.remove(angebot);

                    // if the room is filled with a Kunstinstallation, then no other exhibit can be added
                    if (angebot.ausstellungsstueck instanceof Kunstinstallation) {
                        break;
                    }
                }
            }
        }

        this.geplant = true;
    }

    private boolean versucheAusstellungsstueckZuPlatzieren(Raum raum, Angebot angebot) {

        boolean passt = false;

        // wenn es ein Bild ist, dann teste alle waende ob es passt
        if (angebot.ausstellungsstueck instanceof Bild) {
            Bild bild = (Bild) angebot.ausstellungsstueck;

            // teste raum
            if (!checkIfAirRequirementIsMet(bild, getCurrentAirRequirementForRoom(raum))) {
                // if not met, skip the image
                System.out.println(
                        "Bild Id: " + angebot.id + " passt nicht in Raum: " + raum.id + " wegen Luftanforderung");
                return passt;
            }

            Ausleihe[] alleAusleihenVonBilderImRaum = getAllAusleihenWithAusstellungsstueckForRoom(Bild.class, raum);

            // teste alle waende
            for (Position position : Position.getWandPositionen()) {

                Bild[] bestehendeBilder = Arrays.asList(alleAusleihenVonBilderImRaum).stream()
                .filter(ausleihen -> ausleihen.position == position)
                .map(ausleihe -> ausleihe.angebot.ausstellungsstueck).toArray(Bild[]::new);

                if (RaumVerwalter.checkIfBildFitsToWall(raum, position, bild, bestehendeBilder)) {
                    addAusleihe(angebot, raum, position);
                    passt = true;
                    break;
                }
                System.out.println(
                        "Bild Id: " + angebot.id + " passt nicht in Raum: " + raum.id + " an Wand: " + position.label);
            }

            // wenn es ein Kunstgegenstand ist, dann pruefe ob dieser in den Raum passt.
        } else if (angebot.ausstellungsstueck instanceof Kunstgegenstand) {
            Kunstgegenstand kunstgegenstand = (Kunstgegenstand) angebot.ausstellungsstueck;

            Ausleihe[] bestehendeAusleihenVonKunstgegenstaende = getAllAusleihenWithAusstellungsstueckForRoom(Kunstgegenstand.class, raum);
            Kunstgegenstand[] bestehendeKunstgegenstaende = Arrays.asList(bestehendeAusleihenVonKunstgegenstaende).stream()
                    .map(ausleihe -> ausleihe.angebot.ausstellungsstueck).toArray(Kunstgegenstand[]::new);

            if (RaumVerwalter.checkIfGegenstandFitsOnFloor(raum, kunstgegenstand, bestehendeKunstgegenstaende)) { 
                addAusleihe(angebot, raum, Position.BODEN);
                passt = true;
            }
            System.out.println("Kunstgegenstand Id: " + angebot.id + " passt nicht in Raum: " + raum.id);

            // wenn es eine Kunstinstallation ist, dann pruefe ob diese in den Raum passt
        } else if (angebot.ausstellungsstueck instanceof Kunstinstallation) {
            Kunstinstallation kunstinstallation = (Kunstinstallation) angebot.ausstellungsstueck;

            Ausleihe[] bestehendeAusleihenVonKunstgegenstaende = getAllAusleihenForRoom(raum);
            if (bestehendeAusleihenVonKunstgegenstaende.length > 0) {
                System.out.println("Kunstinstallation Id: " + angebot.id + " passt nicht in Raum: " + raum.id + " weil Raum schon belegt ist");
                return passt;
            }

            if (RaumVerwalter.checkIfGegenstandFitsOnFloor(raum, kunstinstallation, new Ausstellungsstueck3D[]{})) {
                addAusleihe(angebot, raum, Position.VOLLKOMMEN);
                passt = true;
            }
            System.out.println("Kunstinstallation Id: " + angebot.id + " passt nicht in Raum: " + raum.id);
        }

        return passt;
    }

    private ArrayList<Angebot> filterGebrauchteAngebote(Angebot[] angebote) {
        return filterGebrauchteAngebote(new ArrayList<Angebot>(Arrays.asList(angebote)));
    }

    private ArrayList<Angebot> filterGebrauchteAngebote(ArrayList<Angebot> angebote) {
        List<Angebot> gebrauchteAngebote = ausleihen.stream().map(ausleihe -> ausleihe.angebot)
                .collect(Collectors.toList());
        angebote.removeAll(gebrauchteAngebote);
        return angebote;
    }

    private boolean checkIfRaumIsFilledWithKunstinstallation(Raum raum) {
        // check if there is any ausleihe with raum ans position vollkommen
        return ausleihen.stream().anyMatch(ausleihe -> ausleihe.raum == raum
                && ausleihe.position == Position.VOLLKOMMEN);
    }

    private Ausleihe[] getAllAusleihenForRoom(Raum raum) {
        return ausleihen.stream().filter(ausleihe -> ausleihe.raum == raum).toArray(Ausleihe[]::new);
    }

    private Ausleihe[] getAllAusleihenWithAusstellungsstueckForRoom(Class<? extends Ausstellungsstueck> ausstellungsstueckClass, Raum raum) {
        return Arrays.asList(getAllAusleihenForRoom(raum)).stream()
                .filter(ausleihe -> ausstellungsstueckClass.isInstance(ausleihe.angebot.ausstellungsstueck)).toArray(Ausleihe[]::new);
    }

    private double[] getCurrentAirRequirementForRoom(Raum raum) {
        double minTemp = 0;
        double maxTemp = Double.MAX_VALUE;
        double minFeuchtigkeit = 0;
        double maxFeuchtigkeit = Double.MAX_VALUE;

        Ausleihe[] ausleihen = getAllAusleihenWithAusstellungsstueckForRoom(Bild.class, raum);
        for (Ausleihe ausleihe : ausleihen) {
            Bild bild = (Bild) ausleihe.angebot.ausstellungsstueck;
            minTemp = Math.max(minTemp, bild.minTemp);
            maxTemp = Math.min(maxTemp, bild.maxTemp);
            minFeuchtigkeit = Math.max(minFeuchtigkeit, bild.minFeuchtigkeit);
            maxFeuchtigkeit = Math.min(maxFeuchtigkeit, bild.maxFeuchtigkeit);
        }

        return new double[] { minTemp, maxTemp, minFeuchtigkeit, maxFeuchtigkeit };
    }

    private boolean checkIfAirRequirementIsMet(Bild bild, double[] airRequirement) {
        double minTemp = airRequirement[0];
        double maxTemp = airRequirement[1];
        double minFeuchtigkeit = airRequirement[2];
        double maxFeuchtigkeit = airRequirement[3];

        boolean tempCheck = bild.minTemp <= maxTemp && bild.maxTemp >= minTemp;
        boolean feuchtigkeitCheck = bild.minFeuchtigkeit <= maxFeuchtigkeit && bild.maxFeuchtigkeit >= minFeuchtigkeit;

         if (!tempCheck) {
            System.out.println("Bild: " + bild.minTemp + " - " + bild.maxTemp
                    + "\nRaum: " + minTemp + " - " + maxTemp);
         }
         if (!feuchtigkeitCheck) {
            System.out.println("Bild: " + bild.minFeuchtigkeit + " - " + bild.maxFeuchtigkeit
                    + "\nRaum: " + minFeuchtigkeit + " - " + maxFeuchtigkeit);
             
         }

         return tempCheck && feuchtigkeitCheck;
    }

}
