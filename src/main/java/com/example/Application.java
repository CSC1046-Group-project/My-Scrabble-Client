package com.example;

import com.example.Network.Network;
import com.example.RendereUI.RendererUI;
import com.example.SceneManager.SceneManager;

import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    final String _appName = "MyScrabble";       // App name

    // Start the application
    @Override
    public void start(Stage stage) {

        RendererUI.init(stage, _appName);
        SceneManager.init();
        SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE);
        Network.run();

        run();
    }

    // Run the application
    private void run() {

    }

    // Stop the application and close network thread
    @Override
    public void stop() {
        Network.close();
    }
}
