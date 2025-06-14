package listmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;

public class KurvaPanel extends JPanel {
    // Titik kontrol untuk kurva GeneralPath
    private final int[] x1 = {-45, -40, -32, -24, 10, 24};
    private final int[] y1 = {-15, -12, -2, 0, 12, 20};

    // Titik kontrol untuk kurva Bézier Custom
    private final int[] x2 = {100, 200, 100, 0, 100};
    private final int[] y2 = {100, 200, 300, 200, 100};

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

        // anti-aliasing
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

        // --- Kurva Bézier Custom
        g2.translate(400, 0);
        g2.setColor(Color.GRAY);
        for (int i = 0; i < x2.length - 1; i++) {
            g2.drawLine(x2[i], y2[i], x2[i + 1], y2[i + 1]);
        }

        g2.setColor(fillColor);
        for (int i = 0; i < x2.length; i++) {
            g2.fillOval(x2[i] - 4, y2[i] - 4, 8, 8);
        }

        g2.setColor(frameColor);
        GeneralPath curve = new GeneralPath();
        curve.moveTo(x2[0], y2[0]);

        for (double t = 0; t <= 1.0; t += 0.01) {
            double px = 0, py = 0;
            int n = x2.length - 1;
            for (int i = 0; i <= n; i++) {
                double bin = binomial(n, i) * Math.pow(1 - t, n - i) * Math.pow(t, i);
                px += bin * x2[i];
                py += bin * y2[i];
            }
            curve.lineTo(px, py);
        }
        g2.draw(curve);

        g2.setColor(Color.BLACK);
        g2.drawString("Kurva Bézier Custom", 40, 80);
        g2.setTransform(original);
    }

    private double binomial(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}
