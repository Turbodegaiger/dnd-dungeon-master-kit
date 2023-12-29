package ru.pda.dnddungeonmasterkit.graphics.panes.view;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;
import ru.pda.dnddungeonmasterkit.util.FileManager;

import javax.swing.*;

@Slf4j
public class BackgroundPane extends CustomJPanel {
    public BackgroundPane(JButton button, String location) {
        log.info("Drawing background pane...");
        this.setName("BackgroundPane");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createBackground(), JLayeredPane.DEFAULT_LAYER, 0);
        layeredPane.add(createPicture(FileManager.backgrounds.get(location)), JLayeredPane.POPUP_LAYER, 50);
        layeredPane.add(button, JLayeredPane.DRAG_LAYER, 51);
        add(layeredPane);
    }
}
