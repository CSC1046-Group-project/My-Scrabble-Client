package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

// Vbox widget to handle vertical widgets
public class VBoxBuilder extends Widget{

    private final VBox _vbox;

    public VBoxBuilder() {
        _vbox = new VBox();
        _vbox.setSpacing(24);
        _vbox.setAlignment(Pos.CENTER);
        _vbox.setPadding(new Insets(20));
        _vbox.setStyle("-fx-background-color: #22222B; -fx-background-radius: 10;");
    }

    public VBoxBuilder setSpacing(double value) {
        _vbox.setSpacing(value);
        return this;
    }

    public VBoxBuilder setAlignment(Pos value) {
        _vbox.setAlignment(value);
        return this;
    }

    public VBoxBuilder setMaxWidth(double value) {
        _vbox.setMaxWidth(value);
        return this;
    }

    public VBoxBuilder setMaxHeight(double value) {
        _vbox.setMaxHeight(value);
        return this;
    }

    public VBoxBuilder setPrefWidth(double value) {
        _vbox.setPrefWidth(value);
        return this;
    }

    public VBoxBuilder setPrefHeigth(double value) {
        _vbox.setPrefHeight(value);
        return this;
    }

    public VBoxBuilder setPadding(double topRightBottomLeft) {
        _vbox.setPadding(new Insets(topRightBottomLeft));
        return this;
    }

    public VBoxBuilder setStyle(String style) {
        _vbox.setStyle(style);
        return this;
    }

    public void add(Node... elements) {
        _vbox.getChildren().addAll(elements);
    }

    public void remove(Node node) {
        _vbox.getChildren().remove(node);
    }

    public void removeAll() {
        _vbox.getChildren().clear();
    }

    public VBoxBuilder addWithFlex(Node... elements) {
        if (elements == null || elements.length == 0) {
            return this;
        }
        _vbox.getChildren().clear();
        for (int i = 0; i < elements.length; i++) {
            _vbox.getChildren().add(elements[i]);
            if (i < elements.length - 1) {
                Region spacer = new Region();
                VBox.setVgrow(spacer, Priority.ALWAYS);
                _vbox.getChildren().add(spacer);
            }
        }
        return this;
    }



    @Override
    public Node getNode() {
        return _vbox;
    }
}
