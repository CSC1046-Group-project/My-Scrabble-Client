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
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;
import com.example.SceneManager.SceneManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class LoginScene extends MyScene {

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

        IconButtonBuilder back = WidgetFactory.iconButton(
            "/assets/left-arrow.png",
            e -> SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE)
        );
        // Title
        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);
        // Create ampty button just to center the title on the middle
        IconButtonBuilder nothing = WidgetFactory.iconButton(
            "",
            e -> {}
        );
        HBoxBuilder header = WidgetFactory.hbox();
        header.addWithFlex(back.getNode(), title.getNode(), nothing.getNode());

        // Line under title
        LineBuilder titleLine = WidgetFactory.line();
        // Group title + line
        VBoxBuilder titleBox = WidgetFactory.vbox();
        titleBox.add(header.getNode(), titleLine.getNode());

        // Email text field
        TextFieldBuilder email = WidgetFactory.textField("Email");

        // Password text field
        TextFieldBuilder password = WidgetFactory.textField("Password");

        // Login button
        ButtonBuilder loginButton = WidgetFactory.button(
            "Login",
            e -> login(email.getText(), password.getText())
        );

        LineBuilder lineButtons = WidgetFactory.line();

        // Link to register
        TextBuilder link = WidgetFactory.text("Don't have an account?").setFont(14);
        ButtonBuilder linkButton = WidgetFactory.button("Register", e -> SceneManager.loadScene(SceneManager.SceneNames.REGISTER_SCENE))
            .setFont(14)
            .setPrefHeight(14)
            .setStyle("-fx-background-color: transparent;")
            .setTextFill(Color.web("0x3B8895"));
        HBoxBuilder linkBox = WidgetFactory.hbox().setSpacing(3);
        linkBox.add(link.getNode(), linkButton.getNode());

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox();
        block.add(email.getNode(), password.getNode(), lineButtons.getNode(), loginButton.getNode(), linkBox.getNode());

        panel.addWithFlex(titleBox.getNode(), block.getNode());

        // Add the panel to the root container
        _root.getChildren().addAll(panel.getNode());
    }

    @Override
    public void initListener() {
        Listener loginListener = new Listener();
        loginListener.on(MessageType.LOGIN_SUCCESS, msg -> onLoginSuccess(msg));
        loginListener.on(MessageType.LOGIN_FAILED, msg -> onLoginFailed(msg));
        Network.setListener(loginListener);
    }

    private void login(String email, String password) {
        Network.sendMessage(ProtocolFactory.login(email, password));
    }

    private void onLoginSuccess(ProtocolMessage message) {
        try {
            String token = message.getArgs().get(0);
            User.setToken(token);
            SceneManager.loadScene(SceneManager.SceneNames.CHOOSE_SCENE);
        } catch (Exception e) {
            System.err.println("Error: Can't get the user token.");
        }
    }

    private void onLoginFailed(ProtocolMessage message) {
        TextBuilder error = WidgetFactory.text("Don't have an account?").setFill(Color.RED);
        _root.getChildren().addAll(error.getNode());
        onDraw();
    }
}
