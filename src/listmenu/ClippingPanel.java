package listmenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ClippingPanel extends JPanel {
    private Color frameColor;
    private Color fillColor;
    private BufferedImage image;

    public ClippingPanel(Color frameColor, Color fillColor) {
        this.frameColor = frameColor;
        this.fillColor = fillColor;
        setBackground(Color.WHITE);

        try {
            image = ImageIO.read(new File("img/dika.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(frameColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = 200;
        int gap = 20;
        int startX = 20;
        int startY = 20;

        // === ORIGINAL ===
        int x1 = startX;
        int y1 = startY;
        g2d.drawImage(image, x1, y1, size, size, this);
        g2d.drawRect(x1, y1, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Original", x1 + size / 2 - 30, y1 + size + 15);

        // === LINGKARAN ===
        int x2 = x1 + size + gap;
        int y2 = y1;
        Shape circle = new Ellipse2D.Double(x2, y2, size, size);
        g2d.setClip(circle);
        g2d.drawImage(image, x2, y2, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(circle);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Lingkaran", x2 + size / 2 - 35, y2 + size + 15);

        // === BINTANG ===
        int x3 = x2 + size + gap;
        int y3 = y1;
        Shape star = createStarShape(x3 + size / 2, y3 + size / 2, size / 2.0, size / 4.0, 5);
        g2d.setClip(star);
        g2d.drawImage(image, x3, y3, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(star);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Bintang", x3 + size / 2 - 30, y3 + size + 15);
    }

    private Shape createStarShape(double centerX, double centerY, double outerRadius, double innerRadius, int numPoints) {
        GeneralPath path = new GeneralPath();
        double angle = Math.PI / numPoints;

        for (int i = 0; i < 2 * numPoints; i++) {
            double r = (i % 2 == 0) ? outerRadius : innerRadius;
            double theta = i * angle - Math.PI / 2;
            double x = centerX + r * Math.cos(theta);
            double y = centerY + r * Math.sin(theta);

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        path.closePath();
        return path;
    }
}
