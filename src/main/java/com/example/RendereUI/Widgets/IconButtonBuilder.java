package com.example.RendereUI.Widgets;

import java.io.InputStream;

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
        InputStream iconStream = getClass().getResourceAsStream(iconPath);

        if (iconStream != null) {
            Image icon = new Image(iconStream);
            _iconView = new ImageView(icon);
            _iconView.setFitWidth(15);
            _iconView.setFitHeight(24);
            _icoBtn.setGraphic(_iconView);
        } else {
            System.err.println("WARNING: Icon not found at path: " + iconPath);
            _iconView = null; // Optional
        }

        _icoBtn.setPrefHeight(48);
        _icoBtn.setPrefWidth(48);
        _icoBtn.setStyle("-fx-background-color: transparent;");

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
        if (_iconView == null)
            return this;
        _iconView.setFitHeight(value);
        _icoBtn.setGraphic(_iconView);
        return this;
    }

    public IconButtonBuilder setFitWidth(double value) {
        if (_iconView == null)
            return this;
        _iconView.setFitWidth(value);
        _icoBtn.setGraphic(_iconView);
        return this;
    }

    public IconButtonBuilder setStyle(String style) {
        _icoBtn.setStyle(style);
        return this;
    }

    @Override
    public Node getNode() {
        return _icoBtn;
    }
}
