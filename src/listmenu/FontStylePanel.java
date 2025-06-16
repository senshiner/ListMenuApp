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

        int x = 80;
        int y = 60;
        int spacing = 40;

        // Tahoma Plain
        g2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        g2.setColor(frameColor);
        g2.drawString("Tahoma Plain", x, y);

        // Tahoma Bold
        g2.setFont(new Font("Tahoma", Font.BOLD, 24));
        g2.drawString("Tahoma Bold", x, y + spacing);

        // Tahoma Italic
        g2.setFont(new Font("Tahoma", Font.ITALIC, 24));
        g2.drawString("Tahoma Italic", x, y + 2 * spacing);

        // Tahoma Bold Italic
        g2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
        g2.setColor(fillColor);
        g2.drawString("Tahoma Bold Italic", x, y + 3 * spacing);

        // Tahoma Monospaced-like Style
        g2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        g2.setColor(frameColor);
        g2.drawString("Tahoma Style", x, y + 4 * spacing);
    }
}
