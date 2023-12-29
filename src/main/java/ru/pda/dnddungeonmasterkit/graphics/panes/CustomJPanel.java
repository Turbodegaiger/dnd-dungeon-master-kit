package ru.pda.dnddungeonmasterkit.graphics.panes;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.util.Resizer;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class CustomJPanel extends JPanel {
    protected JLabel createBackground() {
        JLabel label = new JLabel();
        label.setBounds(5, 5, 5, 5);
        label.setSize(GraphicsEngine.standardResolution);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        return label;
    }

    protected JLabel createPicture(String fileName) {
        final ImageIcon iconFile = new ImageIcon("src/main/resources/" + fileName);
        return createPicture(iconFile);
    }

    protected JLabel createPicture(ImageIcon image) {
        JLabel label = new JLabel();
        if (image != null && image.getIconWidth() > 0) {
            label = Resizer.getFormattedImage(image);
            label.setBounds(0, 0,
                    (int) GraphicsEngine.standardResolution.getWidth(),
                    (int) GraphicsEngine.standardResolution.getHeight());
        } else {
            log.info("File not found, cannot load an Icon; using black square instead.");
            label.setBounds(0, 0, GraphicsEngine.standardResolution.width, GraphicsEngine.standardResolution.height);
            label.setOpaque(true);
            label.setBackground(Color.BLACK);
            label.setText("Image not found.");
        }
        return label;
    }
}
