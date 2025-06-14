package listmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class RadarChartPanel extends JPanel {
    private final int[] data = {30, 51, 100, 80, 84, 98, 73, 109, 36, 36};
    private final String[] labels = {
        "N01", "N02", "N03", "N04", "N05", 
        "N06", "N07", "N08", "N09", "N10"
    };

    private final Color frameColor;
    private final Color fillColor;

    public RadarChartPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 100;
        int maxData = 120;
        int numAxes = data.length;
        double angleStep = 2 * Math.PI / numAxes;

        // Lingkaran bantu
        g2.setColor(Color.LIGHT_GRAY);
        for (int r = 20; r <= radius; r += 20) {
            Path2D ring = new Path2D.Double();
            for (int i = 0; i < numAxes; i++) {
                double angle = i * angleStep - Math.PI / 2;
                double x = centerX + r * Math.cos(angle);
                double y = centerY + r * Math.sin(angle);
                if (i == 0) ring.moveTo(x, y);
                else ring.lineTo(x, y);
            }
            ring.closePath();
            g2.draw(ring);
        }

        // Sumbu dari tengah
        for (int i = 0; i < numAxes; i++) {
            double angle = i * angleStep - Math.PI / 2;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            g2.drawLine(centerX, centerY, (int) x, (int) y);
        }

        // Radar path
        Path2D radarPath = new Path2D.Double();
        for (int i = 0; i < numAxes; i++) {
            double valueRatio = (double) data[i] / maxData;
            double angle = i * angleStep - Math.PI / 2;
            double x = centerX + valueRatio * radius * Math.cos(angle);
            double y = centerY + valueRatio * radius * Math.sin(angle);
            if (i == 0) radarPath.moveTo(x, y);
            else radarPath.lineTo(x, y);
        }
        radarPath.closePath();

        // Radar dengan fillColor
        g2.setColor(new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 100));
        g2.fill(radarPath);

        // Outline dengan frameColor
        g2.setColor(frameColor);
        g2.draw(radarPath);

        // Label setiap sumbu
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.setColor(Color.BLACK);
        for (int i = 0; i < numAxes; i++) {
            double angle = i * angleStep - Math.PI / 2;
            double x = centerX + (radius + 15) * Math.cos(angle);
            double y = centerY + (radius + 15) * Math.sin(angle);
            g2.drawString(labels[i], (int) x - 10, (int) y);
        }
    }
}

