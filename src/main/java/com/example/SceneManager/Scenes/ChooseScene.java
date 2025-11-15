package com.example.SceneManager.Scenes;

import com.example.Game.User;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;
import com.example.Network.Protocol.ProtocolMessage;
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

public class ChooseScene extends MyScene {

    @Override
    public void initRoot() {
        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        // Centered Panel
        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550);

        // Create empty button just to center the title on the middle
        IconButtonBuilder nothing1 = WidgetFactory.iconButton("", e -> {});
        // Title
        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);
        // Create empty button just to center the title on the middle
        IconButtonBuilder nothing2 = WidgetFactory.iconButton("", e -> {});
        HBoxBuilder header = WidgetFactory.hbox();
        header.addWithFlex(nothing1.getNode(), title.getNode(), nothing2.getNode());

        // Line under title
        LineBuilder titleLine = WidgetFactory.line();
        // Group title + line
        VBoxBuilder titleBox = WidgetFactory.vbox();
        titleBox.add(header.getNode(), titleLine.getNode());


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
        HBoxBuilder settings = WidgetFactory.hbox().setSpacing(0);
        settings.add(settingsIcon.getNode(), settingsButton.getNode());


        // Create logout icon
        IconButtonBuilder logoutIcon = WidgetFactory.iconButton(
            "/assets/logout.png",
            e -> logout()
        ).setFitWidth(24).setFitHeight(24);
        // Create logout button
        ButtonBuilder logoutButton = WidgetFactory.button(
            "Disconnect",
            e -> SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE)
        ).setPrefHeight(34)
            .setFont(16)
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(120)
            .setAlignment(Pos.CENTER_LEFT);
        HBoxBuilder logout = WidgetFactory.hbox().setSpacing(0);
        logout.add(logoutIcon.getNode(), logoutButton.getNode());

        // Buttons block
        VBoxBuilder middleBlock = WidgetFactory.vbox().setAlignment(Pos.CENTER_LEFT);
        middleBlock.add(settings.getNode(), logout.getNode());


        // Create game button
        ButtonBuilder createGameButton = WidgetFactory.button(
            "Create a game",
            e -> createPrivateGame()
        ).setPrefWidth(215);
        // Join game button
        ButtonBuilder joinGameButton = WidgetFactory.button(
            "Join with code",
            e -> SceneManager.loadScene(SceneManager.SceneNames.JOIN_SCENE)
        ).setPrefWidth(215);
        HBoxBuilder buttons = WidgetFactory.hbox().setSpacing(0);
        buttons.addWithFlex(createGameButton.getNode(), joinGameButton.getNode());

        LineBuilder line = WidgetFactory.line();

        // Start game button
        ButtonBuilder startGameButton = WidgetFactory.button(
            "Start a game",
            e -> startPublicGame()
        );

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox();
        block.add(buttons.getNode(), line.getNode(), startGameButton.getNode());

        panel.addWithFlex(titleBox.getNode(), middleBlock.getNode(), block.getNode());

        // Add the panel to the root container
        _root.getChildren().addAll(panel.getNode());
    }

    @Override
    public void initListener() {
        Listener chooseListener = new Listener();
        chooseListener.on(MessageType.START_PUBLIC_GAME_SUCCESS, msg -> onStartPublicGameSuccess(msg));
        chooseListener.on(MessageType.START_PUBLIC_GAME_FAILED, msg -> onStartPublicGameFailed(msg));
        chooseListener.on(MessageType.CREATE_PRIVATE_GAME_SUCCESS, msg -> onCreatePrivateGameSuccess(msg));
        chooseListener.on(MessageType.CREATE_PRIVATE_GAME_FAILED, msg -> onCreatePrivateGameFailed(msg));
        Network.setListener(chooseListener);
    }

    private void logout() {
        User.logout();
        SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE);
    }

    private void startPublicGame() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.startPublicGame(User.getToken()));
    }

    private void createPrivateGame() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.createPrivateGame(User.getToken()));
    }

    private void onStartPublicGameSuccess(ProtocolMessage message) {
        try {
            String roomId = message.getArgs().get(0);
            User.setRoomId(roomId);
            SceneManager.loadScene(SceneManager.SceneNames.GAME_SCENE);
        } catch (Exception e) {
        }
    }

    private void onStartPublicGameFailed(ProtocolMessage message) {

    }

    private void onCreatePrivateGameSuccess(ProtocolMessage message) {
        SceneManager.loadScene(SceneManager.SceneNames.GAME_SCENE);
    }

    private void onCreatePrivateGameFailed(ProtocolMessage message) {

    }
}
