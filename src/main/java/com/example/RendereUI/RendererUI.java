package com.example.RendereUI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RendererUI {

    private static Stage _stage;
    private static Scene _scene;
    private static StackPane _root;

    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;

    public static void init(Stage stage, final String appName) {
        _stage = stage;
        _stage.setTitle(appName);
        _root = new StackPane();
        _scene = new Scene(_root, WIDTH, HEIGHT);
        _stage.setScene(_scene);
        _stage.show();
    }

    public static void draw(StackPane newRoot) {
        _root = newRoot;
        _scene.setRoot(_root);
    }
}
