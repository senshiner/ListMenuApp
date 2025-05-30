package listmenu;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PieChartPanel extends JPanel {
    private final int[] data = {30, 51, 100, 80, 84, 98, 73, 109, 36, 36};
    private final String[] labels = {
        "N01", "N02", "N03", "N04", "N05", 
        "N06", "N07", "N08", "N09", "N10"
    };
    private final Color[] colors = {
        Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE,
        Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.PINK,
        Color.DARK_GRAY, Color.BLACK
    };

    public PieChartPanel() {
        setBackground(ListMenuApp.frameColor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int total = Arrays.stream(data).sum();
        int startAngle = 0;
        int diameter = 300;

        Graphics2D g2 = (Graphics2D) g;

        // Gambar pie chart
        for (int i = 0; i < data.length; i++) {
            int angle = (int) Math.round((data[i] * 360.0) / total);
            g2.setColor(colors[i]);
            g2.fillArc(20, 20, diameter, diameter, startAngle, angle);
            startAngle += angle;
        }

        // Gambar legend di samping
        int legendX = diameter + 40;
        int legendY = 40;
        g2.setFont(new Font("Arial", Font.PLAIN, 14));

        for (int i = 0; i < labels.length; i++) {
            g2.setColor(colors[i]);
            g2.fillRect(legendX, legendY + i * 30, 20, 20);
            g2.setColor(Color.BLACK);
            g2.drawString(labels[i] + " (" + data[i] + ")", legendX + 30, legendY + 15 + i * 30);
        }
    }
}
