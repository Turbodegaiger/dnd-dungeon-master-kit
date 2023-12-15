package ru.pda.dnddungeonmasterkit.engine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.enums.CurrentPane;
import ru.pda.dnddungeonmasterkit.graphics.panes.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Component
@Slf4j
@AllArgsConstructor
public class GraphicsEngine {
    public static Dimension standardResolution = new Dimension(1024, 768);
    private HashMap<String, JComponent> panes = new HashMap<>();
    private HashMap<String, JComponent> locations = new HashMap<>();

    private CurrentPane currentPane;
    JFrame frame;

    public GraphicsEngine() {
        this.frame = new JFrame("DnD Dungeon Master Kit");
    }

    public void drawFrame() {
        log.info("Drawing frame.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(standardResolution);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void createStartScreen(JButton startButton) {
        log.info("Drawing start screen.");
        JComponent startPane;
        if (panes.containsKey("StartPane")) {
            startPane = panes.get("StartPane");
            startPane.setVisible(true);
        } else {
            startPane = new StartPane(startButton);
            panes.put("StartPane", startPane);
        }
        drawScreen(startPane);
        currentPane = CurrentPane.START;
        log.info("Start screen ready.");
    }

    public void drawMenuScreen(List<JButton> buttons) {
        log.info("Drawing menu screen.");
        if (panes.containsKey("MapPane") &&
                currentPane != CurrentPane.MENU) {
            panes.get("MapPane").setVisible(false);
        }
        JComponent menuPane;
        if (panes.containsKey("MenuPane")) {
            menuPane = panes.get("MenuPane");
        } else {
            menuPane = new MenuPane(buttons);
            panes.put("MenuPane", menuPane);
        }
        drawScreen(menuPane);
        currentPane = CurrentPane.MENU;
        log.info("Menu screen ready.");
    }


    public void drawLocationMenuScreen(List<JButton> buttons) {
        log.info("Drawing location menu screen.");
        if (panes.containsKey("LocalMapPane") &&
                currentPane != CurrentPane.LOCAL_MAP) {
            panes.get("LocalMapPane").setVisible(false);
        }
        JComponent menuPane;
        if (panes.containsKey("LocationMenuPane")) {
            menuPane = panes.get("LocationMenuPane");
        } else {
            menuPane = new LocationMenuPane(buttons);
            panes.put("LocationMenuPane", menuPane);
        }
        drawScreen(menuPane);
        currentPane = CurrentPane.LOCAL_MAP;
        log.info("Location menu screen ready.");
    }

    public void drawWorldMap(JButton button) {
        log.info("Drawing world map screen.");
        JComponent worldMapPane;
        if (panes.containsKey("MapPane")) {
            worldMapPane = panes.get("MapPane");
            worldMapPane.setVisible(true);
        } else {
            worldMapPane = new MapPane(button);
            panes.put("MapPane", worldMapPane);
        }
        drawScreen(worldMapPane);
        currentPane = CurrentPane.WORLD_MAP;
        log.info("World map screen ready.");
    }


    public void drawLocalMap(JButton button, String locationName) {
        log.info("Drawing local map " + locationName + " screen.");
        JComponent localMapPane;
        if (locations.containsKey(locationName)) {
            localMapPane = locations.get(locationName);
            localMapPane.setVisible(true);
        } else {
            localMapPane = new LocalMapPane(button, locationName);
            locations.put(locationName, localMapPane);
        }
        drawScreen(localMapPane);
        currentPane = CurrentPane.LOCAL_MAP;
        log.info("Local map screen ready.");
    }

    public void typeText(String text) {
        JOptionPane.showMessageDialog(null, text,"Вас оскорбили!", JOptionPane.INFORMATION_MESSAGE);
    }


    private void drawScreen(JComponent component) {
        frame.setContentPane(component);
        frame.getLayeredPane().revalidate();
        frame.getLayeredPane().repaint();
        frame.repaint();
        frame.revalidate();
        frame.setVisible(true);
    }
}
