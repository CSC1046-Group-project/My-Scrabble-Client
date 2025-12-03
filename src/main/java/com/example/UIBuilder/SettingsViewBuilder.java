package com.example.UIBuilder;

import com.example.Interfaces.INavigationService;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.SliderBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class SettingsViewBuilder {

    private final INavigationService _navigationService;

    public SettingsViewBuilder(
        INavigationService navigationService
    ) {
        _navigationService = navigationService;
    }

    public Node build() {

        // Centered Panel
        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(550)
            .setPrefWidth(550)
            .setAlignment(Pos.TOP_LEFT);

        HBoxBuilder header = buildHeader();

        VBoxBuilder usernameBlock = buildUsernameBlock();
        VBoxBuilder passwordBlock = buildPasswordBlock();

        // Line under change username / change password
        LineBuilder titleLine = WidgetFactory.line();

        HBoxBuilder volumeBlock = buildVolumeBlock();
        HBoxBuilder musicBlock = buildMusicBlock();

        panel.add(
            header.getNode(),
            usernameBlock.getNode(),
            passwordBlock.getNode(),
            titleLine.getNode(),
            volumeBlock.getNode(),
            musicBlock.getNode());
        return panel.getNode();
    }

    private HBoxBuilder buildHeader() {
        IconButtonBuilder back = WidgetFactory.iconButton(
            "/assets/left-arrow.png",
            e -> _navigationService.navigateToChooseScene()
            // TODO: for now there is no event but we will put a save name/password event later
        );

        IconButtonBuilder profilepic = WidgetFactory.iconButton(
                // TODO: we will add a user.getProfilePicPath() later, no event for this
            "/assets/example-profilepic.png"
        ).setFitWidth(30).setFitHeight(30);

        TextBuilder title = WidgetFactory.text("Settings").setFont(36);

        HBoxBuilder header = WidgetFactory.hbox();
        header.add(back.getNode(), title.getNode(), profilepic.getNode());
        return header;
    }

    private VBoxBuilder buildUsernameBlock() {
        IconButtonBuilder enterUsername = WidgetFactory.iconButton(
            "/assets/send.png"
            // TODO: add a save new username event
        ).setFitWidth(24).setFitHeight(24).setStyle("-fx-background-color: #3B8895; -fx-background-radius: 10;");

        TextBuilder changeUsernameText = WidgetFactory.text("Change Username").setFont(16);
        TextFieldBuilder changeUsernameInput = WidgetFactory.textField("Enter new username");

        HBoxBuilder usernameBox = WidgetFactory.hbox().setSpacing(30);
        usernameBox.add(changeUsernameInput.getNode(), enterUsername.getNode());
        HBox.setHgrow(changeUsernameInput.getNode(), javafx.scene.layout.Priority.ALWAYS);

        VBoxBuilder usernameBlock = WidgetFactory.vbox().setAlignment(Pos.CENTER_LEFT);
        usernameBlock.add(changeUsernameText.getNode(), usernameBox.getNode());

        return usernameBlock;
    }

    private VBoxBuilder buildPasswordBlock() {

        IconButtonBuilder enterPassword = WidgetFactory.iconButton(
            "/assets/send.png"
            // TODO: add a save new password event
        ).setFitWidth(24).setFitHeight(24).setStyle("-fx-background-color: #3B8895; -fx-background-radius: 10;");

        TextBuilder changePasswordText = WidgetFactory.text("Change Password").setFont(16);
        TextFieldBuilder changePasswordInput = WidgetFactory.textField("Enter new password");

        HBoxBuilder passwordBox = WidgetFactory.hbox().setSpacing(30);
        passwordBox.add(changePasswordInput.getNode(), enterPassword.getNode());
        HBox.setHgrow(changePasswordInput.getNode(), javafx.scene.layout.Priority.ALWAYS);

        VBoxBuilder passwordBlock = WidgetFactory.vbox().setAlignment(Pos.CENTER_LEFT);
        passwordBlock.add(changePasswordText.getNode(), passwordBox.getNode());

        return passwordBlock;
    }

    private HBoxBuilder buildVolumeBlock() {

        TextBuilder changeVolumeText = WidgetFactory.text("Set Volume").setFont(16);
        SliderBuilder volumeSlider = WidgetFactory.slider();
        HBox.setHgrow(volumeSlider.getNode(), javafx.scene.layout.Priority.ALWAYS);

        HBoxBuilder volumeBlock = WidgetFactory.hbox().setSpacing(10);
        volumeBlock.add(changeVolumeText.getNode(), volumeSlider.getNode());

        return volumeBlock;
    }

    private HBoxBuilder buildMusicBlock() {

        TextBuilder changeMusicText = WidgetFactory.text("Set   Music").setFont(16);
        SliderBuilder musicSlider = WidgetFactory.slider();
        HBox.setHgrow(musicSlider.getNode(), javafx.scene.layout.Priority.ALWAYS);

        HBoxBuilder musicBlock = WidgetFactory.hbox().setSpacing(10);
        musicBlock.add(changeMusicText.getNode(), musicSlider.getNode());

        return musicBlock;
    }
}
