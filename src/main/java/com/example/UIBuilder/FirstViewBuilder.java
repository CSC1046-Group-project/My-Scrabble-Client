package com.example.UIBuilder;

import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.scene.Node;

public class FirstViewBuilder {
    private final INavigationService navigationService;

    public FirstViewBuilder(INavigationService navigationService) {
        this.navigationService = navigationService;
    }

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
            e -> navigationService.navigateToLoginScene()
        );
        ButtonBuilder registerButton = WidgetFactory.button(
            "Register",
            e -> navigationService.navigateToRegisterScene()
        );

        LineBuilder line = WidgetFactory.line();

        // Box to put Login and register button together
        VBoxBuilder buttonBox = WidgetFactory.vbox();
        buttonBox.add(loginButton.getNode(), line.getNode(), registerButton.getNode());
        return buttonBox;
    }
}
