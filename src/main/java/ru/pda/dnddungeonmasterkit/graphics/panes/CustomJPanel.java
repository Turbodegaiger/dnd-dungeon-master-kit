package ru.pda.dnddungeonmasterkit.graphics.panes;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;

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
        final ImageIcon icon = new ImageIcon("src/main/resources/" + fileName);
        JLabel label = new JLabel(icon);
        if (icon.getIconWidth() > 0) {
            label.setBounds(15, 170,
                    icon.getIconWidth(),
                    icon.getIconHeight());
        } else {
            log.info("File " + fileName + " not found, cannot load an Icon; using black square instead.");
            label.setBounds(15, 170, 30, 30);
            label.setOpaque(true);
            label.setBackground(Color.BLACK);
        }
        return label;
    }
}
