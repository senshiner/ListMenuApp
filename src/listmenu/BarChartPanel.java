package listmenu;

import javax.swing.*;
import java.awt.*;

public class BarChartPanel extends JPanel {
    private final int[] data = {30, 51, 100, 80, 84, 98, 73, 109, 36, 36};
    private final String[] labels = {
        "N01", "N02", "N03", "N04", "N05",
        "N06", "N07", "N08", "N09", "N10"
    };

    private final Color frameColor;
    private final Color fillColor;

    public BarChartPanel(Color frameColor, Color fillColor) {
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

        int maxData = 120;
        int margin = 40;
        int barWidth = 30;
        int gap = 20;

        int chartHeight = panelHeight - 2 * margin;
        int chartWidth = (barWidth + gap) * data.length;

        int startX = (panelWidth - chartWidth) / 2;
        int baseY = panelHeight - margin;

        // Sumbu X
        g2.setColor(Color.GRAY);
        g2.drawLine(startX - 10, baseY, startX + chartWidth + 10, baseY);

        // Gambar setiap bar
        for (int i = 0; i < data.length; i++) {
            int x = startX + i * (barWidth + gap);
            int barHeight = (int) ((double) data[i] / maxData * chartHeight);
            int y = baseY - barHeight;

            // Isi batang
            g2.setColor(new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 180));
            g2.fillRect(x, y, barWidth, barHeight);

            // Outline
            g2.setColor(frameColor);
            g2.drawRect(x, y, barWidth, barHeight);

            // Label
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
            String label = labels[i];
            int labelWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, x + (barWidth - labelWidth) / 2, baseY + 15);
        }
    }
}
