package com.example.UIBuilder;

import com.example.Controllers.RegisterController;
import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public class RegisterViewBuilder {
    private final INavigationService navigationService;
    private final RegisterController controller;

    public RegisterViewBuilder(INavigationService navigationService, RegisterController controller) {
        this.navigationService = navigationService;
        this.controller = controller;
    }

    public Node build() {
        VBoxBuilder panel = WidgetFactory.vbox()
            .setPadding(20)
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550);

        // Header
        VBoxBuilder titleBox = buildHeader();

        // Form
        VBoxBuilder formBlock = buildForm();

        panel.addWithFlex(titleBox.getNode(), formBlock.getNode());
        return panel.getNode();
    }

    private VBoxBuilder buildHeader() {
        IconButtonBuilder back = WidgetFactory.iconButton(
            "/assets/left-arrow.png",
            e -> navigationService.navigateToFirstScene()
        );
        // Title
        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);
        // Create empty button just to center the title on the middle
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
        return titleBox;
    }

    private VBoxBuilder buildForm() {
        // Username text field
        TextFieldBuilder username = WidgetFactory.textField("Username");

        // Email text field
        TextFieldBuilder email = WidgetFactory.textField("Email");

        // Password text field
        TextFieldBuilder password = WidgetFactory.textField("Password");

        // Register button
        ButtonBuilder registerButton = WidgetFactory.button(
            "Register",
            e -> controller.handleRegister(username.getText(), email.getText(), password.getText())
        );

        LineBuilder lineButtons = WidgetFactory.line();

        HBoxBuilder linkBox = buildLoginLink();

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox().setSpacing(9);
        block.add(
            username.getNode(),
            email.getNode(),
            password.getNode(),
            lineButtons.getNode(),
            registerButton.getNode(),
            linkBox.getNode());
        return block;
    }

    private HBoxBuilder buildLoginLink() {

        // Link to register
        TextBuilder link = WidgetFactory.text("Already have an account?").setFont(14);
        ButtonBuilder linkButton = WidgetFactory.button("Login", e -> navigationService.navigateToLoginScene())
            .setFont(14)
            .setPrefHeight(14)
            .setStyle("-fx-background-color: transparent;")
            .setTextFill(Color.web("0x3B8895"));
        HBoxBuilder linkBox = WidgetFactory.hbox().setSpacing(3);
        linkBox.add(link.getNode(), linkButton.getNode());
        return linkBox;
    }
}
