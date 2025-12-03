package com.example.UIBuilder;

import com.example.Controllers.GameController;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.BoardBuilder;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameViewBuilder {

    private GameController _controller;

    // widgets to share
    private VBoxBuilder _usersBox;
    private ButtonBuilder _readyButton;
    private VBoxBuilder _tilesleft;
    private BoardBuilder _board;
    private ButtonBuilder _submitButton;
    private ButtonBuilder _skipButton;
    private ButtonBuilder _swapButton;
    private Pane _rack;

    public GameViewBuilder() {}

    public void setController(GameController controller) {
        _controller = controller;
    }

    public Node build() {

        VBoxBuilder page = WidgetFactory.vbox()
            .setStyle("-fx-background-color: transparent;")
            .setPadding(0);

        HBoxBuilder gameView = WidgetFactory.hbox()
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE)
            .setMaxHeight(1880)
            .setPrefHeight(1880);

        HBoxBuilder header = buildHeader();
        VBoxBuilder game = buildBoardView();
        VBoxBuilder panel = buildRightSidePanel();

        gameView.addWithFlex(game.getNode(), panel.getNode());
        page.add(header.getNode(), gameView.getNode());
        return page.getNode();
    }

    private HBoxBuilder buildHeader() {

        HBoxBuilder header = WidgetFactory.hbox()
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE);

        // TODO: Give to the setting page the return value of the GAME SCENE
        // // Create settings icon
        // IconButtonBuilder settingsIcon = WidgetFactory.iconButton(
        //     "/assets/settings.png",
        //     e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        // ).setFitWidth(24).setFitHeight(24);
        // // Create settings button
        // ButtonBuilder settingsButton = WidgetFactory.button(
        //     "Settings",
        //     e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        // ).setPrefHeight(34)
        //     .setFont(16)
        //     .setStyle("-fx-background-color: transparent;")
        //     .setMaxWidth(120)
        //     .setAlignment(Pos.CENTER_LEFT);
        // HBoxBuilder settings = WidgetFactory.hbox().setSpacing(0).setStyle("-fx-background-color: transparent;");
        // settings.add(settingsIcon.getNode(), settingsButton.getNode());

        // Game code/password
        TextBuilder gameCode = WidgetFactory.text("Private Game 56-54-24-12-43#Password");

        header.addWithFlex(gameCode.getNode());
        return header;
    }

    private VBoxBuilder buildBoardView() {

        VBoxBuilder game = WidgetFactory.vbox()
            .setMaxWidth(1000)
            .setPrefWidth(1000)
            .setStyle("-fx-background-color: transparent;");

        // _tileRack = new TileRack();

        _board = WidgetFactory.board(15, 15, 50);

        ButtonBuilder challengeButton = WidgetFactory.button(
            "Challenge",
            e -> _controller.handleChallenge())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #C04D4D; -fx-background-radius: 10;");

        initTileRack();
        HBoxBuilder challengeRackBlock = WidgetFactory.hbox()
            .setMaxWidth(696)
            .setPrefWidth(696)
            .setStyle("-fx-background-color: transparent;");
        challengeRackBlock.add(challengeButton.getNode(), _rack);

        HBoxBuilder buttons = WidgetFactory.hbox().setStyle("-fx-background-color: transparent;").setSpacing(20);
        ButtonBuilder resignButton = WidgetFactory.button("Resign", e -> _controller.handleResign())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        _skipButton = WidgetFactory.button("Skip", e -> _controller.handleSkip())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        _swapButton = WidgetFactory.button("Swap", e -> _controller.handleSwap())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        _submitButton = WidgetFactory.button("Submit", e -> _controller.handleSubmit())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5);
        buttons.add(resignButton.getNode(), _skipButton.getNode(), _swapButton.getNode(), _submitButton.getNode());
        game.add(_board.getNode(), challengeRackBlock.getNode(), buttons.getNode());

        return game;
    }

    private VBoxBuilder buildRightSidePanel() {

        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(850)
            .setPrefWidth(550)
            .setAlignment(Pos.CENTER_RIGHT);

        _usersBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833");

        _readyButton = WidgetFactory.button(
            "Set Ready",
            e -> _controller.handleReady())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5);

        _usersBox.add(_readyButton.getNode());

        VBoxBuilder tilebagBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(1);
        HBoxBuilder tiletext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");

        TextBuilder tilebagText = WidgetFactory.text("Tile bag - 81 tiles left").setFont(16);
        tiletext.add(tilebagText.getNode());

        _tilesleft = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        tilebagBox.add(tiletext.getNode(), _tilesleft.getNode());

        VBoxBuilder turnhistoryBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        HBoxBuilder turnhistorytext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        TextBuilder turnhistoryText = WidgetFactory.text("Turn History").setFont(16);
        turnhistorytext.add(turnhistoryText.getNode());

        panel.addWithFlex(_usersBox.getNode(), tilebagBox.getNode(), turnhistoryBox.getNode());
        return panel;
    }

    private void initTileRack() {

        _rack = new Pane();
        _rack.setPrefWidth(550);
        _rack.setPrefHeight(86);
        _rack.setMaxWidth(550);
        _rack.setMaxHeight(86);
        _rack.setStyle("-fx-background-color: #222226;");
    }

    public VBoxBuilder getUsersBox() {
        return _usersBox;
    }

    public ButtonBuilder getReadyButton() {
        return _readyButton;
    }

    public VBoxBuilder getTilesLeft() {
        return _tilesleft;
    }

    public ButtonBuilder getSubmitButton(){
        return _submitButton;
    }

    public ButtonBuilder getSkipButton(){
        return _skipButton;
    }

    public ButtonBuilder getSwapButton(){
        return _swapButton;
    }

    public Pane getRack() {
        return _rack;
    }

    public BoardBuilder getBoard() {
        return _board;
    }
}
