package com.example.SceneManager;

import com.example.RendereUI.RendererUI;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public abstract class MyScene {

    private final Scene _scene;

    public MyScene () {
        StackPane root = new StackPane();
        _scene = new Scene(root);
        _scene.setFill(Color.web("0x16161E"));
    }

    public Scene getScene() {
        return _scene;
    }

    public void load() {
        RendererUI.getStage().setScene(_scene);
        RendererUI.draw();
    }

    public abstract void onDraw();
}
