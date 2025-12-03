package com.example.SceneManager.Scenes;

import com.example.Interfaces.INavigationService;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.WinningViewBuilder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class WinningScene extends MyScene {

    public WinningScene(
        INavigationService navigationService
    ) {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        // Build UI
        WinningViewBuilder viewBuilder = new WinningViewBuilder(navigationService);
        _root.getChildren().add(viewBuilder.build());
    }

    @Override
    public void initListener() {
    }
}
