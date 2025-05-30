package listmenu;

import javax.swing.*;
import java.awt.*;

public class GrafikPanel extends JPanel {
    private Color frameColor;
    private Color fillColor;

    public GrafikPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Garis
        g.setColor(frameColor);
        g.drawLine(10, 10, 200, 200);

        // Persegi
        g.setColor(fillColor);
        g.fillRect(50, 50, 100, 100);
        g.setColor(frameColor);
        g.drawRect(50, 50, 100, 100);

        // Oval
        g.setColor(fillColor);
        g.fillOval(150, 150, 100, 100);
        g.setColor(frameColor);
        g.drawOval(150, 150, 100, 100);

        // Teks
        g.setColor(frameColor);
        g.drawString("Contoh Canvas 2D!", 100, 100);
    }
}
