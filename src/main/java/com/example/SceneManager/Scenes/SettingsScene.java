package com.example.SceneManager.Scenes;

import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.LineBuilder;
import com.example.RendereUI.Widgets.SliderBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TextFieldBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;
import com.example.SceneManager.SceneManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SettingsScene extends MyScene {

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
            .setPrefWidth(550)
            .setAlignment(Pos.TOP_LEFT);

        IconButtonBuilder back = WidgetFactory.iconButton(
            "/assets/left-arrow.png",
            e -> SceneManager.loadScene(SceneManager.SceneNames.CHOOSE_SCENE)
            // for now there is no event but we will put a save name/password event later
        );

        IconButtonBuilder enterUsername = WidgetFactory.iconButton(
            "/assets/left-arrow.png"
            // add a save new username event
        ).setFitWidth(24).setFitHeight(24);

        // change these with the blue arrow png
        IconButtonBuilder enterPassword = WidgetFactory.iconButton(
            "/assets/left-arrow.png"
            // add a save new password event
        ).setFitWidth(24).setFitHeight(24);

        IconButtonBuilder profilepic = WidgetFactory.iconButton(
                // we will add a user.getProfilePicPath() later, no event for this 
            "/assets/example-profilepic.png"
        ).setFitWidth(30).setFitHeight(30);

        TextBuilder title = WidgetFactory.text("Settings").setFont(36);

        HBoxBuilder header = WidgetFactory.hbox();
        header.add(back.getNode(), title.getNode(), profilepic.getNode());
        
        // Line under change username / change password
        LineBuilder titleLine = WidgetFactory.line();
        
        TextBuilder changeUsernameText = WidgetFactory.text("Change Username").setFont(16);
        TextFieldBuilder changeUsernameInput = WidgetFactory.textField("Enter new username");

        TextBuilder changePasswordText = WidgetFactory.text("Change Password").setFont(16);
        TextFieldBuilder changePasswordInput = WidgetFactory.textField("Enter new password");


        HBoxBuilder usernameBox = WidgetFactory.hbox().setSpacing(0);
        usernameBox.add(changeUsernameInput.getNode(), enterUsername.getNode());
        HBox.setHgrow(changeUsernameInput.getNode(), javafx.scene.layout.Priority.ALWAYS);    

        HBoxBuilder passwordBox = WidgetFactory.hbox().setSpacing(0);
        passwordBox.add(changePasswordInput.getNode(), enterPassword.getNode());
        HBox.setHgrow(changePasswordInput.getNode(), javafx.scene.layout.Priority.ALWAYS);


        TextBuilder changeVolumeText = WidgetFactory.text("Set Volume").setFont(16);       
        SliderBuilder volumeSlider = WidgetFactory.slider();
        HBox.setHgrow(volumeSlider.getNode(), javafx.scene.layout.Priority.ALWAYS);

        TextBuilder changeMusicText = WidgetFactory.text("Set   Music").setFont(16);       
        SliderBuilder musicSlider = WidgetFactory.slider();
        HBox.setHgrow(musicSlider.getNode(), javafx.scene.layout.Priority.ALWAYS);

        HBoxBuilder volumeBlock = WidgetFactory.hbox().setSpacing(10);
        volumeBlock.add(changeVolumeText.getNode(), volumeSlider.getNode());

        HBoxBuilder musicBlock = WidgetFactory.hbox().setSpacing(10);
        musicBlock.add(changeMusicText.getNode(), musicSlider.getNode());

        panel.add(header.getNode(), changeUsernameText.getNode(), usernameBox.getNode(), changePasswordText.getNode(), passwordBox.getNode(), titleLine.getNode(), volumeBlock.getNode(), musicBlock.getNode());

        _root.getChildren().addAll(panel.getNode());
    }

    @Override
    public void initListener() {

    }
}
