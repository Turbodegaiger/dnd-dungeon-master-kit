package ru.pda.dnddungeonmasterkit.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class GameEngine implements ApplicationRunner {
    @Autowired
    GraphicsEngine graphicsEngine;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        graphicsEngine.drawFrame();
        loadStartScreen();
    }

    private void loadStartScreen() {
        graphicsEngine.drawStartScreen();
    }
}
