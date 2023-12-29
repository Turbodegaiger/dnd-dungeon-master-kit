package ru.pda.dnddungeonmasterkit.graphics.panes.menu;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;

import javax.swing.*;
import java.util.List;

@Slf4j
public class MenuPane extends CustomJPanel {
    public MenuPane() {}
    public MenuPane(List<JButton> button) {
        log.info("Drawing menu pane...");
        this.setName("MenuPane");
        String fileName = "Start.jpg";
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createBackground(), JLayeredPane.DEFAULT_LAYER, 0);
        layeredPane.add(createPicture(fileName), JLayeredPane.PALETTE_LAYER, 1);
        for (int i = 0; i < button.size(); i++) {
            layeredPane.add(button.get(i), JLayeredPane.MODAL_LAYER, i+2);
        }
        add(layeredPane);
    }
}
