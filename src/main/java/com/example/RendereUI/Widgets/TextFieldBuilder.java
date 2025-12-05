package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

// Text field widget to display an input text field
public class TextFieldBuilder extends Widget {

    private final TextField _textField;

    public TextFieldBuilder(String text) {
        _textField = new TextField();
        _textField.setPromptText(text);
        _textField.setMaxWidth(Double.MAX_VALUE);
        _textField.setPrefHeight(57);
        _textField.setFont(Font.font(16));
        _textField.setStyle("-fx-background-color: #282833;" +
            "-fx-background-radius: 10;" +
            "-fx-text-fill: white;");
        _textField.focusedProperty().addListener((obs, oldVal, newVal) -> {
        if (newVal) {
            _textField.setStyle("-fx-background-color: #282833;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: white;" +
                "-fx-border-color: #343442;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 10;");
        } else {
            _textField.setStyle("-fx-background-color: #282833;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: white;" +
                "-fx-border-color: transparent;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 10;");
        }
        });
    }

    public String getText() {
        return _textField.getText();
    }

    @Override
    public Node getNode() {
        return _textField;
    }
}
