package com.example.SceneManager.Scenes;

import com.example.SceneManager.MyScene;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FirstScene extends MyScene {

    @Override
    public void initRoot() {

        Button but = new Button("Login");
        _root.getChildren().add(but);

        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
    }
}
