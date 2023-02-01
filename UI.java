import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 * Write a description of class UI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class UI {
    private static int stage = -1;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel importPanel;
    private static JPanel planungPanel;
    private static JPanel exportPanel;

    private static JComboBox<Thema> planungThemenComboBox;
    private static JComboBox<Planung> exportPlanungComboBox;

    public static void main(String[] args) {
        // create and show a java swing ui with two buttons
        frame = new JFrame("Museumsverwaltung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel);

        // divide the ui into three sections named, "Import", "Planung", "Export" with
        // label
        importPanel = new JPanel();
        importPanel.setLayout(new BoxLayout(importPanel, BoxLayout.Y_AXIS));
        importPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(importPanel);
        importPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Import"));

        // IMPORT
        // add a Input field for Raeume and Angebote Paths, plus a button for each to
        // open a file chooser
        // add a button to run the import
        // get and set the path data from / in main
        // add a label to show what the path is for
        JPanel importRaeumePanel = new JPanel();
        importRaeumePanel.setLayout(new BoxLayout(importRaeumePanel, BoxLayout.X_AXIS));
        JLabel raeumeLabel = new JLabel("Räume Pfad:");
        importRaeumePanel.add(raeumeLabel);
        JLabel raeumePathLabel = new JLabel(Main.getRaeumePfad());
        importRaeumePanel.add(raeumePathLabel);
        JButton raeumePathButton = new JButton("...");
        raeumePathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Main.getRaeumePfad());
            fileChooser.showOpenDialog(frame);
            Main.setRaeumePfad(fileChooser.getSelectedFile().getAbsolutePath());
            raeumePathLabel.setText(Main.getRaeumePfad());
        });
        importRaeumePanel.add(raeumePathButton);
        importPanel.add(importRaeumePanel);

        JPanel importAngebotePanel = new JPanel();
        importAngebotePanel.setLayout(new BoxLayout(importAngebotePanel, BoxLayout.X_AXIS));
        JLabel angeboteLabel = new JLabel("Angebote Pfad:");
        importAngebotePanel.add(angeboteLabel);
        JLabel angebotePathLabel = new JLabel(Main.getAngebotePfad());
        importAngebotePanel.add(angebotePathLabel);
        JButton angebotePathButton = new JButton("...");
        angebotePathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Main.getAngebotePfad());
            fileChooser.showOpenDialog(frame);
            Main.setAngebotePfad(fileChooser.getSelectedFile().getAbsolutePath());
            angebotePathLabel.setText(Main.getAngebotePfad());
        });
        importAngebotePanel.add(angebotePathButton);
        importPanel.add(importAngebotePanel);

        JButton importButton = new JButton("Importieren");
        importButton.addActionListener(e -> {
            Main.runImport();
            // TODO handle Exception
            nextStage(1);
        });
        importPanel.add(importButton);

        // PLANUNG
        planungPanel = new JPanel();
        planungPanel.setLayout(new BoxLayout(planungPanel, BoxLayout.Y_AXIS));
        planungPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(planungPanel);
        planungPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Planung"));

        // add a one line input field to set the Bezeichung of the Planung and lable it
        JPanel planungBezeichnungPanel = new JPanel();
        planungBezeichnungPanel.setLayout(new BoxLayout(planungBezeichnungPanel, BoxLayout.X_AXIS));
        JLabel planungBezeichnungLabel = new JLabel("Bezeichnung:");
        planungBezeichnungPanel.add(planungBezeichnungLabel);
        JTextField planungBezeichnungTextField = new JTextField();
        planungBezeichnungPanel.add(planungBezeichnungTextField);
        planungPanel.add(planungBezeichnungPanel);
              
        // add a Dropdown with a label to select a Themen Schwerpunkt
        // using all available Themen from ThemenVerwalter.getAllThemen()
        JPanel planungThemenPanel = new JPanel();
        planungThemenPanel.setLayout(new BoxLayout(planungThemenPanel, BoxLayout.X_AXIS));
        JLabel planungThemenLabel = new JLabel("Themen Schwerpunkt:");
        planungThemenPanel.add(planungThemenLabel);
        Thema[] themen = ThemenVerwalter.getInstance().getAllThemen();
        planungThemenComboBox = new JComboBox<Thema>(themen);
         // set item labels
         planungThemenComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Thema) {
                    Thema thema = (Thema) value;
                    setText(thema.bezeichnung + " (" + ThemenVerwalter.getInstance().getCountForThema(thema) + ")" );
                }
                return this;
            }
        });
        // on new thema selected, set the new thema in main
        planungThemenComboBox.addActionListener(e -> {
            Main.setFokusThema((Thema) planungThemenComboBox.getSelectedItem());
        });
        planungThemenPanel.add(planungThemenComboBox);
        planungPanel.add(planungThemenPanel);

        // set the cost limit in main
        JPanel planungCostLimitPanel = new JPanel();
        planungCostLimitPanel.setLayout(new BoxLayout(planungCostLimitPanel, BoxLayout.X_AXIS));
        JLabel planungCostLimitLabel = new JLabel("Kosten Limit:");
        planungCostLimitPanel.add(planungCostLimitLabel);
        // add text field with int only input
        JTextField planungCostLimitTextField = new JTextField();
        // set input filter to only allow numbers using key adapter
        planungCostLimitTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                    Main.setKostenGrenze(Integer.parseInt(planungCostLimitTextField.getText()));
                }
            }
        });
        planungCostLimitPanel.add(planungCostLimitTextField);

        // add simple label with € sign
        JLabel planungCostLimitEuroLabel = new JLabel("€");
        planungCostLimitPanel.add(planungCostLimitEuroLabel);
        planungPanel.add(planungCostLimitPanel);

        // add a button to the planung section to call planungDurchfuehren
        JButton planungDurchfuehrenButton = new JButton("Planung durchführen");
        planungDurchfuehrenButton.addActionListener(e -> {
            // if bezeichnung combo box is empty, set value to current datetime
            if (planungBezeichnungTextField.getText().isEmpty()) {
                planungBezeichnungTextField.setText(LocalDateTime.now().toString());
            }
            Main.runPlanung(planungBezeichnungTextField.getText());
            nextStage(2);
        });
        planungPanel.add(planungDurchfuehrenButton);

        // EXPORT
        exportPanel = new JPanel();
        exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.Y_AXIS));
        exportPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(exportPanel);
        exportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Export"));

        // add a text field with a label to input the export path and a button to open a
        // file picker
        // like in the import section
        JPanel exportPathPanel = new JPanel();
        exportPathPanel.setLayout(new BoxLayout(exportPathPanel, BoxLayout.X_AXIS));
        JLabel exportPathLabel = new JLabel("Export Pfad:");
        exportPathPanel.add(exportPathLabel);
        JLabel exportPathTextField = new JLabel();
        exportPathTextField.setText(Main.getExportDirPfad());
        exportPathPanel.add(exportPathTextField);
        JButton exportPathButton = new JButton("...");
        exportPathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(Main.getExportDirPfad());
            fileChooser.showOpenDialog(frame);
            Main.setExportDirPfad(fileChooser.getSelectedFile().getAbsolutePath());
            exportPathTextField.setText(Main.getExportDirPfad());
        });
        exportPathPanel.add(exportPathButton);
        exportPanel.add(exportPathPanel);

        // add a dropdown to select the planung to export
        // using all available PLANUNG from Main.getAllPlanungen()
        JPanel exportPlanungPanel = new JPanel();
        exportPlanungPanel.setLayout(new BoxLayout(exportPlanungPanel, BoxLayout.X_AXIS));
        JLabel exportPlanungLabel = new JLabel("Planung:");
        exportPlanungPanel.add(exportPlanungLabel);
        exportPlanungComboBox = new JComboBox<Planung>(Main.getAllPlanungen());
        // set item labels
        exportPlanungComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Planung) {
                    Planung planung = (Planung) value;
                    setText(planung.bezeichnung);
                }
                return this;
            }
        });
        exportPlanungPanel.add(exportPlanungComboBox);
        exportPanel.add(exportPlanungPanel);


        // add a button to the export section to first open a file picker and then
        // export the PLANUNG using Exporter.exportExample
        JButton exportButton = new JButton("Exportieren");
        exportButton.addActionListener(e -> {
            Main.runExport((Planung)exportPlanungComboBox.getSelectedItem());
        });
        exportPanel.add(exportButton);

        frame.pack();

        nextStage();

    }

    public static void update() {
        frame.invalidate();
    }

    private static void toggleWithSubComponents(Component c) {
        c.setEnabled(!c.isEnabled());
        if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                toggleWithSubComponents(child);
            }
        }
    }

    private static void nextStage() {
        nextStage(stage + 1);
    }

    private static void nextStage(int newStage) {
        ArrayList<Component> target = new ArrayList<Component>();
        switch (stage) {
            case -1:
                target.add(planungPanel);
                target.add(exportPanel);
                break;
            case 0:
                Thema[] themen = ThemenVerwalter.getInstance().getAllThemen();
                planungThemenComboBox.setModel(new DefaultComboBoxModel<Thema>(themen));
                planungThemenComboBox.setSelectedIndex(0);
                target.add(planungPanel);
                break;
            case 1:
                exportPlanungComboBox.setModel(new DefaultComboBoxModel<Planung>(Main.getAllPlanungen()));
                target.add(exportPanel);
                break;
        }
        for (Component component : target) {
            toggleWithSubComponents(component);
        }
        update();
        stage = newStage;
    }

}
