package com.example;

import com.example.RendereUI.RendererUI;
import com.example.SceneManager.SceneManager;

import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    final String _appName = "MyScrabble";

    @Override
    public void start(Stage stage) {

        RendererUI.init(stage, _appName);
        SceneManager.init();
        SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE);

        run();
    }

    private void run() {

    }
}
