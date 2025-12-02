package com.example.UIBuilder;

import com.example.Controllers.ChooseController;
import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.geometry.Pos;
import javafx.scene.Node;

public class ChooseViewBuilder {

    private final INavigationService _navigationService;
    private final ChooseController _controller;

    public ChooseViewBuilder(
        INavigationService navigationService,
        ChooseController controller
    ) {
        _navigationService = navigationService;
        _controller = controller;
    }

    public Node build() {

        // Centered Panel
        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550);

        VBoxBuilder header = buildHeader();
        VBoxBuilder middleBlock = buildmiddleBlock();
        VBoxBuilder buttonBlock = buildButtonBlock();


        panel.addWithFlex(header.getNode(), middleBlock.getNode(), buttonBlock.getNode());
        return panel.getNode();
    }

    private VBoxBuilder buildHeader() {

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

        return titleBox;
    }

    private VBoxBuilder buildmiddleBlock() {

        // Create settings icon
        IconButtonBuilder settingsIcon = WidgetFactory.iconButton(
            "/assets/settings.png",
            e -> _navigationService.navigateToSettingsScene()
        ).setFitWidth(24).setFitHeight(24);
        // Create settings button
        ButtonBuilder settingsButton = WidgetFactory.button(
            "Settings",
            e -> _navigationService.navigateToSettingsScene()
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
            e -> _controller.handleLogout()
        ).setFitWidth(24).setFitHeight(24);
        // Create logout button
        ButtonBuilder logoutButton = WidgetFactory.button(
            "Disconnect",
            e -> _controller.handleLogout()
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

        return middleBlock;
    }

    private VBoxBuilder buildButtonBlock() {

        // Create game button
        ButtonBuilder createGameButton = WidgetFactory.button(
            "Create a game",
            e -> _controller.handleCreatePrivateGame()
        ).setPrefWidth(215);

        // Join game button
        ButtonBuilder joinGameButton = WidgetFactory.button(
            "Join with code",
            e -> _navigationService.navigateToJoinScene()
        ).setPrefWidth(215);
        HBoxBuilder buttons = WidgetFactory.hbox().setSpacing(0);
        buttons.addWithFlex(createGameButton.getNode(), joinGameButton.getNode());

        LineBuilder line = WidgetFactory.line();

        // Start game button
        ButtonBuilder startGameButton = WidgetFactory.button(
            "Start a game",
            e -> _controller.handleStartPublicGame()
        );

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox();
        block.add(buttons.getNode(), line.getNode(), startGameButton.getNode());
        return block;
    }
}
