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

public class JoinScene extends MyScene {

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
            e -> SceneManager.loadScene(SceneManager.SceneNames.CHOOSE_SCENE)
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


        // Code text field
        TextFieldBuilder code = WidgetFactory.textField("Code");

        // Password text field
        TextFieldBuilder password = WidgetFactory.textField("Password");

        // Join button
        ButtonBuilder joinButton = WidgetFactory.button(
            "Join",
            e -> join(code.getText(), password.getText())
        );

        LineBuilder lineButtons = WidgetFactory.line();

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox();
        block.add(code.getNode(), password.getNode(), lineButtons.getNode(), joinButton.getNode());

        panel.addWithFlex(titleBox.getNode(), block.getNode());

        // Add the panel to the root container
        _root.getChildren().addAll(panel.getNode());
    }

    @Override
    public void initListener() {
        Listener joinListener = new Listener();
        joinListener.on(MessageType.JOIN_PRIVATE_GAME_SUCCESS, msg -> onJoinPrivateGameSuccess(msg));
        joinListener.on(MessageType.JOIN_PRIVATE_GAME_FAILED, msg -> onJoinPrivateGameFailed(msg));
        Network.setListener(joinListener);
    }

    private void join(String code, String password) {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.joinPrivateGame(User.getToken(), code, password));
    }

    private void onJoinPrivateGameSuccess(ProtocolMessage message) {
        SceneManager.loadScene(SceneManager.SceneNames.GAME_SCENE);
    }

    private void onJoinPrivateGameFailed(ProtocolMessage message) {

    }
}
