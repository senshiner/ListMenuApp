package listmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class KurvaPanel extends JPanel {
    // Titik kontrol untuk kurva GeneralPath
    private final int[] x1 = {-45, -40, -32, -24, 10, 24};
    private final int[] y1 = {-15, -12, -2, 0, 12, 20};

    // Titik kontrol kurva Bézier kustom
    private final int[] x2 = {0, 100, 200};
    private final int[] y2 = {200, 100, 200};

    private Color frameColor;
    private Color fillColor;

    public KurvaPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform original = g2.getTransform();

        // --- Kurva Bézier GeneralPath
        g2.translate(100, 200);
        g2.scale(2, 2);

        g2.setColor(Color.RED);
        for (int i = 0; i < x1.length; i++) {
            g2.fillOval(x1[i] - 2, y1[i] - 2, 4, 4);
        }

        g2.setColor(Color.GRAY);
        GeneralPath controlPolygon = new GeneralPath();
        controlPolygon.moveTo(x1[0], y1[0]);
        for (int i = 1; i < x1.length; i++) {
            controlPolygon.lineTo(x1[i], y1[i]);
        }
        g2.draw(controlPolygon);

        g2.setColor(Color.BLUE);
        GeneralPath bezierCurve = new GeneralPath();
        bezierCurve.moveTo(x1[0], y1[0]);
        for (int i = 1; i < x1.length - 2; i += 3) {
            bezierCurve.curveTo(
                x1[i], y1[i],
                x1[i + 1], y1[i + 1],
                x1[i + 2], y1[i + 2]
            );
        }
        g2.draw(bezierCurve);

        g2.setTransform(original);
        g2.setColor(Color.BLACK);
        g2.drawString("Kurva Bézier", 40, 80);

        // --- Kurva Bézier
        g2.translate(400, 0);

        // Garis bantu antara titik kontrol
        g2.setColor(Color.GRAY);
        g2.drawLine(x2[0], y2[0], x2[1], y2[1]);
        g2.drawLine(x2[1], y2[1], x2[2], y2[2]);

        // Titik-titik kontrol
        g2.setColor(fillColor);
        for (int i = 0; i < x2.length; i++) {
            g2.fillOval(x2[i] - 4, y2[i] - 4, 8, 8);
        }

        // Kurva Bézier kustom
        g2.setColor(frameColor);
        GeneralPath curve = new GeneralPath();
        curve.moveTo(x2[0], y2[0]);
        curve.quadTo(x2[1], y2[1], x2[2], y2[2]);
        g2.draw(curve);

        g2.setColor(Color.BLACK);
        g2.drawString("Kurva Bézier Custom", 40, 80);

        g2.setTransform(original);
    }
}
