package listmenu;

import javax.swing.*;
import java.awt.*;

public class ListMenuApp extends JFrame {

    private JComboBox<String> projectSelector;
    private JButton warnaFrameBtn;
    private JButton warnaFillBtn;
    private JPanel mainPanel;

    public static Color frameColor = new Color(51, 51, 0);
    public static Color fillColor = Color.CYAN;

    public ListMenuApp() {
        setTitle("List Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel atas
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Menu:"));

        projectSelector = new JComboBox<>(new String[]{"Grafik", "Kurva", "XOR", "PieChart"});
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
            updateProjectPanel(); // refresh panel
        });

        warnaFillBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Pilih Warna Fill", fillColor);
            if (c != null) fillColor = c;
            updateProjectPanel(); // refresh panel
        });

        projectSelector.addActionListener(e -> updateProjectPanel());

        updateProjectPanel(); // tampilkan project default

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
            mainPanel.add(new PieChartPanel(), BorderLayout.CENTER);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListMenuApp::new);
    }
}
