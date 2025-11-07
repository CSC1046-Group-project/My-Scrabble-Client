package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextBuilder extends Widget{

    private final Text _text;

    public TextBuilder(String text) {
        _text = new Text(text);
        _text.setFill(Color.WHITE);
        _text.setFont(Font.font(16));
    }

    public TextBuilder setFont(double size) {
        _text.setFont(Font.font(size));
        return this;
    }

    public TextBuilder setFill(Paint paint) {
        _text.setFill(paint);
        return this;
    }

    @Override
    public Node getNode() {
        return _text;
    }
}
