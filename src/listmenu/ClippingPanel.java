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
            image = ImageIO.read(new File("img/furina.jpg"));
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

        // === BULAN SABIT ===
        int x2 = x1 + size + gap;
        int y2 = y1;
        Shape crescent = createCrescentShape(x2, y2, size);
        g2d.setClip(crescent);
        g2d.drawImage(image, x2, y2, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(crescent);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Bulan Sabit", x2 + size / 2 - 40, y2 + size + 15);

        // === LOVE ===
        int x3 = x2 + size + gap;
        int y3 = y1;
        Shape heart = createHeartShape(x3, y3, size, size);
        g2d.setClip(heart);
        g2d.drawImage(image, x3, y3, size, size, this);
        g2d.setClip(null);
        g2d.setColor(frameColor);
        g2d.draw(heart);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Love", x3 + size / 2 - 20, y3 + size + 15);
    }

    private Shape createHeartShape(int x, int y, int w, int h) {
        GeneralPath heart = new GeneralPath();
        heart.moveTo(x + w / 2.0, y + h * 0.8);
        heart.curveTo(x + w * 1.2, y + h * 0.5, x + w * 0.8, y, x + w / 2.0, y + h * 0.3);
        heart.curveTo(x + w * 0.2, y, x - w * 0.2, y + h * 0.5, x + w / 2.0, y + h * 0.8);
        heart.closePath();
        return heart;
    }

    private Shape createCrescentShape(int x, int y, int size) {
        Ellipse2D outer = new Ellipse2D.Double(x, y, size, size);
        Ellipse2D inner = new Ellipse2D.Double(x + size * 0.25, y, size, size);
        Area crescent = new Area(outer);
        crescent.subtract(new Area(inner));
        return crescent;
    }
}
