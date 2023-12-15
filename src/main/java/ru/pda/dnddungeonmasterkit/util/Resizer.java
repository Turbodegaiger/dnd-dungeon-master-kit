package ru.pda.dnddungeonmasterkit.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Resizer {
    public static ImageIcon resizeImageIcon(ImageIcon imageIcon , Integer width , Integer height) {
        BufferedImage bufferedImage = new BufferedImage( width , height , BufferedImage.TRANSLUCENT );

        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(imageIcon.getImage() , 0 , 0 , width , height , null);
        graphics2D.dispose();

        return new ImageIcon(bufferedImage , imageIcon.getDescription());
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
        int newWidth = boundary.width;
        int newHeight = boundary.height;
        if (imgSize.height > imgSize.width && imgSize.getHeight() > boundary.getHeight()) {
            newHeight = boundary.height;
            newWidth = imgSize.width / (imgSize.height / boundary.height);
        } else if (imgSize.getWidth() > boundary.getWidth()) {
            newWidth = boundary.width;
            newHeight = imgSize.height / (imgSize.width / boundary.width);
        }
        return new Dimension(newWidth, newHeight);
    }
}
