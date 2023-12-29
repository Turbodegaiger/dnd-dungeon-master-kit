package ru.pda.dnddungeonmasterkit.graphics.panes.view;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;

import javax.swing.*;

@Slf4j
public class StartPane extends CustomJPanel {
    public StartPane(JButton button) {
        log.info("Drawing start pane...");
        this.setName("StartPane");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createBackground(), JLayeredPane.DEFAULT_LAYER, 2);
        layeredPane.add(createPicture("Start.jpg"), JLayeredPane.PALETTE_LAYER, 1);
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER, 0);

        add(layeredPane);
    }
}
