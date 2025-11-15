package com.example.SceneManager.Scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.Game.Tile;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.BoardBuilder;
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

    private BoardBuilder _board;

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


        VBoxBuilder game = WidgetFactory.vbox()
            .setMaxWidth(1000)
            .setPrefWidth(1000)
            .setStyle("-fx-background-color: transparent;");

        VBoxBuilder panel = WidgetFactory.vbox();

        header(header);
        boardView(game);
        rightSidePanel();

        gameView.add(game.getNode(), panel.getNode());
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

    private void boardView(VBoxBuilder game) {

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

        _board = WidgetFactory.board(15, 15, 50);
        // TileBuilder tile1 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile2 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile3 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile4 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile5 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile6 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile7 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile8 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile9 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile10 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile11 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile12 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile13 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile14 = WidgetFactory.tile("h", 2, 0, 0);
        // TileBuilder tile15 = WidgetFactory.tile("h", 2, 0, 0);
        // _board.addTile(tile1, 0, 1);
        // _board.addTile(tile2, 0, 2);
        // _board.addTile(tile3, 0, 3);
        // _board.addTile(tile4, 0, 4);
        // _board.addTile(tile5, 0, 5);
        // _board.addTile(tile6, 0, 6);
        // _board.addTile(tile7, 0, 7);
        // _board.addTile(tile8, 0, 8);
        // _board.addTile(tile9, 0, 9);
        // _board.addTile(tile10, 0, 10);
        // _board.addTile(tile11, 0, 11);
        // _board.addTile(tile12, 0, 12);
        // _board.addTile(tile13, 0, 13);
        // _board.addTile(tile14, 0, 14);
        // _board.addTile(tile15, 0, 0);

        Pane rack = tileRack(tiles);

        HBoxBuilder buttons = WidgetFactory.hbox().setStyle("-fx-background-color: transparent;").setSpacing(20);
        ButtonBuilder resignButton = WidgetFactory.button("Resign", e -> {})
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder skipButton = WidgetFactory.button("Skip", e -> {})
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder swapButton = WidgetFactory.button("Swap", e -> {})
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder submitButton = WidgetFactory.button("Submit", e -> {})
            .setPrefWidth(122.5)
            .setMaxWidth(122.5);
        buttons.add(resignButton.getNode(), skipButton.getNode(), swapButton.getNode(), submitButton.getNode());
        game.add(_board.getNode(), rack, buttons.getNode());
    }

    private Pane tileRack(List<Tile> tiles) {

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

        rack.getChildren().addAll(
            shuffleIcon.getNode(),
            rotateIcon.getNode()
        );

        AtomicInteger x = new AtomicInteger(82);
        tiles.forEach(t -> {
            TileBuilder tile = WidgetFactory.tile(t, x.get(), 16);
            tile.setOnRelease((a,b) -> test(a, b));
            rack.getChildren().add(tile.getNode());
            x.addAndGet(56);
        });

        return rack;
    }

    private void rightSidePanel() {

    }

    public void test(TileBuilder tile, double[] pos) {
        Integer[] mousePosOnBoard = _board.getCellHover(pos[0], pos[1]);
        TileBuilder newTile = WidgetFactory.tile(tile.getTile(), 0, 0);
        _board.addTile(newTile, mousePosOnBoard[0], mousePosOnBoard[1]);
    }
}
