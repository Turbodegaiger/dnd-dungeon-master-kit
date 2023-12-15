package ru.pda.dnddungeonmasterkit.creator;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.graphics.buttons.start.BackButton;
import ru.pda.dnddungeonmasterkit.graphics.buttons.start.StartButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ButtonCreator {
    public static JButton getStartButton(String s) {
        JButton startButton = new StartButton();
        startButton.setText(s);
        startButton.setSize(100, 30);
        startButton.setLocation((int) (GraphicsEngine.standardResolution.getWidth()/2), 50);
        startButton.setHorizontalAlignment(SwingConstants.CENTER);
        return startButton;
    }

    public static JButton getBackButton(String s) {
        JButton backButton = new BackButton();
        backButton.setText(s);
        backButton.setSize(150, 30);
        backButton.setLocation((int) (GraphicsEngine.standardResolution.getWidth() - 175), 5);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        return backButton;
    }

    public static List<JButton> getMenuButtons(List<String> nameList) {
        List<JButton> buttonList = new ArrayList<>();
        int posY = 260;
        for (String s : nameList){
            JButton button = new JButton();
            button.setText(s);
            button.setSize(170, 30);
            button.setLocation((int) (GraphicsEngine.standardResolution.getWidth()/2) - 75, posY);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            buttonList.add(button);
            posY += 50;
        }
        return buttonList;
    }

    public static List<JButton> getLocationMenuButtons(List<String> nameList) {
        List<JButton> buttonList = new ArrayList<>();
        int posY = 150;
        for (String s : nameList){
            JButton button = new JButton();
            button.setText(s);
            button.setSize(170, 30);
            if (s.equals("Back to menu")) {
                posY += 150;
            }
            button.setLocation((int) (GraphicsEngine.standardResolution.getWidth()) - 200, posY);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            buttonList.add(button);
            posY += 50;
        }
        return buttonList;
    }
}
