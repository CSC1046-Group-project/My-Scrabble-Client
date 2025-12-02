package com.example.SceneManager.Scenes;

import com.example.Interfaces.INavigationService;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.FirstViewBuilder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FirstScene extends MyScene {

    public FirstScene(INavigationService navigationService) {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        // Build UI
        FirstViewBuilder viewBuilder = new FirstViewBuilder(navigationService);

        // Add the ui to the root container
        _root.getChildren().addAll(viewBuilder.build());
    }

    @Override
    public void initRoot() {
    }

    @Override
    public void initListener() {
    }
}
