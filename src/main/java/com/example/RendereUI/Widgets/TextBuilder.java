package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextBuilder extends Widget{

    private final Text _text;

    public TextBuilder(String text) {
        _text = new Text(text);
        _text.setFill(Color.WHITE);
        _text.setFont(Font.font(16));
        _text.setTextAlignment(TextAlignment.LEFT);
    }

    public TextBuilder setFont(double size) {
        _text.setFont(Font.font(size));
        return this;
    }

    public TextBuilder setTextAlignment(TextAlignment value) {
        _text.setTextAlignment(value);
        return this;
    }

    public TextBuilder setFill(Paint paint) {
        _text.setFill(paint);
        return this;
    }

    public void setText(String text) {
        _text.setText(text);
    }

    @Override
    public Node getNode() {
        return _text;
    }
}
