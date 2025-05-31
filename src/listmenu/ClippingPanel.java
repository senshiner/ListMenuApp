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
            image = ImageIO.read(new File("img/casto.jpg")); // letakkan di luar src/
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

        int size = 200;
        int gap = 20;
        int startX = 20;
        int startY = 20;

        // Gambar asli
        g2d.drawImage(image, startX, startY, size, size, this);
        g2d.drawRect(startX, startY, size, size);

        // Clip segitiga
        Shape triangle = new Polygon(
            new int[]{startX + size + gap, startX + size + gap + size / 2, startX + size + gap + size},
            new int[]{startY + size, startY, startY + size},
            3
        );
        g2d.setClip(triangle);
        g2d.drawImage(image, startX + size + gap, startY, size, size, this);
        g2d.setClip(null);
        g2d.draw(triangle);

        // Clip lingkaran
        Shape circle = new Ellipse2D.Double(startX, startY + size + gap, size, size);
        g2d.setClip(circle);
        g2d.drawImage(image, startX, startY + size + gap, size, size, this);
        g2d.setClip(null);
        g2d.draw(circle);

        // Clip love shape
        Shape heart = createHeartShape(startX + size + gap, startY + size + gap, size, size);
        g2d.setClip(heart);
        g2d.drawImage(image, startX + size + gap, startY + size + gap, size, size, this);
        g2d.setClip(null);
        g2d.draw(heart);
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
