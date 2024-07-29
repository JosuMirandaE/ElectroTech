package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AnimatedPanel extends JPanel {

    private List<BufferedImage> images = new ArrayList<>();
    private int currentImageIndex = 0;
    private int x = 0; // Position of the image
    private final int xSpeed = 5; // Speed of the image movement
    private final int imageWidth;
    private final int imageHeight;
    
    public AnimatedPanel(String[] imagePaths) {
        for (String path : imagePaths) {
            try {
                BufferedImage img = ImageIO.read(getClass().getResource(path));
                images.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (!images.isEmpty()) {
            imageWidth = images.get(0).getWidth();
            imageHeight = images.get(0).getHeight();
        } else {
            imageWidth = 0;
            imageHeight = 0;
        }

        Timer timer = new Timer(1000, e -> updateAnimation());
        timer.start();
    }

    private void updateAnimation() {
        x += xSpeed;
        if (x > getWidth()) {
            x = -imageWidth; // Reset to the left side if it goes beyond the panel width
        }
        
        currentImageIndex = (currentImageIndex + 1) % images.size();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!images.isEmpty()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(images.get(currentImageIndex), x, 0, this);
        }
    }
}