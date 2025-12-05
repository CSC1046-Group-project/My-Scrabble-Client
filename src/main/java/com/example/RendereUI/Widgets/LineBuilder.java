package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.layout.Region;

// Line widget to display a line
public class LineBuilder extends Widget{

    private final Region _line;

    public LineBuilder() {
        _line = new Region();
        _line.setPrefHeight(1);
        _line.setStyle("-fx-background-color: #343442;");
    }

    @Override
    public Node getNode() {
        return _line;
    }
}
