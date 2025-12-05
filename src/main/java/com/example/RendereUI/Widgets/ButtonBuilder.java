package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

// Button widget to display a button
public class ButtonBuilder extends Widget{

    private final Button _button;

    public ButtonBuilder(String text, EventHandler<ActionEvent> event) {
        _button = new Button(text);
        _button.setMaxWidth(Double.MAX_VALUE);
        _button.setPrefHeight(57);
        _button.setTextFill(Color.WHITE);
        _button.setFont(Font.font(24));
        _button.setStyle("-fx-background-color: #3B8895; -fx-background-radius: 10;");
        _button.setOnAction(event);
    }

    public ButtonBuilder setAlignment(Pos value) {
        _button.setAlignment(value);
        return this;
    }

    public ButtonBuilder setPrefHeight(double value) {
        _button.setPrefHeight(value);
        return this;
    }

    public ButtonBuilder setPrefWidth(double value) {
        _button.setPrefWidth(value);
        return this;
    }

    public ButtonBuilder setMaxHeight(double value) {
        _button.setMaxHeight(value);
        return this;
    }

    public ButtonBuilder setMaxWidth(double value) {
        _button.setMaxWidth(value);
        return this;
    }

    public ButtonBuilder setTextFill(Paint value) {
        _button.setTextFill(value);
        return this;
    }

    public ButtonBuilder setFont(double size) {
        _button.setFont(Font.font(size));
        return this;
    }

    public ButtonBuilder setStyle(String style) {
        _button.setStyle(style);
        return this;
    }

    @Override
    public Node getNode() {
        return _button;
    }
}
