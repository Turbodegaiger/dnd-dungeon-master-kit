package ru.pda.dnddungeonmasterkit.util;

import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Resizer {
    private static ImageIcon resizeImageIcon(ImageIcon imageIcon , Integer width , Integer height) {
        BufferedImage bufferedImage = new BufferedImage(width , height , BufferedImage.TRANSLUCENT);

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        graphics2D.dispose();

        return new ImageIcon(bufferedImage , imageIcon.getDescription());
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        float newWidth;
        float newHeight;
        float ratio = (float) imgSize.width / imgSize.height;
        float scaleRatio;
        if (ratio > ((float) boundary.width / boundary.height)) {
            scaleRatio = (float) imgSize.width / boundary.width;
        } else {
            scaleRatio = (float) imgSize.height / boundary.height;
        }
        newWidth = imgSize.width / scaleRatio;
        newHeight = imgSize.height / scaleRatio;
        return new Dimension((int) newWidth, (int) newHeight);
    }

    public static JLabel getFormattedImage(ImageIcon icon) {
        final Dimension correctRatio = getScaledDimension(
                new Dimension(icon.getIconWidth(), icon.getIconHeight()),
                GraphicsEngine.standardResolution);
        return new JLabel(resizeImageIcon(icon, correctRatio.width, correctRatio.height));
    }
}
