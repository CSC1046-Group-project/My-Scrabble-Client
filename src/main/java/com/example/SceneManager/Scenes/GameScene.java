package com.example.SceneManager.Scenes;

import com.example.SceneManager.MyScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class GameScene extends MyScene {

    @Override
    public void initRoot() {

         // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        header();
        boardView();
        rightSidePanel();
    }

    private void header() {

    }

    private void boardView() {

    }

    private void rightSidePanel() {

    }
}
