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

        g2.setColor(frameColor);
        g2.setFont(new Font("Serif", Font.PLAIN, 24));
        g2.drawString("Plain Font", x, y);

        g2.setFont(new Font("Serif", Font.BOLD, 24));
        g2.drawString("Bold Font", x, y + spacing);

        g2.setFont(new Font("Serif", Font.ITALIC, 24));
        g2.drawString("Italic Font", x, y + 2 * spacing);

        g2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        g2.setColor(fillColor);
        g2.drawString("Bold Italic Font", x, y + 3 * spacing);

        g2.setFont(new Font("Monospaced", Font.PLAIN, 24));
        g2.setColor(frameColor);
        g2.drawString("Monospaced Font", x, y + 4 * spacing);
    }
}
