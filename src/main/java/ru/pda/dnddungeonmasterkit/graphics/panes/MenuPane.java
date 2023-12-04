package ru.pda.dnddungeonmasterkit.graphics.panes;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Slf4j
public class MenuPane extends JPanel {
    public MenuPane(List<JButton> button) {
        log.info("Drawing menu pane...");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1024, 768));

        layeredPane.add(createBackground(), JLayeredPane.DEFAULT_LAYER, 2);
        layeredPane.add(createPicture(), JLayeredPane.PALETTE_LAYER, 1);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER, 0);

        add(layeredPane);
    }
    private JLabel createPicture() {
        final ImageIcon icon = new ImageIcon("src/main/resources/Start.jpg");
        JLabel label = new JLabel(icon);
        if (icon != null) {
            label.setBounds(15, 170,
                    icon.getIconWidth(),
                    icon.getIconHeight());
        } else {
            System.err.println("Menu icon not found; using black square instead.");
            label.setBounds(15, 170, 30, 30);
            label.setOpaque(true);
            label.setBackground(Color.BLACK);
        }
        return label;
    }

    private JLabel createBackground() {
        JLabel label = new JLabel();
        label.setBounds(5, 5, 5, 5);
        label.setSize(GraphicsEngine.standardResolution);
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        return label;
    }
}
