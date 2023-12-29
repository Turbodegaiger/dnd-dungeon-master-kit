package ru.pda.dnddungeonmasterkit.graphics.panes.view;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.panes.CustomJPanel;

import javax.swing.*;

@Slf4j
public class MapPane extends CustomJPanel {
    public MapPane() {}
    public MapPane(JButton button) {
        log.info("Drawing world map pane...");
        this.setName("MenuPane");
        String fileName = "DndMap.jpg";
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GraphicsEngine.standardResolution);

        layeredPane.add(createPicture(fileName), JLayeredPane.POPUP_LAYER, 40);
        layeredPane.add(button, JLayeredPane.DRAG_LAYER, 41);
        add(layeredPane);
    }
//
//    @Override
//    protected JLabel createPicture(String fileName) {;
//        final ImageIcon iconFile = new ImageIcon("src/main/resources/" + fileName);
//        JLabel label = new JLabel();;
//        if (iconFile.getIconWidth() > 0) {
//            label = Resizer.getFormattedImage(iconFile);
//            label.setBounds(0, 0,
//                    (int) GraphicsEngine.standardResolution.getWidth(),
//                    (int) GraphicsEngine.standardResolution.getHeight());
//        } else {
//            log.info("File " + fileName + " not found, cannot load an Icon; using black square instead.");
//            label.setBounds(0, 0, GraphicsEngine.standardResolution.width, GraphicsEngine.standardResolution.height);
//            label.setOpaque(true);
//            label.setBackground(Color.BLACK);
//            label.setText("Image not found.");
//        }
//        return label;
//    }
}
