package ru.pda.dnddungeonmasterkit.graphics.panes;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;

import javax.swing.*;

@Slf4j
public class LocalMapPane extends MapPane {
    public LocalMapPane(JButton button, String locationName) {
        log.info("Drawing local map pane...");
        this.setName(locationName);
        String fileName = "locations/" + locationName + ".jpg";
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);


        layeredPane.add(createPicture(fileName), JLayeredPane.POPUP_LAYER, 40);
        layeredPane.add(button, JLayeredPane.DRAG_LAYER, 41);
        add(layeredPane);
    }
}
