package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class VBoxBuilder extends Widget{

    private final VBox _vbox;

    public VBoxBuilder() {
        _vbox = new VBox();
        _vbox.setSpacing(24);
        _vbox.setAlignment(Pos.CENTER);
        _vbox.setPadding(new Insets(20));
        _vbox.setMaxWidth(550);
        _vbox.setMaxHeight(550);
        _vbox.setPrefWidth(550);
        _vbox.setStyle("-fx-background-color: #22222B; -fx-background-radius: 10;");
    }

    public void add(Node... elements) {
        _vbox.getChildren().addAll(elements);
    }

    public VBoxBuilder addWithFlex(Node top, Node bottom) {
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        _vbox.getChildren().addAll(top, spacer, bottom);
        return this;
    }

    @Override
    public Node getNode() {
        return _vbox;
    }
}
