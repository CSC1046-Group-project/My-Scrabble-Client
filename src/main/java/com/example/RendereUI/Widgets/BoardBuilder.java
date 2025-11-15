package com.example.RendereUI.Widgets;

import java.util.ArrayList;
import java.util.List;

import com.example.RendereUI.Widget;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BoardBuilder extends Widget {

    private final Pane _board;
    private final List<TileBuilder> tiles = new ArrayList<>();

    private final int rows;
    private final int cols;
    private final int tileSize;

    public BoardBuilder(int rows, int cols, int tileSize) {
        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        _board = new Pane();
        _board.setPrefSize(cols * tileSize, rows * tileSize);
        _board.setMaxSize(cols * tileSize, rows * tileSize);
        _board.setStyle("-fx-background-color: #eee; -fx-border-color: #333; -fx-border-width: 2;");
    }

    public void addTile(TileBuilder tile, int row, int col) {
        tile.setLayoutX(col * tileSize);
        tile.setLayoutY(row * tileSize);
        tiles.add(tile);
        _board.getChildren().add(tile.getNode());
    }

    public Integer[] getCellHover(double mouseX, double mouseY) {
        javafx.geometry.Point2D local = _board.screenToLocal(mouseX, mouseY);
        int col = (int)(local.getX() / tileSize);
        int row = (int)(local.getY() / tileSize);

        System.out.println(col);
        System.out.println(row);

        return new Integer[]{row, col};
    }

    public void removeTile(TileBuilder tile) {
        tiles.remove(tile);
        _board.getChildren().remove(tile.getNode());
    }

    @Override
    public Node getNode() {
        return _board;
    }
}
