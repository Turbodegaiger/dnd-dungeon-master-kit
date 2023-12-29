package ru.pda.dnddungeonmasterkit.graphics.panes.menu;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;

import javax.swing.*;
import java.util.List;

@Slf4j
public class BackgroundMenuPane extends CustomJPanel {
    public BackgroundMenuPane(List<JButton> button) {
        log.info("Drawing background menu pane...");
        this.setName("BackgroundMenuPane");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createBackground(), JLayeredPane.DEFAULT_LAYER, 0);
        for (int i = 0; i < button.size(); i++) {
            layeredPane.add(button.get(i), JLayeredPane.MODAL_LAYER, i+2);
        }
        add(layeredPane);
    }
}
