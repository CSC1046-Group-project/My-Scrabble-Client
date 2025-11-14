package com.example.RendereUI.Widgets;

import com.example.RendereUI.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TileBuilder extends Widget {

    private final StackPane _tile;

    public TileBuilder(String letter, int value, int x, int y) {

        _tile = new StackPane();
        _tile.setPrefSize(50, 50);
        _tile.setStyle("-fx-background-color: #FFDA9E; -fx-background-radius: 10;");

        Text t = new Text(letter);
        t.setFont(Font.font(24));
        StackPane.setAlignment(t, Pos.CENTER);

        Text p = new Text(String.valueOf(value));
        p.setFont(Font.font(10));
        StackPane.setAlignment(p, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(p, new Insets(5));

        _tile.getChildren().addAll(t, p);

        _tile.setLayoutX(x);
        _tile.setLayoutY(y);

        enableDrag(_tile);
    }

    private void enableDrag(Node node) {
        final double[] offset = new double[2];

        node.setOnMousePressed(e -> {
            offset[0] = e.getSceneX() - node.getTranslateX();
            offset[1] = e.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(e -> {
            node.setTranslateX(e.getSceneX() - offset[0]);
            node.setTranslateY(e.getSceneY() - offset[1]);
        });

        node.setOnMouseReleased(e -> {
            node.setTranslateX(0);
            node.setTranslateY(0);
        });
    }

    public TileBuilder setLayoutX(int x) {
        _tile.setLayoutX(x);
        return this;
    }

    public TileBuilder setLayoutY(int y) {
        _tile.setLayoutY(y);
        return this;
    }

    @Override
    public Node getNode() {
        return _tile;
    }
}
