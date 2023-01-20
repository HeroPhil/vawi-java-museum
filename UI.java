import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Write a description of class UI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class UI
{
    static Planung PLANUNG;
    public static void main(String[] args) {
        // create and show a java swing ui with two buttons
        JFrame frame = new JFrame("Museumsverwaltung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);

        // divide the ui into three sections named, "Import", "Planung", "Export" with label
        JPanel importPanel = new JPanel();
        importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.Y_AXIS));
        mainPanel.add(importPanel);
        importPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Import"));

        // add two buttons to the import section which shall open a file chooser
        // one for importing raeume and one for importing angebote
        // use the importer methods to import the choosen files
        JButton importRaeumeButton = new JButton("Räume importieren");
        importRaeumeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            Importer.importRaeume(fileChooser.getSelectedFile().getAbsolutePath());
        });
        importPanel.add(importRaeumeButton);

        JButton importAngeboteButton = new JButton("Angebote importieren");
        importAngeboteButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            try {
                Importer.importAngebote(fileChooser.getSelectedFile().getAbsolutePath());
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        importPanel.add(importAngeboteButton);


        JPanel planungPanel = new JPanel();
        planungPanel.setLayout(new BoxLayout(planungPanel, BoxLayout.Y_AXIS));
        mainPanel.add(planungPanel);
        planungPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Planung"));

        // add a button to the planung section to call planungDurchfuehren
        JButton planungDurchfuehrenButton = new JButton("Planung durchführen");
        planungDurchfuehrenButton.addActionListener(e -> {
            PLANUNG = new Planung(ThemenVerwalter.getInstance().getAllThemen()[0]);
            PLANUNG.planungDurchfuehren();
        });
        planungPanel.add(planungDurchfuehrenButton);

        JPanel exportPanel = new JPanel();
        exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.Y_AXIS));
        mainPanel.add(exportPanel);
        exportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Export"));

        // add a button to the export section to first open a file picker and then export the PLANUNG using Exporter.exportExample
        JButton exportButton = new JButton("Exportieren");
        exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            Exporter.exportExample(PLANUNG.getAllAusleihen(), fileChooser.getSelectedFile().getAbsolutePath());
        });
        exportPanel.add(exportButton);

        frame.pack();

    }
}
