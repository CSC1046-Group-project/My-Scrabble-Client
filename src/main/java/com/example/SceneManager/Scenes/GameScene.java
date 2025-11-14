package com.example.SceneManager.Scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.Game.Tile;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TileBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;
import com.example.SceneManager.SceneManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
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
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE)
            .setMaxHeight(1880)
            .setPrefHeight(1880);


        // VBoxBuilder game = WidgetFactory.vbox();
        // VBoxBuilder panel = WidgetFactory.vbox();

        header(header);
        boardView(gameView);
        rightSidePanel();

        // gameView.add(game.getNode(), panel.getNode());
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

    private void boardView(HBoxBuilder gameView) {

        // REMOVE THIS SAMPLE EXAMPLE
        List<Tile> tiles = new ArrayList<>();
        String[] letters = { "E", "E", "O", "R", "H", "D", "U" };
        int[] points     = {  1,   1,   1,   1,   4,   2,   1 };
        for (int i = 0; i < letters.length; i++) {
            Tile tile = new Tile();
            tile.setLetter(letters[i]);
            tile.setPoint(points[i]);
            tiles.add(tile);
        }

        tileRack(gameView, tiles);
    }

    private void tileRack(HBoxBuilder gameView, List<Tile> tiles) {

        Pane rack = new Pane();
        rack.setPrefWidth(550);
        rack.setPrefHeight(86);
        rack.setMaxWidth(550);
        rack.setMaxHeight(86);
        rack.setStyle("-fx-background-color: #222226;");

        // Shuffle button (left)
        IconButtonBuilder shuffleIcon = WidgetFactory.iconButton(
            "/assets/shuffle.png",
            e -> {}
        ).setFitWidth(32).setFitHeight(32)
         .setStyle("-fx-background-color: #282833");

        shuffleIcon.getNode().setLayoutX(13);
        shuffleIcon.getNode().setLayoutY(16);

        // Rotate icon (right)
        IconButtonBuilder rotateIcon = WidgetFactory.iconButton(
            "/assets/rotate.png",
            e -> {}
        ).setFitWidth(32).setFitHeight(32)
        .setStyle("-fx-background-color: #282833");

        rotateIcon.getNode().setLayoutX(480);
        rotateIcon.getNode().setLayoutY(16);

        // One draggable tile
        // TileBuilder tile = WidgetFactory.tile("E", 1);
        // tile.getNode().setLayoutX(100);
        // tile.getNode().setLayoutY(10);

        rack.getChildren().addAll(
            shuffleIcon.getNode(),
            rotateIcon.getNode()
        );


        AtomicInteger x = new AtomicInteger(82);
        tiles.forEach(t -> {
            TileBuilder tile = WidgetFactory.tile(t.getLetter(), t.getPoint(), x.get(), 16);
            rack.getChildren().add(tile.getNode());
            x.addAndGet(56);
        });

        gameView.add(rack);
    }

    private void rightSidePanel() {

    }
}
