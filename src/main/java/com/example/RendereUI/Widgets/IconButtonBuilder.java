package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconButtonBuilder extends Widget{

    private final Button _icoBtn;

    public IconButtonBuilder(String iconPath, EventHandler<ActionEvent> event) {
        _icoBtn = new Button();
        System.out.println(getClass().getResourceAsStream(iconPath));
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(15);
        iconView.setFitHeight(24);
        _icoBtn.setPrefHeight(48);
        _icoBtn.setPrefWidth(48);
        _icoBtn.setGraphic(iconView);
        _icoBtn.setStyle("-fx-background-color: transparent;");
        _icoBtn.setOnAction(event);
    }

    @Override
    public Node getNode() {
        return _icoBtn;
    }
}
