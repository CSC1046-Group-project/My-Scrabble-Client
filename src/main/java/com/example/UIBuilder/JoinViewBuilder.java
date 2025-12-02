package com.example.UIBuilder;

import com.example.Controllers.JoinController;
import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.SceneManager;

import javafx.scene.Node;

public class JoinViewBuilder {

    private final INavigationService _navigationService;
    private final JoinController _controller;

    public JoinViewBuilder(
        INavigationService navigationService,
        JoinController controller
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
        VBoxBuilder block = buildBlock();


        panel.addWithFlex(header.getNode(), block.getNode());
        return panel.getNode();
    }


    private VBoxBuilder buildHeader() {

        IconButtonBuilder back = WidgetFactory.iconButton(
            "/assets/left-arrow.png",
            e -> _navigationService.navigateToChooseScene()
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

        return titleBox;
    }

    private VBoxBuilder buildBlock() {
        // Code text field
        TextFieldBuilder code = WidgetFactory.textField("Code");

        // Password text field
        TextFieldBuilder password = WidgetFactory.textField("Password");

        // Join button
        ButtonBuilder joinButton = WidgetFactory.button(
            "Join",
            e -> _controller.join(code.getText(), password.getText())
        );

        LineBuilder lineButtons = WidgetFactory.line();

        // Buttons block
        VBoxBuilder block = WidgetFactory.vbox();
        block.add(code.getNode(), password.getNode(), lineButtons.getNode(), joinButton.getNode());

        return block;
    }
}
