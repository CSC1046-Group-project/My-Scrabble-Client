package com.example.RendereUI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Class to render the application
public class RendererUI {

    private static Stage _stage;        // Window
    private static Scene _scene;        // Current scene to display
    private static StackPane _root;     // Main window component

    private static final int WIDTH = 1920;  // Width of the window
    private static final int HEIGHT = 1080; // Height of the window

    // Init the renderer
    public static void init(Stage stage, final String appName) {
        _stage = stage;
        _stage.setTitle(appName);
        _root = new StackPane();
        _scene = new Scene(_root, WIDTH, HEIGHT);
        _stage.setScene(_scene);
        _stage.show();
    }

    // Draw a scene on the screen
    public static void draw(StackPane newRoot) {
        _root = newRoot;
        _scene.setRoot(_root);
    }
}
