package com.example.SceneManager.Scenes;

import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
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

public class FirstScene extends MyScene {

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

        // Title
        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);
        // Line under title
        LineBuilder titleLine = WidgetFactory.line();
        // Group title + line
        VBoxBuilder titleBox = WidgetFactory.vbox();
        titleBox.add(title.getNode(), titleLine.getNode());

        // Login and Register Buttons
        ButtonBuilder loginButton = WidgetFactory.button(
            "Login",
            e -> SceneManager.loadScene(SceneManager.SceneNames.LOGIN_SCENE)
        );
        ButtonBuilder registerButton = WidgetFactory.button(
            "Register",
            e -> SceneManager.loadScene(SceneManager.SceneNames.REGISTER_SCENE)
        );

        LineBuilder line = WidgetFactory.line();

        // Box to put Login and register button together
        VBoxBuilder buttonBox = WidgetFactory.vbox();
        buttonBox.add(loginButton.getNode(), line.getNode(), registerButton.getNode());

        // Combine Title box and button box
        panel.addWithFlex(titleBox.getNode(), buttonBox.getNode());

        // Add the panel to the root container
        _root.getChildren().addAll(panel.getNode());
    }
}
