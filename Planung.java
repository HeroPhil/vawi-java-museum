import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Central class for the planning of the exhibition.
 *
 * @author Philip Herold
 */
public class Planung
{

    /**
     * The thema to plan for.
     */
    public final Thema thema;

    /**
     * The maximum costs for the planung.
     */
    public final int kostenGrenze;

    /**
     * Flag if the planung is already done.
     */
    public boolean geplant;

    /**
     * The displayname of the planung.
     */
    public final String bezeichnung;

    /**
     * The list of all ausleihen.
     * This list is only filled if the planung is already done.
     * Otherwise it is empty.
     * This represents the result of the planung.
     */
    private final ArrayList<Ausleihe> ausleihen;

    private Ausleihe entbehrlicheAusleihe;

    /**
     * Constructor
     * 
     * @param thema the thema to plan for
     */
    public Planung(String bezeichnung, Thema thema, int kostenGrenze) {
        if (bezeichnung == "") {
            bezeichnung = new Date().toString();
        }
        this.bezeichnung = bezeichnung;
        this.thema = thema;
        this.kostenGrenze = kostenGrenze;
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

        System.out.println("Starte Planung (" + bezeichnung + ") für Thema " + thema.bezeichnung + " mit Kosten Grenze " + kostenGrenze + "€");

        // 1 Ensure all rooms are empty
        ausleihen.clear();

        Raum[] raeume = RaumVerwalter.getInstance().getAllRaeume();

        // 2 Go through all rooms and fill them with aussstellungsstuecke
        // overall:
        int raeumeMitMindestThemenAnforderungErfuellt = 0;
        // per room
        Set<Thema> verwendeteThemen;
        // per setup;
        Thema[] themaFilter;
        raumLoop: for (int i = 0; i < raeume.length; i++) {
            Raum raum = raeume[i];

            // 2.0 Reset for new Room
            themaFilter = ThemenVerwalter.getInstance().getAllThemen();
            verwendeteThemen = new HashSet<Thema>();

            // there are 3 setups to consider in order:
            // 0: try to satisfy all mindestanforderung (at least one for thema in half of
            // the rooms)
            // 1: use all themen (do not filter themen)
            // 2: only used themen (do filter themen)
            setupLoop: for (int setup = 0; setup < 3; setup++) {

                // 2.1 update setup
                switch (setup) {
                    case 0:
                        // if there are still rooms which require the specific thema, then only use this
                        if (raeumeMitMindestThemenAnforderungErfuellt < raeume.length / 2.0) {
                            themaFilter = new Thema[] { thema };
                            break;
                        }
                        continue setupLoop; // next setup;
                    case 1:
                        themaFilter = ThemenVerwalter.getInstance().getAllThemen();
                        break;
                    // use only used themen
                    case 2:
                        themaFilter = verwendeteThemen.toArray(new Thema[verwendeteThemen.size()]);
                        break;

                }
                // 2.2 get angebote
                Angebot[] alleAngebote = AngebotVerwalter.getInstance()
                        .getAngeboteSortedByAttraktivitaetAndFilteredbyThema(themaFilter);
                ArrayList<Angebot> moeglicheAngebote = filterGebrauchteAngebote(alleAngebote);

                // 2.3 try to place as many as possible
                for (Angebot angebot : moeglicheAngebote) {
                    System.out.println("Try to place " + angebot.id + "(" + angebot.ausstellungsstueck.getClass().getName() + " ; " + angebot.ausstellungsstueck.thema.bezeichnung + ") in "
                            + raum.id + " with setup " + setup);
                    if (versucheAusstellungsstueckZuPlatzieren(raum, angebot)) { // if successfully placed
                        System.out.println("SUCCESS");
                        verwendeteThemen.add(angebot.ausstellungsstueck.thema); // remember used thema
                        // do we need to continue with next setup? or are we done with the room?
                        boolean continueWithNextSetup = false;
                        boolean continueWithNextRaum = false;
                        switch (setup) {
                            case 0:
                                raeumeMitMindestThemenAnforderungErfuellt++;
                                continueWithNextSetup = true;
                                // no break because we also might to continue with next raum
                            case 1:
                                // check if three different Themen are already used > continue to next setup and
                                // only use them
                                if (verwendeteThemen.size() >= 3) {
                                    continueWithNextSetup = true;
                                }
                            default:
                                // ensure that only one Kunstinstallation is placed in a room alone
                                if (angebot.ausstellungsstueck instanceof Kunstinstallation) {
                                    continueWithNextRaum = true;
                                    break; // dont need to remember used thema if raum is done
                                }
                        }
                        if (continueWithNextRaum) {
                            continue raumLoop;
                        }
                        if (continueWithNextSetup) {
                            continue setupLoop;
                        }
                    }
                }

                // continue with next setup

            }

            // continue with next raum

        }

        this.geplant = true;
        // TODO print warning if mindestanforderung is not met

        // 3 Reduziere die Koste, falls noetig
        if (reduceCost()) {
            System.out.println("Kosten wurden reduziert");
        }

    }

    /**
     * Versucht das angebot in den raum zu platzieren.
     * Fueht entsprechende Checks fuer unterschiedliche Ausstellungsstuecke durch.
     * Bei Erfolg wird eine neue Ausleihe hinzugefuegt.
     * 
     * @param raum    Der Raum, in den das Ausstellungsstueck platziert werden soll.
     * @param angebot Das Angebot, welches platziert werden soll.
     * @return true wenn das Ausstellungsstueck erfolgreich platziert wurde.
     */
    private boolean versucheAusstellungsstueckZuPlatzieren(Raum raum, Angebot angebot) {
        // TODO subject to optimization and code cleanup

        //boolean passt = false;

        // wenn es ein Bild ist, dann teste alle waende ob es passt
        if (angebot.ausstellungsstueck instanceof Bild) {
            Bild bild = (Bild) angebot.ausstellungsstueck;

            // teste raum
            if (!checkIfAirRequirementIsMet(bild, getCurrentAirRequirementForRoom(raum))) {
                // if not met, skip the image
                System.out.println(
                        "Bild Id: " + angebot.id + " passt nicht in Raum: " + raum.id + " wegen Luftanforderung");
                return false;
            }

            Ausleihe[] alleAusleihenVonBilderImRaum = getAllAusleihenWithAusstellungsstueckForRoom(Bild.class, raum);

            // teste alle waende
            for (Position position : Position.getWandPositionen()) {

                Bild[] bestehendeBilder = Arrays.asList(alleAusleihenVonBilderImRaum).stream()
                        .filter(ausleihen -> ausleihen.position == position)
                        .map(ausleihe -> ausleihe.angebot.ausstellungsstueck).toArray(Bild[]::new);

                if (RaumVerwalter.checkIfBildFitsToWall(raum, position, bild, bestehendeBilder)) {
                    addAusleihe(angebot, raum, position);
                    return true;
                }
                System.out.println(
                        "Bild Id: " + angebot.id + " passt nicht in Raum: " + raum.id + " an Wand: " + position.label);
            }

            // wenn es ein Kunstgegenstand ist, dann pruefe ob dieser in den Raum passt.
        } else if (angebot.ausstellungsstueck instanceof Kunstgegenstand) {
            Kunstgegenstand kunstgegenstand = (Kunstgegenstand) angebot.ausstellungsstueck;

            Ausleihe[] bestehendeAusleihenVonKunstgegenstaende = getAllAusleihenWithAusstellungsstueckForRoom(
                    Kunstgegenstand.class, raum);
            Kunstgegenstand[] bestehendeKunstgegenstaende = Arrays.asList(bestehendeAusleihenVonKunstgegenstaende)
                    .stream()
                    .map(ausleihe -> ausleihe.angebot.ausstellungsstueck).toArray(Kunstgegenstand[]::new);

            if (RaumVerwalter.checkIfGegenstandFitsOnFloor(raum, kunstgegenstand, bestehendeKunstgegenstaende)) {
                addAusleihe(angebot, raum, Position.BODEN);
                return true;
            }
            System.out.println("Kunstgegenstand Id: " + angebot.id + " passt nicht in Raum: " + raum.id);

            // wenn es eine Kunstinstallation ist, dann pruefe ob diese in den Raum passt
        } else if (angebot.ausstellungsstueck instanceof Kunstinstallation) {
            Kunstinstallation kunstinstallation = (Kunstinstallation) angebot.ausstellungsstueck;

            // ? might be more efficient to check this outside of this method (filter for
            // anything but Kunstinstallation)
            Ausleihe[] bestehendeAusleihenVonKunstgegenstaende = getAllAusleihenForRoom(raum);
            if (bestehendeAusleihenVonKunstgegenstaende.length > 0) {
                System.out.println("Kunstinstallation Id: " + angebot.id + " passt nicht in Raum: " + raum.id
                        + " weil Raum schon belegt ist");
                return false;
            }

            if (RaumVerwalter.checkIfGegenstandFitsOnFloor(raum, kunstinstallation, new Ausstellungsstueck3D[] {})) {
                addAusleihe(angebot, raum, Position.VOLLKOMMEN);
                return true;
            }
            System.out.println("Kunstinstallation Id: " + angebot.id + " passt nicht in Raum: " + raum.id);
        }

        return false;
    }

    /**
     * Filter das angegebene Array von Angeboten, sodass nur noch die Angebote
     * uebrigbleiben, die noch nicht ausgeliehen wurden.
     * 
     * @param angebote Array von Angeboten
     * @return gefilterte Liste von Angeboten
     */
    private ArrayList<Angebot> filterGebrauchteAngebote(Angebot[] angebote) {
        return filterGebrauchteAngebote(new ArrayList<Angebot>(Arrays.asList(angebote)));
    }

    /**
     * Filter das angegebene Array von Angeboten, sodass nur noch die Angebote
     * uebrigbleiben, die noch nicht ausgeliehen wurden.
     * 
     * @param angebote Array von Angeboten
     * @return gefiltertes Array von Angeboten
     */
    private ArrayList<Angebot> filterGebrauchteAngebote(ArrayList<Angebot> angebote) {
        List<Angebot> gebrauchteAngebote = ausleihen.stream().map(ausleihe -> ausleihe.angebot)
                .collect(Collectors.toList());
        angebote.removeAll(gebrauchteAngebote);
        return angebote;
    }


    /**
     * Prueft, ob sich eine Kunstinstallation bereits in einem Raum befindet.
     * @param raum Raum, in dem die Kunstinstallation ausgeliehen werden soll
     * @return true, wenn der Raum bereits mit einer Kunstinstallation belegt ist
     */
    @Deprecated
    private boolean checkIfRaumIsFilledWithKunstinstallation(Raum raum) {
        // check if there is any ausleihe with raum ans position vollkommen
        return ausleihen.stream().anyMatch(ausleihe -> ausleihe.raum == raum
                && ausleihe.position == Position.VOLLKOMMEN);
    }

    /**
     * Lieft alle Ausleihen, die sich in dem angegebenen Raum befinden.
     * @param raum Raum, in dem sich die Ausleihen befinden sollen
     * @return Array von Ausleihen
     */
    private Ausleihe[] getAllAusleihenForRoom(Raum raum) {
        return ausleihen.stream().filter(ausleihe -> ausleihe.raum == raum).toArray(Ausleihe[]::new);
    }

    /**
     * Lieft alle Ausleihen, die sich in dem angegebenen Raum befinden und ein
     * Ausstellungsstueck der angegebenen Klasse sind.
     * @param ausstellungsstueckClass Klasse des Ausstellungsstuecks (z.B. Kunstinstallation.class, Bild.class, Kunstgegenstand.class)
     * @param raum Raum, in dem sich die Ausleihen befinden sollen
     * @return Array von Ausleihen
     */
    private Ausleihe[] getAllAusleihenWithAusstellungsstueckForRoom(
            Class<? extends Ausstellungsstueck> ausstellungsstueckClass, Raum raum) {
        return Arrays.asList(getAllAusleihenForRoom(raum)).stream()
                .filter(ausleihe -> ausstellungsstueckClass.isInstance(ausleihe.angebot.ausstellungsstueck))
                .toArray(Ausleihe[]::new);
    }

    /**
     * Gibt die aktuelle Luftbeduerfnisse fuer den angegebenen Raum zurueck.
     * Diese werden aus den Beduerfnissen der Ausleihen berechnet.
     * @param raum Raum, fuer den die Luftbeduerfnisse berechnet werden sollen
     * @return Array mit den Luftbeduerfnissen fuer den Raum im Format {minTemp, maxTemp, minFeuchtigkeit, maxFeuchtigkeit}
     */
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

    /**
     * Prueft, ob die Luftbeduerfnisse fuer den angegebenen Raum erfuellt sind.
     * @param bild Bild, fuer das die Luftbeduerfnisse geprueft werden sollen
     * @param airRequirement Array mit den Luftbeduerfnissen fuer den Raum im Format {minTemp, maxTemp, minFeuchtigkeit, maxFeuchtigkeit}
     * @return true, wenn die Luftbeduerfnisse erfuellt sind
     */
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

    /**
     * Berechnet die Attraktivitaet des Museums.
     * @return Attraktivitaet des Museums
     */
    public double calcAvgAttraktivitaet() {
        double totalAttraktivitaet = 0;
        for (Ausleihe ausleihe : ausleihen) {
            totalAttraktivitaet += ausleihe.angebot.ausstellungsstueck.attraktivitaet;
        }
        return totalAttraktivitaet / ausleihen.size();
    }

    /**
     * Berechent die gesamten Kosten aller Ausleihen.
     * @return Gesamtkosten.
     */
    public int calcTotalCost() {
        int totalCost = 0;
        for (Ausleihe ausleihe : ausleihen) {
            totalCost += ausleihe.angebot.kosten;
        }
        return totalCost;
    }


    /**
     * Reduziert die Kosten, wenn diese über dem Limit sind, indem die teuersten Ausleihen entfernt werden, welche nicht das Fokusthema haben.
     * @return true, wenn die Kosten reduziert werden mussten.
     */
    private boolean reduceCost() {
        int overshoot = calcTotalCost() - kostenGrenze;

        if (overshoot <= 0) {
            return false;
        }

        // create a copy "entbehrlicheAusleihen" by filter ausleihen welche nicht das Thema haben und sortiere absteigend nach Kosten
        List<Ausleihe> entbehrlicheAusleihen = ausleihen.stream()
                .filter(ausleihe -> ausleihe.angebot.ausstellungsstueck.thema != thema)
                .sorted(Comparator.comparing((Ausleihe ausleihe) -> ausleihe.angebot.kosten).reversed())
                .collect(Collectors.toList());        

        while(overshoot > 0) {
            Ausleihe entbehrlicheAusleihe = entbehrlicheAusleihen.get(0);

            ausleihen.remove(entbehrlicheAusleihe);

            overshoot -= entbehrlicheAusleihe.angebot.kosten;
            entbehrlicheAusleihen.remove(0);

            if (entbehrlicheAusleihen.size() > 0) {
                continue;
            }
            System.out.println("Kostenueberschuss konnte nicht weiter reduziert werden.\nBetragsueberschuss: " + overshoot);
            break;
        }

        return true;
    }

}
