package com.example.UIBuilder;

import com.example.Controllers.LoginController;
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

public class LoginViewBuilder {
    private final INavigationService navigationService;
    private final LoginController controller;

    public LoginViewBuilder(INavigationService navigationService, LoginController controller) {
        this.navigationService = navigationService;
        this.controller = controller;
    }

    public Node build() {
        VBoxBuilder panel = WidgetFactory.vbox()
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

        TextBuilder title = WidgetFactory.text("My Scrabble").setFont(36);

        IconButtonBuilder spacer = WidgetFactory.iconButton("", e -> {});

        HBoxBuilder header = WidgetFactory.hbox();
        header.addWithFlex(back.getNode(), title.getNode(), spacer.getNode());

        LineBuilder titleLine = WidgetFactory.line();

        VBoxBuilder titleBox = WidgetFactory.vbox();
        titleBox.add(header.getNode(), titleLine.getNode());

        return titleBox;
    }

    private VBoxBuilder buildForm() {
        TextFieldBuilder email = WidgetFactory.textField("Email");
        TextFieldBuilder password = WidgetFactory.textField("Password");

        ButtonBuilder loginButton = WidgetFactory.button(
            "Login",
            e -> controller.handleLogin(email.getText(), password.getText())
        );

        LineBuilder lineButtons = WidgetFactory.line();

        HBoxBuilder linkBox = buildRegisterLink();

        VBoxBuilder block = WidgetFactory.vbox();
        block.add(
            email.getNode(),
            password.getNode(),
            lineButtons.getNode(),
            loginButton.getNode(),
            linkBox.getNode()
        );

        return block;
    }

    private HBoxBuilder buildRegisterLink() {
        TextBuilder link = WidgetFactory.text("Don't have an account?").setFont(14);

        ButtonBuilder linkButton = WidgetFactory.button(
            "Register",
            e -> navigationService.navigateToRegisterScene()
        )
            .setFont(14)
            .setPrefHeight(14)
            .setStyle("-fx-background-color: transparent;")
            .setTextFill(Color.web("0x3B8895"));

        HBoxBuilder linkBox = WidgetFactory.hbox().setSpacing(3);
        linkBox.add(link.getNode(), linkButton.getNode());

        return linkBox;
    }
}
