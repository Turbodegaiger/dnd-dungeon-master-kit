package ru.pda.dnddungeonmasterkit.engine;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.pda.dnddungeonmasterkit.graphics.buttons.menu.StartButton;
import ru.pda.dnddungeonmasterkit.graphics.panes.StartPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
@Slf4j
@AllArgsConstructor
public class GraphicsEngine implements ActionListener {
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

    public void drawStartScreen() {
        log.info("Drawing menu screen.");
        JComponent startPane = new StartPane(getStartButton("START"));
        frame.setContentPane(startPane);
        frame.setVisible(true);
        log.info("Start screen ready.");
    }

    public JButton getStartButton(String text) {
        log.info("Creating button.");
        JButton startButton = new StartButton();
        startButton.setText(text);
        startButton.setSize(100, 30);
        startButton.setLocation((int) (standardResolution.getWidth()/2), 50);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        startButton.addActionListener(this);
        return startButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
