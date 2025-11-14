package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HBoxBuilder extends Widget {

    private final HBox _hBox;

    public HBoxBuilder() {
        _hBox = new HBox();
        _hBox.setSpacing(24);
        _hBox.setAlignment(Pos.CENTER_LEFT);
        _hBox.setMaxWidth(550);
        _hBox.setMaxHeight(550);
        _hBox.setPrefWidth(550);
        _hBox.setStyle("-fx-background-color: #22222B; -fx-background-radius: 10;");
    }

    public HBoxBuilder setSpacing(double value) {
        _hBox.setSpacing(value);
        return this;
    }

    public HBoxBuilder setAlignment(Pos value) {
        _hBox.setAlignment(value);
        return this;
    }

    public HBoxBuilder setStyle(String style) {
        _hBox.setStyle(style);
        return this;
    }

    public HBoxBuilder setMaxWidth(double value) {
        _hBox.setMaxWidth(value);
        return this;
    }

    public void add(Node... elements) {
        _hBox.getChildren().addAll(elements);
    }

    public HBoxBuilder addWithFlex(Node... elements) {
        if (elements == null || elements.length == 0) {
            return this;
        }
        _hBox.getChildren().clear();
        for (int i = 0; i < elements.length; i++) {
            _hBox.getChildren().add(elements[i]);
            if (i < elements.length - 1) {
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                _hBox.getChildren().add(spacer);
            }
        }
        return this;
    }

    @Override
    public Node getNode() {
        return _hBox;
    }
}
