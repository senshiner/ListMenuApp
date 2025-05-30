package listmenu;

import javax.swing.*;
import java.awt.*;

public class XORDrawingPanel extends JPanel {
    private Color frameColor;
    private Color fillColor;

    public XORDrawingPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Aktifkan XOR mode
        g2d.setXORMode(Color.WHITE); // Warna XOR dasar

        // Persegi dengan warna fillColor
        g2d.setColor(fillColor);
        g2d.fillRect(50, 50, 200, 200);

        // Oval dengan warna frameColor
        g2d.setColor(frameColor);
        g2d.fillOval(100, 100, 200, 200);

        // Kembalikan ke mode normal
        g2d.setPaintMode();
    }
}
