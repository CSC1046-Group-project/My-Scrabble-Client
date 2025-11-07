package com.example.RendereUI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RendererUI {

    private static Stage _stage;

    public static void init(Stage stage, final String appName) {
        _stage = stage;
        _stage.setTitle(appName);
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 960, 540);
        _stage.setScene(scene);
        _stage.show();
    }

    public static Stage getStage() {
        return _stage;
    }

    public static void draw() {
        _stage.show();
    }
}
