package ru.pda.dnddungeonmasterkit.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.pda.dnddungeonmasterkit.creator.ButtonCreator;
import ru.pda.dnddungeonmasterkit.enums.LocationMenuButtonAction;
import ru.pda.dnddungeonmasterkit.enums.MenuButtonAction;
import ru.pda.dnddungeonmasterkit.util.InsultGenerator;

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

    private void makeMenuAction(MenuButtonAction action) {
        log.info("Selected action: " + action.toString());
        switch (action) {
            case START -> loadMenuScreen();
            case BACK -> loadLocationMenuScreen();
            case BACK_TO_MENU -> loadMenuScreen();
            case WORLD_MAP -> loadWorldMap();
            case LOCAL_MAP -> loadLocationMenuScreen();
            case SOUND_ON_OFF -> toggleSound();
            case UTILS -> generateInsult();
        }
    }

    private void generateInsult() {
        log.info("Придумываем оскорбления для Текилы...");
        String insult = InsultGenerator.generate();
        graphicsEngine.typeText(insult);
    }

    private void makeLocationMenuAction(LocationMenuButtonAction action) {
        if (action == LocationMenuButtonAction.BACK_TO_MENU) {
            makeMenuAction(MenuButtonAction.BACK_TO_MENU);
            return;
        }
        log.info("Selected action: " + action.toString());
        loadLocalMap(action.toString());
    }

    private void loadWorldMap() {
        String action = "BACK TO MENU";
        JButton backButton = ButtonCreator.getBackButton(action);
        backButton.addActionListener(al -> makeMenuAction(MenuButtonAction.BACK_TO_MENU));
        graphicsEngine.drawWorldMap(backButton);
    }

    private void loadLocalMap(String locationName) {
        String action = "BACK";
        JButton backButton = ButtonCreator.getBackButton(action);
        backButton.addActionListener(al -> makeMenuAction(MenuButtonAction.valueOf(action)));
        graphicsEngine.drawLocalMap(backButton, locationName);
    }

    private void toggleSound() {
    }

    private void loadStartScreen() {
        String action = "START";
        JButton startButton = ButtonCreator.getStartButton(action);
        startButton.addActionListener(al -> makeMenuAction(MenuButtonAction.valueOf(action)));
        graphicsEngine.createStartScreen(startButton);
    }

    private void loadMenuScreen() {
        List<JButton> buttons = ButtonCreator.getMenuButtons(List.of(
                "Open world map",
                "Open local map",
                "Open battle mode",
                "Open creature collection",
                "Sound on/off",
                "Utils"));
        for (JButton b : buttons) {
            switch (b.getText()) {
                case "Open world map" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.WORLD_MAP));
                case "Open local map" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.LOCAL_MAP));
                case "Open battle mode" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.BATTLE));
                case "Open creature collection" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.CREATURES));
                case "Sound on/off" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.SOUND_ON_OFF));
                case "Utils" -> b.addActionListener(al -> makeMenuAction(MenuButtonAction.UTILS));
            }
        }
        graphicsEngine.drawMenuScreen(buttons);
    }

    private void loadLocationMenuScreen() {
        List<JButton> buttons = ButtonCreator.getLocationMenuButtons(List.of(
                "Forest",
                "Divided village",
                "Fel garden",
                "Underground town",
                "Spiders lair",
                "Back to menu"));
        for (JButton b : buttons) {
            switch (b.getText()) {
                case "Forest" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.FOREST));
                case "Divided village" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.DIVIDED_VILLAGE));
                case "Fel garden" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.FEL_GARDEN));
                case "Underground town" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.UNDERGROUND_TOWN));
                case "Spiders lair" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.SPIDERS_LAIR));
                case "Back to menu" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuButtonAction.BACK_TO_MENU));
            }
        }
        graphicsEngine.drawLocationMenuScreen(buttons);
    }
}
