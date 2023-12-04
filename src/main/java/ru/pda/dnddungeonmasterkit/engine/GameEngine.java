package ru.pda.dnddungeonmasterkit.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.pda.dnddungeonmasterkit.creator.ButtonCreator;
import ru.pda.dnddungeonmasterkit.enums.ButtonAction;

import javax.swing.*;
import java.util.List;

@Component
@Slf4j
public class GameEngine implements ApplicationRunner {
    @Autowired
    GraphicsEngine graphicsEngine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        graphicsEngine.drawFrame();
        loadStartScreen();
    }

    void makeAction(ButtonAction action) {
        switch (action) {
            case START -> loadMenuScreen();
        }
    }

    private void loadStartScreen() {
        JButton startButton = ButtonCreator.getStartButton("START");
        startButton.addActionListener(al -> makeAction(ButtonAction.START));
        graphicsEngine.createStartScreen(startButton);
    }

    private void loadMenuScreen() {
        List<JButton> buttons = ButtonCreator.getMenuButtons(List.of(
                "Open world map",
                "Open local map",
                "Open battle mode",
                "Open creature collection",
                "Sound on/off"));
        for (JButton b : buttons) {
            switch (b.getName()) {
                case "Open world map" -> b.addActionListener(al -> makeAction(ButtonAction.WORLD_MAP));
                case "Open local map" -> b.addActionListener(al -> makeAction(ButtonAction.LOCAL_MAP));
                case "Open battle mode" -> b.addActionListener(al -> makeAction(ButtonAction.BATTLE));
                case "Open creature collection" -> b.addActionListener(al -> makeAction(ButtonAction.CREATURES));
                case "Sound on/off" -> b.addActionListener(al -> makeAction(ButtonAction.SOUND_ON_OFF));
            }
        }
        graphicsEngine.drawMenuScreen(buttons);
    }
}
