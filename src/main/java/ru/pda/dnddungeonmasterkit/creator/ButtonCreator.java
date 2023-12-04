package ru.pda.dnddungeonmasterkit.creator;

import lombok.extern.slf4j.Slf4j;
import ru.pda.dnddungeonmasterkit.engine.GraphicsEngine;
import ru.pda.dnddungeonmasterkit.enums.ButtonAction;
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

    public static List<JButton> getMenuButtons(List<String> nameList) {
        List<JButton> buttonList = new ArrayList<>();
        int posY = 100;
        for (String s : nameList){
            JButton button = new JButton();
            button.setText(s);
            button.setSize(125, 30);
            button.setLocation((int) (GraphicsEngine.standardResolution.getWidth()/2), posY);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            buttonList.add(button);
            posY += 50;
        }
        return buttonList;
    }
}
