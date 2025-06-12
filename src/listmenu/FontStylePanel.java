package listmenu;

import javax.swing.*;
import java.awt.*;

public class FontStylePanel extends JPanel {
    private Color frameColor;
    private Color fillColor;

    public FontStylePanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 50;
        int y = 60;
        int spacing = 40;

        // Poppins Regular
        g2.setFont(new Font("Poppins", Font.PLAIN, 24));
        g2.setColor(frameColor);
        g2.drawString("Poppins Plain", x, y);

        // Poppins Bold
        g2.setFont(new Font("Poppins", Font.BOLD, 24));
        g2.drawString("Poppins Bold", x, y + spacing);

        // Poppins Italic
        g2.setFont(new Font("Poppins", Font.ITALIC, 24));
        g2.drawString("Poppins Italic", x, y + 2 * spacing);

        // Poppins Bold Italic
        g2.setFont(new Font("Poppins", Font.BOLD | Font.ITALIC, 24));
        g2.setColor(fillColor);
        g2.drawString("Poppins Bold Italic", x, y + 3 * spacing);

        // JetBrains Mono (Monospaced Style)
        g2.setFont(new Font("JetBrains Mono", Font.PLAIN, 24));
        g2.setColor(frameColor);
        g2.drawString("Monospaced Style", x, y + 4 * spacing);
    }
}

