package ru.pda.dnddungeonmasterkit.graphics.panes;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;

import javax.swing.*;
import java.util.List;

@Slf4j
public class LocationMenuPane extends MenuPane {
    public LocationMenuPane(List<JButton> button) {
        log.info("Drawing location menu pane...");
        this.setName("LocationMenuPane");
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        for (int i = 0; i < button.size(); i++) {
            layeredPane.add(button.get(i), JLayeredPane.MODAL_LAYER, i+2);
        }
        setOpaque(false);
        add(layeredPane);
    }
}
