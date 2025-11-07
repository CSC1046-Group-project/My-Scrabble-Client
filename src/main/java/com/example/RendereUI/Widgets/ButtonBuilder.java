package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    @Override
    public Node getNode() {
        return _button;
    }
}
