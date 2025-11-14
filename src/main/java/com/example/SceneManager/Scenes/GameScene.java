package com.example.SceneManager.Scenes;

import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;
import com.example.SceneManager.SceneManager;

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

        VBoxBuilder page = WidgetFactory.vbox()
            .setStyle("-fx-background-color: transparent;")
            .setPadding(0);
        HBoxBuilder header = WidgetFactory.hbox()
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE);

        HBoxBuilder gameView = WidgetFactory.hbox()
            // .setStyle("-fx-background-color: blue;")
            .setMaxWidth(Double.MAX_VALUE)
            .setMaxHeight(1880)
            .setPrefHeight(1880);

        LineBuilder testLine = WidgetFactory.line();
        gameView.add(testLine.getNode());

        header(header);
        boardView();
        rightSidePanel();

        page.add(header.getNode(), gameView.getNode());
        _root.getChildren().addAll(page.getNode());
    }

    private void header(HBoxBuilder header) {

        // Create settings icon
        IconButtonBuilder settingsIcon = WidgetFactory.iconButton(
            "/assets/settings.png",
            e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        ).setFitWidth(24).setFitHeight(24);
        // Create settings button
        ButtonBuilder settingsButton = WidgetFactory.button(
            "Settings",
            e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        ).setPrefHeight(34)
            .setFont(16)
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(120)
            .setAlignment(Pos.CENTER_LEFT);
        HBoxBuilder settings = WidgetFactory.hbox().setSpacing(0).setStyle("-fx-background-color: transparent;");
        settings.add(settingsIcon.getNode(), settingsButton.getNode());

        // Game code/password
        TextBuilder gameCode = WidgetFactory.text("Private Game 56-54-24-12-43#Password");

        header.addWithFlex(settings.getNode(), gameCode.getNode());
    }

    private void boardView() {

    }

    private void rightSidePanel() {

    }
}
