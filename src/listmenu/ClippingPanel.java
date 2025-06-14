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
            image = ImageIO.read(new File("img/casto.jpg"));
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // smoother

        int size = 200;
        int gap = 20;
        int startX = 20;
        int startY = 20;

        // Gambar asli
        g2d.drawImage(image, startX, startY, size, size, this);
        g2d.drawRect(startX, startY, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Original", startX + size / 2 - 25, startY + size + 15);

        // Clip segitiga
        int triX = startX + size + gap;
        int triY = startY;
        int[] xPoints = {triX, triX + size / 2, triX + size};
        int[] yPoints = {triY + size, triY, triY + size};
        Shape triangle = new Polygon(xPoints, yPoints, xPoints.length);
        g2d.setClip(triangle);
        g2d.drawImage(image, triX, triY, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(triangle);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Segitiga", triX + size / 2 - 35, triY + size + 15);

        // Clip lingkaran
        int cirX = startX;
        int cirY = startY + size + gap;
        Shape circle = new Ellipse2D.Double(cirX, cirY, size, size);
        g2d.setClip(circle);
        g2d.drawImage(image, cirX, cirY, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(circle);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Lingkaran", cirX + size / 2 - 30, cirY + size + 15);

        // Clip love shape
        int heartX = startX + size + gap;
        int heartY = startY + size + gap;
        Shape heart = createHeartShape(heartX, heartY, size, size);
        g2d.setClip(heart);
        g2d.drawImage(image, heartX, heartY, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(heart);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Love", heartX + size / 2 - 30, heartY + size + 15);
    }

    private Shape createHeartShape(int x, int y, int w, int h) {
        GeneralPath heart = new GeneralPath();
        heart.moveTo(x + w / 2.0, y + h * 0.8);
        heart.curveTo(x + w * 1.2, y + h * 0.5, x + w * 0.8, y, x + w / 2.0, y + h * 0.3);
        heart.curveTo(x + w * 0.2, y, x - w * 0.2, y + h * 0.5, x + w / 2.0, y + h * 0.8);
        heart.closePath();
        return heart;
    }
}
