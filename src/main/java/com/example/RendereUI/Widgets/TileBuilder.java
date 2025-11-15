package com.example.RendereUI.Widgets;

import java.util.function.BiFunction;

import com.example.Game.Tile;
import com.example.RendereUI.Widget;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TileBuilder extends Widget {

    private final StackPane _tilePane;
    private BiFunction<TileBuilder, double[], Boolean> _onReleaseCallback;
    private final Tile _tile;

    public TileBuilder(Tile tile, int x, int y, boolean isDraggable) {

        _tile = tile;
        _tilePane = new StackPane();
        _tilePane.setPrefSize(50, 50);
        _tilePane.setStyle("-fx-background-color: #FFDA9E; -fx-background-radius: 10;");

        Text t = new Text(_tile.getLetter());
        t.setFont(Font.font(24));
        StackPane.setAlignment(t, Pos.CENTER);

        Text p = new Text(String.valueOf(_tile.getPoint()));
        p.setFont(Font.font(10));
        StackPane.setAlignment(p, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(p, new Insets(5));

        _tilePane.getChildren().addAll(t, p);

        _tilePane.setLayoutX(x);
        _tilePane.setLayoutY(y);

        if (isDraggable) enableDrag(_tilePane);
    }

    public void setOnRelease(BiFunction<TileBuilder, double[], Boolean> callback) {
        this._onReleaseCallback = callback;
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

            double mouseX = e.getScreenX();
            double mouseY = e.getScreenY();

            if (_onReleaseCallback != null) {
                if (!_onReleaseCallback.apply(this, new double[]{mouseX, mouseY})) {
                    node.setTranslateX(0);
                    node.setTranslateY(0);
                }
                else {
                    Pane parent = (Pane) node.getParent();
                    if (parent != null) parent.getChildren().remove(node);
                }
            }
        });
    }

    public TileBuilder setLayoutX(int x) {
        _tilePane.setLayoutX(x);
        return this;
    }

    public TileBuilder setLayoutY(int y) {
        _tilePane.setLayoutY(y);
        return this;
    }

    public Tile getTile() {
        return _tile;
    }

    @Override
    public Node getNode() {
        return _tilePane;
    }
}
