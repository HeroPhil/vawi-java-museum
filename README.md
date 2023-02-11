# Kommentar zur SL3 - OOP mit Java
## Klassen Übersicht

### Main

Der Applikationsstart erfolgt durch das Aufrufen der Main-Methoden.
Diese lädt die Benutzer-Oberfläche und verwaltet alle Planungen.
Außerdem wird der Import und Export gestartet.
Dafür werden dem UI entsprechende Methoden zur Verfüfung gestellt.


### Importer

Die Importer-Klasse ließt die vom Lieferanten zur Verfügung gestellten csv Dateien der Anwendung Planung (für das VaWi Museum) ein. Anhand der gelieferten Daten in den csv Dateien werden den Verwalter Klassen die Entsprechenden Objekte für Räume, Angebote Ausstellungsstücke, Partnermuseen und Themen erstellt und übergeben.


 ### Exporter

In der Exporter-Klasse werden die Daten aus dem Ergebnis der Planung in die verschiedenen angeforderten csv Dateien, Museumsführer, Logistikübersicht und Ausleihübersicht, geschrieben. Zur Optimierung und bessern Lesbarkeit wird eine Kopfzeile eingefügt.

 
### Planung

Die Planungs-Klasse bildet das Herzstück des Planungsalgorithmus.
Eine konkrete Planung wird durch die Main Methode erstellt.
Dabei werden Meta-Informationen wie der gewählte Schwerpunkt und eine Kostenobergrenze übergeben.
Der Planungsprozess wird durch die Methode planungDurchfuehren() gestartet.


### Ausleihe

In der Ausleihe-Klasse wird das Angebot zum aktuellen Raum und zur Position zugeordnet.
Die Menge an Ausleihen in der Planung bilden den Planungszustand ab.


### UI

Die UI-Klasse erstellt die Swing Benutzeroberfläche die in drei Bereiche unterteilt ist. Zuerst werden die csv Dateien zum Import ausgesucht. Danach können Parameter zur Planung definiert und die Planung gestartet werden. Im Anschluss schreibt der Export die Daten in die gewünschten csv Dateien und gibt diese aus. Dabei wird auf Methoden der Main Klasse zugegriffen.


### Angebot und AngebotVerwalter

Die Angebots-Klasse umfasst jeweils ein Ausstellungsstück, den Preis für die Ausleihe, sowie eine Referenz zum entsprechenden Partnermuseum.

Die Verwalterklasse-Angebot verwaltet eine Liste aller angebotenen Ausstellungsstücke und sortiert diese nach Attraktivität. Zusätzlich können diese nach Themen gefiltert werden.


### Raum und RaumVerwalter

Die Raum-Klasse umfasst, die Bezeichnung, die Maße Länge und Breite und die vorhandenen Türen eines Raumes.

Über die Methode getNettoWandlaenge können die Maße abzüglich der vorhandene Türen von der Länge Nord/Süd oder Breite Ost/West berechnet werden. 

Die Verwalterklasse-Raum verwaltet eine Liste aller verfügbaren Räume. Außerdem dient sie der Planung als Hilfsklasse zur Berechnung der verfügbaren Wandlänge und Bodenflächen:

Es wird geprüft, ob das als nächstes zur Auswahl stehende Bild an eine Wand im Raum passt. Dabei wird die Größe des Bildes, die Position, d.h. die gewählte Wand im Raum sowie die bereits an der gewählten Wand platzierten Bilder berücksichtigt. Für die Ausstellungsstücke Kunstgegenstand und Kunstinstallation werden die 3 Dimensionalen Maße berücksichtigt.
 

### Ausstellungsstück und Subklassen Bild, Kunstgegenstand, Kunstinstallation

Für die Implementierung der Ausstellungsstückklassen wurde sich für eine Vererbungshierarchie entschieden.

Die abstrakten Superklasse Ausstellungsstück bildet die Grundlage für die Subklasse Bild und Ausstellungsstück3D. Ausstellungsstück3D bildet die Grundlage für die Subklassen Kunstgegenstand und Kunstinstallation.

Jedes Ausstellungsstück, ob 2D oder 3D, hat eine Bezeichnung, Breite und Länge, Angaben zum Künstler, sowie Jahr, Attraktivität und eine Themenzuordnung. Bilder besitzen ergänzend Angaben zur Feuchtigkeit und Temperatur.

Die 3D Ausstellungsstücke Kunstgegenstand und Kunstinstallation haben hingegen zusätzliche Attribute Tiefe und Gewicht.

Das logische Unterscheidungsmerkmal zwischen den Kunstgegenständen und Kunstinstallationen ist nur die zulässige Anzahl im Raum.

### PartnerMuseum und PartnerMuseumVerwalter

In der PartnerMuseum-Klasse werden mitgelieferte Name und Anschrift des Partnermuseums aufbereitet und zur Verwendung im PartnerMuseumVerwalter zugeordnet.

Die Verwalterklasse-PartnerMuseum verwaltet eine Menge von Partnermuseen. Es wird geprüft, ob ein Partnermuseum anhand des Namens und der Adresse bereits bekannt ist und gibt dieses zurück. Ist es nicht bekannt wird es als neues Partnermuseum zurück gegeben.


### Thema und ThemaVerwalter

Die Thema-Klasse umfasst die Bezeichnung des Themas.

Die Verwalterklasse-Themen verwaltet eine Map aller bekannten Themen der Ausstellungsstuecke. Analog zum Partnermusseumsverwalter wird die Einzigartigkeit der Themen sichergestellt. Zusätzlich wird als Wert die Anzahl der Ausstellungsstueck mit bestimmten Thema gespeichert.

### ENUMS

Zur Sicherstellung der Datenkonsistenz ist die Angaben zur Position als ENUM implementiert worden.


## planungs Algorithmus

Der Planungsalgorithmus befüllt linear jeden Raum mit dem Attraktivsten, noch verfügbaren, Ausstellungsstück. Dabei werden priorisiert die Ausstellungsstücke mit dem gewählten Themen-Schwerpunkt ausgewählt. Dies wird für die Hälfte der verfügbaren Räume gewährleistet. Die restlichen Räume werden mit den restlichen Ausstellungsstücken befüllt. Dabei wird die Attraktivität der Ausstellungsstücke ebenfalls berücksichtigt. Allerdings werden auch nicht mehr als drei verschiedene Themen in einem Raum platziert.

Darüber hinaus werden die Maße des Ausstellungsstücke berücksichtige, den Alleinbelegungsanspruch der Kunstinstallationen und die Luftanforderungen der Bilder berücksichtigt.

Abschließend werden gegebenenfalls weniger attraktive Ausstellungsstücke wieder entfernt, um die Kosten unterhalb der gesetzten Kostenobergrenze zu halten. Dieser Vorgang sollte in weiteren Iterationen der Planung optimiert werden.