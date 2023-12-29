package ru.pda.dnddungeonmasterkit.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.pda.dnddungeonmasterkit.creator.ButtonCreator;
import ru.pda.dnddungeonmasterkit.enums.LocationMenuAction;
import ru.pda.dnddungeonmasterkit.enums.MenuAction;
import ru.pda.dnddungeonmasterkit.util.FileManager;
import ru.pda.dnddungeonmasterkit.util.InsultGenerator;

import javax.swing.*;
import java.util.List;

@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@Slf4j
public class GameEngine implements ApplicationRunner {
    private final GraphicsEngine graphicsEngine;
    private final String backgroundPath;

    @Autowired
    public GameEngine(GraphicsEngine graphicsEngine,
                      @Value("${path-to-background.url:src\\main\\resources\\locations\\background}") String backgroundPath) {
        this.graphicsEngine = graphicsEngine;
        this.backgroundPath = backgroundPath;
        FileManager.getImages(backgroundPath);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        graphicsEngine.drawFrame();
        loadStartScreen();
    }

    private void loadStartScreen() {
        String action = "START";
        JButton startButton = ButtonCreator.getStartButton(action);
        startButton.addActionListener(al -> makeMenuAction(MenuAction.valueOf(action)));
        graphicsEngine.createStartScreen(startButton);
    }

    private void loadWorldMap() {
        String action = "BACK TO MENU";
        JButton backButton = ButtonCreator.getBackButton(action);
        backButton.addActionListener(al -> makeMenuAction(MenuAction.BACK_TO_MENU));
        graphicsEngine.drawWorldMap(backButton);
    }

    private void loadLocalMap(String locationName) {
        String action = "BACK";
        JButton backButton = ButtonCreator.getBackButton(action);
        backButton.addActionListener(al -> makeMenuAction(MenuAction.valueOf(action)));
        graphicsEngine.drawLocalMap(backButton, locationName);
    }

    private void loadBackgroundView(String location) {
        String action = "BACK";
        JButton backButton = ButtonCreator.getBackButton(action);
        backButton.addActionListener(al -> makeBackgroundAction(action));
        graphicsEngine.drawBackgroundView(backButton, location);
    }

    private void loadMenu() {
        List<JButton> buttons = ButtonCreator.getMenuButtons(List.of(
                "World map",
                "Local map",
                "Background view",
                "Battle mode",
                "Creature collection",
                "Sound on/off",
                "Utils",
                "Exit"));
        for (JButton b : buttons) {
            switch (b.getText()) {
                case "World map" -> b.addActionListener(al -> makeMenuAction(MenuAction.WORLD_MAP));
                case "Local map" -> b.addActionListener(al -> makeMenuAction(MenuAction.LOCAL_MAP));
                case "Background view" -> b.addActionListener(al -> makeMenuAction(MenuAction.BACKGROUND_VIEW));
                case "Battle mode" -> b.addActionListener(al -> makeMenuAction(MenuAction.BATTLE));
                case "Creature collection" -> b.addActionListener(al -> makeMenuAction(MenuAction.CREATURES));
                case "Sound on/off" -> b.addActionListener(al -> makeMenuAction(MenuAction.SOUND_ON_OFF));
                case "Utils" -> b.addActionListener(al -> makeMenuAction(MenuAction.UTILS));
                case "Exit" -> b.addActionListener(al -> makeMenuAction(MenuAction.EXIT));
            }
        }
        graphicsEngine.drawMenuScreen(buttons);
    }

    private void toggleSound() {
    }

    private void loadBackgroundViewMenu() {
        List<String> names = FileManager.getNames(backgroundPath);
        List<JButton> buttons = ButtonCreator.getLocationBackgroundMenuButton(names);
        JButton backButton = ButtonCreator.getBackButton("BACK TO MENU");
        backButton.addActionListener(al -> makeMenuAction(MenuAction.BACK_TO_MENU));
        buttons.add(backButton);

        int i = 0;
        String name = "";
        for (JButton b : buttons) {
            if (i < names.size()) {
                name = names.get(i++);
            }
            if (b.getText().equals(name)) {
                String finalName = name;
                b.addActionListener(al -> makeBackgroundAction(finalName));
            }
        }
        graphicsEngine.drawBackgroundViewMenuScreen(buttons);
    }

    private void loadLocationMenu() {
        List<JButton> buttons = ButtonCreator.getLocationMenuButtons(List.of(
                "Forest",
                "Divided village",
                "Fel garden",
                "Underground town",
                "Spiders lair",
                "Back to menu"));
        for (JButton b : buttons) {
            switch (b.getText()) {
                case "Forest" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.FOREST));
                case "Divided village" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.DIVIDED_VILLAGE));
                case "Fel garden" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.FEL_GARDEN));
                case "Underground town" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.UNDERGROUND_TOWN));
                case "Spiders lair" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.SPIDERS_LAIR));
                case "Back to menu" -> b.addActionListener(al -> makeLocationMenuAction(LocationMenuAction.BACK_TO_MENU));
            }
        }
        graphicsEngine.drawLocationMenuScreen(buttons);
    }

    private void generateInsult() {
        log.info("Придумываем оскорбления для Текилы...");
        String insult = InsultGenerator.generate();
        graphicsEngine.typeText(insult);
    }

    private void makeMenuAction(MenuAction action) {
        log.info("Selected makeLocationMenuAction: " + action.toString());
        switch (action) {
            case START -> loadMenu();
            case BACK -> loadLocationMenu();
            case BACK_TO_MENU -> loadMenu();
            case WORLD_MAP -> loadWorldMap();
            case BACKGROUND_VIEW -> loadBackgroundViewMenu();
            case LOCAL_MAP -> loadLocationMenu();
            case SOUND_ON_OFF -> toggleSound();
            case UTILS -> generateInsult();
            case EXIT -> exit();
        }
    }

    private void makeLocationMenuAction(LocationMenuAction action) {
        if (action == LocationMenuAction.BACK_TO_MENU) {
            makeMenuAction(MenuAction.BACK_TO_MENU);
            return;
        }
        log.info("Selected makeLocationMenuAction: " + action.toString());
        loadLocalMap(action.toString());
    }

    private void makeBackgroundAction(String action) {
        log.info("Selected makeLocationMenuAction: " + action);
        if (action.equals("BACK")) {
            loadBackgroundViewMenu();
        } else {
            loadBackgroundView(action);
        }
    }

    private void exit() {
        System.exit(0);
    }
}
