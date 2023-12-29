package ru.pda.dnddungeonmasterkit.graphics.panes.view;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;

import javax.swing.*;

@Slf4j
public class LocationPane extends CustomJPanel {
    public LocationPane(JButton button, String locationName) {
        log.info("Drawing local background pane...");
        this.setName(locationName);
        String fileName = "locations/background/" + locationName + ".jpg";
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createPicture(fileName), JLayeredPane.POPUP_LAYER, 50);
        layeredPane.add(button, JLayeredPane.DRAG_LAYER, 51);
        add(layeredPane);
    }
}
