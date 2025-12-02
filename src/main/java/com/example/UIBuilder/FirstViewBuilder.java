package com.example.UIBuilder;

import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.SceneManager;

import javafx.scene.Node;

public class FirstViewBuilder {

    public Node build() {

        // Centered Panel
        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550);

        VBoxBuilder header = buildHeader();
        VBoxBuilder buttonBox = buildButtonBox();

        // Combine header and button box
        panel.addWithFlex(header.getNode(), buttonBox.getNode());
        return panel.getNode();
    }

    private VBoxBuilder buildHeader() {
        // Title
        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);
        // Line under title
        LineBuilder titleLine = WidgetFactory.line();
        // Group title + line
        VBoxBuilder titleBox = WidgetFactory.vbox();
        titleBox.add(title.getNode(), titleLine.getNode());
        return titleBox;
    }

    private VBoxBuilder buildButtonBox() {
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
        return buttonBox;
    }
}
