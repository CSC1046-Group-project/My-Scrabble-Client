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
    private final ImageView _iconView;

    public IconButtonBuilder(String iconPath, EventHandler<ActionEvent> event) {
        _icoBtn = new Button();
        System.out.println(getClass().getResourceAsStream(iconPath));
        Image icon = new Image(getClass().getResourceAsStream(iconPath));
        _iconView = new ImageView(icon);
        _iconView.setFitWidth(15);
        _iconView.setFitHeight(24);
        _icoBtn.setPrefHeight(48);
        _icoBtn.setPrefWidth(48);
        _icoBtn.setGraphic(_iconView);
        _icoBtn.setStyle("-fx-background-color: transparent;");

        // only set if there is an event
        if (event != null){
            _icoBtn.setOnAction(event);
        }
    }

    public IconButtonBuilder(String iconPath) {
        this(iconPath, null);
    }

    public IconButtonBuilder setPrefHeight(double value) {
        _icoBtn.setPrefHeight(value);
        return this;
    }

    public IconButtonBuilder setPrefWidth(double value) {
        _icoBtn.setPrefWidth(value);
        return this;
    }

    public IconButtonBuilder setFitHeight(double value) {
        _iconView.setFitHeight(value);
        _icoBtn.setGraphic(_iconView);
        return this;
    }

    public IconButtonBuilder setFitWidth(double value) {
        _iconView.setFitWidth(value);
        _icoBtn.setGraphic(_iconView);
        return this;
    }

    @Override
    public Node getNode() {
        return _icoBtn;
    }
}
