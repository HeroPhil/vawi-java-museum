
# Kommentar zur SL3 - OOP mit Java

 

## Klassen Übersicht

 

### Planung


Die Planungs-Klasse bildet das Herzstück des Klassensystems. Der Aufruf erfolgt durch die Main-Klasse um die Planung zu starten.


Sie beinhaltet alle aus den Dateien gelesenen Informationen bezüglich der Angebote und der zur Verfügung stehenden Räume.


Des Weiteren können Meta-Informationen wie der Zeitraum, der gewählte Schwerpunkt und eine Kostenobergrenze hier gespeichert werden.


Es wird jeder Raum mit Ausstellungsstücken nach manuellen Vorgaben, und den grundsätzlichen Anforderungen, mindestens ein Ausstellungsstück des gewählten Themas in der Hälfte der verfügbaren Räume, bestückt.


Dabei wird auch geprüft ob die Mindestanforderungen erfüllt sind und ob der Raum noch Kapazitäten für das gewählte Ausstellungsstück hat.


Bei Erfolg wird das Angebot im Raum platziert und eine neue Ausleihe hinzugefügt und die Angebotsliste um diese gekürzt.


Die Planungsklasse gibt nach der Planung einer Ausstellung die geplanten Ausleihen für die gewünschten Listen an den Exporter.

 

### Angebot


Die Angebots-Klasse umfasst jedes einzelne Ausstellungsstück, den Preis für dieses, sowie eine Referenz zum entsprechenden Partnermuseum.


Die Angebots-Klasse ist die Grundlage für den kostenorientierten Planungsexport.

 

### Raum


Die Raum-Klasse umfasst einen einzelnen Raum, dessen Bezeichnung, die Maße Länge und Breite und die vorhandenen Türen.


Die Raum-Klasse gibt die Netto Wandlänge aus, wobei vorhandene Türen von der Länge Nord/Süd oder Breite Ost/West abgezogen werden. 


Die Raum-Klasse dient außerdem als Grundlage für den raumorientieren Export sowie für den Museumsführer.

 

### Ausstellungsstück und Subklassen Bild, Kunstgegenstand, Kunstinstallation


Für die Implementierung der Ausstellungsstückklasse wurde sich für eine Vererbungshierarchie entschieden.


Die abstrakten Superklasse Ausstellungsstück bildet die Grundlage für die Subklasse Bild und Ausstellungsstück3D. Ausstellungsstück3D bildet die Grundlage für die Subklassen Kunstgegenstand und Kunstinstallation.


Jedes Ausstellungsstück, ob 2D oder 3D, hat eine Bezeichnung, Breite und Länge, Angaben zum Künstler, sowie Jahr, Attraktivität, Epoche und eine Themenzuordnung. Bilder besitzen ergänzend Angaben zur Feuchtigkeit und Temperatur.


Die 3D Ausstellungsstücke Kunstgegenstand und Kunstinstallation haben hingegen zusätzliche Attribute Tiefe und Gewicht.


Das logische Unterscheidungsmerkmal zwischen den Kunstgegenständen und Kunstinstallationen ist zurzeit nur die zulässige Anzahl im Raum.

 

### Ausleihe


In der Ausleihe-Klasse wird das Angebot zum aktuellen Raum und zur Position zugeordnet.

 

### Exporter


In der Exporter-Klasse werden die Daten aus dem Ergebnis der Planungsklassse in die verschiedenen angeforderten csv Dateien, Museumsführer, Logistikübersicht und Ausleihübersicht, geschrieben. Zur Optimierung und bessern Lesbarkeit wird eine Kopfzeile eingefügt.

 

### Importer


Die Importer-Klasse stellt die vom Lieferanten zur Verfügung gestellten csv Dateien der Anwendung Planung (für das VaWi Museum) zur Verfügung. Die gelieferten Inhalte der csv Dateien werden mit allen gelieferten Merkmalen, Räume zu Id, Ausstellungsstück zu Bild, Kunstgegenstand und Kunstinstallation, zugeordnet.

 

### Main


Die Main-Klasse bildet den Einstiegspunkt zur Planung. Sie startet die entwickelte Benutzeroberfläche, führt den Importer aus, ermöglicht den Einstieg in die Planung und führt im Anschluss ebenso den Exporter aus.

 

### PartnerMuseum


In der PartnerMuseum-Klasse werden mitgelieferte Name und Anschrift des Partnermuseums aufbereitet und zur Verwendung im PartnerMuseumVerwalter zugeordnet. 

 

### Position


In der Klasse Position werden die Ausrichtungen im jeweiligen Raum benannt. Dies ist relevant um das Bild einer bestimmten Wand im Raum zuzuordnen.

 

### Thema


Die Thema-Klasse prüft die Zuordnung des ausgewählten Ausstellungsstücks zum gewählten Thema. Sie setzt einen Wert, ob es dem ausgewählten Thema angehört und somit die Bedingungen zur Themenverteilung in den Räumen erfüllen kann.

 

### UI


Die UI-Klasse erstellt die Benutzeroberfläche die in drei Bereiche unterteilt ist. Zuerst werden die csv Dateien zum Import ausgesucht. Danach können Parameter zur Planung definiert und die Planung gestartet werden. Im Anschluss schreibt der Export die Daten in die gewünschten csv Dateien und gibt diese aus.

 

### PartnerMuseumVerwalter


Die Verwalterklasse-PartnerMuseum prüft ob das Partnermuseum anhand des Namens und der Adresse bereits bekannt ist und gibt dieses zurück. Ist es nicht bekannt wird es als neues Partnermuseum zurück gegeben.

 

### ThemenVerwalter


Die Verwalterklasse-Themen erstellt eine Liste aller per aktueller csv Datei mitgelieferten Themen. Dabei zählt sie wie oft das jeweilige Thema vorhanden ist, um dies bei der Planung anzuzeigen.

 

### AngebotVerwalter


Die Verwalterklasse-Angebot erstellt eine Liste aller angebotenen Ausstellungsstücke und sortiert sie nach Attraktivität. Zusätzlich werden die nach Attraktivität sortierten Angebote nach Themen gefiltert.

 

### RaumVerwalter


Die Verwalterklasse-Raum prüft ob das als nächstes zur Auswahl stehende Bild an eine Wand im Raum passt. Dabei wird die Größe des Bildes, die Position, d.h. die gewählte Wand im Raum sowie die bereits an der gewählten Wand platzierten Bilder berücksichtigt. Für die Ausstellungsstücke Kunstgegenstand und Kunstinstallation wird die Höhe geprüft und bei der Installation ob der Raum noch frei ist; sollte die Installation platziert werden, wird der Raum direkt als voll gekennzeichnet.

 

### ENUMS


Zur Sicherstellung der Datenkonsistenz ist die Angaben zur Position als ENUM implementiert worden.
