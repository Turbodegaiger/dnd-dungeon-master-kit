package ru.pda.dnddungeonmasterkit.engine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.pda.dnddungeonmasterkit.graphics.panes.MenuPane;
import ru.pda.dnddungeonmasterkit.graphics.panes.StartPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class GraphicsEngine {
    public static Dimension standardResolution = new Dimension(1024, 768);
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
        JComponent startPane = new StartPane(startButton);
        drawScreen(startPane);
        log.info("Start screen ready.");
    }

    public void drawMenuScreen(List<JButton> buttons) {
        log.info("Drawing menu screen.");
        JComponent menuPane = new MenuPane(buttons);

    }

    private void drawScreen(JComponent component) {
        frame.removeAll();
        frame.setContentPane(component);
        frame.setVisible(true);
    }
}
