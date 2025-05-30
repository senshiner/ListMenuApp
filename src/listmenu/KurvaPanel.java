package listmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class KurvaPanel extends JPanel {
    private final int[] x = {60, 160, 260, 360};
    private final int[] y = {300, 100, 500, 300};

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

        // Anti-aliasing untuk kurva halus
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Garis bantu antar titik
        g2.setColor(Color.GRAY);
        for (int i = 0; i < x.length - 1; i++) {
            g2.drawLine(x[i], y[i], x[i + 1], y[i + 1]);
        }

        // Titik kontrol (bulatan kecil)
        g2.setColor(fillColor); // isi titik
        for (int i = 0; i < x.length; i++) {
            g2.fillOval(x[i] - 4, y[i] - 4, 8, 8);
        }

        // Kurva Bézier
        g2.setColor(frameColor); // warna garis utama
        GeneralPath curve = new GeneralPath();
        curve.moveTo(x[0], y[0]);

        for (double t = 0; t <= 1.0; t += 0.01) {
            double px = 0, py = 0;
            int n = x.length - 1;

            for (int i = 0; i <= n; i++) {
                double bin = binomial(n, i) * Math.pow(1 - t, n - i) * Math.pow(t, i);
                px += bin * x[i];
                py += bin * y[i];
            }

            curve.lineTo(px, py);
        }

        g2.draw(curve);
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
