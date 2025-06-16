package listmenu;

import javax.swing.*;
import java.awt.*;

public class ListMenuApp extends JFrame {

    private JComboBox<String> projectSelector;
    private JButton warnaFrameBtn;
    private JButton warnaFillBtn;
    private JPanel mainPanel;

    public static Color frameColor = Color.BLACK;
    public static Color fillColor = new Color(0, 179, 0);

    public ListMenuApp() {
        setTitle("List Menu - M Faisal Arif (241011401251)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Menu:"));

        projectSelector = new JComboBox<>(new String[]{"Grafik", "Kurva", "XOR", "PieChart", "Clipping", "FontStyle"});
        topPanel.add(projectSelector);

        warnaFrameBtn = new JButton("Warna Frame");
        warnaFillBtn = new JButton("Warna Fill");
        topPanel.add(warnaFrameBtn);
        topPanel.add(warnaFillBtn);

        add(topPanel, BorderLayout.NORTH);

        // Panel utama (tempat tampil project)
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Event
        warnaFrameBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Pilih Warna Frame", frameColor);
            if (c != null) frameColor = c;
            updateProjectPanel();
        });

        warnaFillBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Pilih Warna Fill", fillColor);
            if (c != null) fillColor = c;
            updateProjectPanel();
        });

        projectSelector.addActionListener(e -> updateProjectPanel());

        updateProjectPanel(); // project default

        setVisible(true);
    }

    private void updateProjectPanel() {
        mainPanel.removeAll();
        String selected = (String) projectSelector.getSelectedItem();
        if ("Grafik".equals(selected)) {
            mainPanel.add(new GrafikPanel(frameColor, fillColor), BorderLayout.CENTER);
        } else if ("Kurva".equals(selected)) {
            mainPanel.add(new KurvaPanel(frameColor, fillColor), BorderLayout.CENTER);
        } else if ("XOR".equals(selected)) {
            mainPanel.add(new XORDrawingPanel(frameColor, fillColor), BorderLayout.CENTER);
        } else if ("PieChart".equals(selected)) {
            mainPanel.add(new PieChartPanel(frameColor, fillColor), BorderLayout.CENTER);
        } else if ("Clipping".equals(selected)) {
            mainPanel.add(new ClippingPanel(frameColor, fillColor), BorderLayout.CENTER);
        } else if ("FontStyle".equals(selected)) {
            mainPanel.add(new FontStylePanel(frameColor, fillColor), BorderLayout.CENTER);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListMenuApp::new);
    }
}
