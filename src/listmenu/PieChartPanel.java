package listmenu;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {
    private final int[] data = {30, 51, 100, 80, 84, 98, 73, 109, 36, 36};
    private final String[] labels = {
        "N01", "N02", "N03", "N04", "N05",
        "N06", "N07", "N08", "N09", "N10"
    };

    private final Color frameColor;
    private final Color fillColor;

    public PieChartPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int diameter = Math.min(panelWidth, panelHeight) - 80;
        int centerX = (panelWidth - diameter) / 2;
        int centerY = (panelHeight - diameter) / 2;

        int total = 0;
        for (int value : data) {
            total += value;
        }

        // Gambar pie slices
        double startAngle = 0;
        for (int i = 0; i < data.length; i++) {
            double percentage = (double) data[i] / total;
            double angle = percentage * 360;

            Color sliceColor = new Color(
                (fillColor.getRed() + i * 25) % 256,
                (fillColor.getGreen() + i * 35) % 256,
                (fillColor.getBlue() + i * 45) % 256,
                200
            );

            g2.setColor(sliceColor);
            g2.fillArc(centerX, centerY, diameter, diameter, (int) startAngle, (int) angle);

            g2.setColor(frameColor);
            g2.drawArc(centerX, centerY, diameter, diameter, (int) startAngle, (int) angle);

            startAngle += angle;
        }

        // Gambar legenda
        int legendX = centerX + diameter + 20;
        int legendY = centerY;
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 0; i < data.length; i++) {
            Color color = new Color(
                (fillColor.getRed() + i * 25) % 256,
                (fillColor.getGreen() + i * 35) % 256,
                (fillColor.getBlue() + i * 45) % 256,
                200
            );
            g2.setColor(color);
            g2.fillRect(legendX, legendY + i * 20, 15, 15);

            g2.setColor(Color.BLACK);
            g2.drawString(labels[i], legendX + 20, legendY + i * 20 + 12);
        }
    }
}
