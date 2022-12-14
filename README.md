# Kommentar zur SL1 - OOP mit Java

## Klassen Übersicht

### Planung
Die Planungs-Klasse bildet das Herzstück des Klassensystems.
Sie beinhaltet alle aus den Dateien gelesenen Informationen bezüglich der Angebote und der zur Verfügung stehenden Räume.
Des Weiteren können Meta-Informationen wie der Zeitraum und der gewählte Schwerpunkt hier gespeichert werden.
All diese Informationen dienen zukünftige als Input für den Optimierungsalgorithmus, welcher ebenfalls in diese Klasse implementiert werden soll.

### Angebot
Die Angebots-Klasse umfasst ein einzelnes Ausstellungsstücken, den preis für dieses sowie eine Referenz zu entsprechenden Partnermuseum.
Letzte kann eventuell noch je nach Komplexität und zukünftigen Funktionsumfang zu einer eigenen Klasse ausgelagert werden.
Die Angebots-Klasse ist die Grundlage für den kostenorientierte Planungsexport.

### Raum
Die Raum-Klasse umfasst einen einzelnen Raum, dessen Maße, sowie alle eingeplanten Ausstellungsstücke für diesen Raum.
Die Raum-Klasse dient außerdem als Grundlage für den raumorientieren Export sowie für den Museumsführer.

### Ausstellungsstück und Subklassen
Für die Implementierung der Ausstellungsstückklasse wurde sich für eine Vererbungshierarchie entschieden.
Die abstrakten Superklassen Ausstellungsstück und Ausstellungsstück3D bilden die Grundlage für die Subklassen Bild, Kunstgegenstand und Kunstinstallation.
Jedes Ausstellungsstück, ob 2D oder 3D, hat eine Breite und Länge, sowie Jahr, Attraktivität und Epoche. Bilder besitzen ergänzend Angaben zur Feuchtigkeit und Temperatur.
Die 3D Ausstellungsstücke Kunstgegenstand und Kunstinstallation haben hingegen zusätzliche Attribute Tiefe und Gewicht.
Das logische Unterscheidungsmerkmal zwischen den Kunstgegenständen und Kunstinstallationen ist zurzeit nur die zulässige Anzahl im Raum.
Bliebt dieser Umstand weiter bestehen, so könnten die Subklassen hier in die Superklasse zusammengefasst werden.


### ENUMS

#### Epoche
Zur Sicherstellung der Datenkonsistenz sollte die Angaben zur Epoche als ENUM implementiert werden.