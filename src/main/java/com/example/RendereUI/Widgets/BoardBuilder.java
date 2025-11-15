package com.example.RendereUI.Widgets;

import java.util.ArrayList;
import java.util.List;

import com.example.Game.Board;
import com.example.Game.BoardCell;
import com.example.RendereUI.Widget;
import com.example.RendereUI.WidgetFactory;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BoardBuilder extends Widget {

    private final Pane _boardPane;
    private final List<TileBuilder> tiles = new ArrayList<>();
    private final Board _board;

    private final int rows;
    private final int cols;
    private final int tileSize;

    public BoardBuilder(int rows, int cols, int tileSize) {
        _board = new Board();

        this.rows = rows;
        this.cols = cols;
        this.tileSize = tileSize;

        _boardPane = new Pane();
        _boardPane.setPrefSize(cols * tileSize, rows * tileSize);
        _boardPane.setMaxSize(cols * tileSize, rows * tileSize);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                BoardCell cell = _board.getCell(i, j);
                BoardCellBuilder cellBuild = WidgetFactory.boardCell(i * 50, j * 50, cell.getPower());
                _boardPane.getChildren().add(cellBuild.getNode());
            }
        }
    }

    public boolean addTile(TileBuilder tile, int row, int col) {

        if (!_board.addTile(row, col, tile.getTile()))
            return false;

        tile.setLayoutX(col * tileSize);
        tile.setLayoutY(row * tileSize);
        tiles.add(tile);
        _boardPane.getChildren().add(tile.getNode());
        return true;
    }

    public Integer[] getCellHover(double mouseX, double mouseY) {
        javafx.geometry.Point2D local = _boardPane.screenToLocal(mouseX, mouseY);
        int col = (int)(local.getX() / tileSize);
        int row = (int)(local.getY() / tileSize);
        return new Integer[]{row, col};
    }

    public void removeTile(TileBuilder tile) {
        tiles.remove(tile);
        _boardPane.getChildren().remove(tile.getNode());
    }

    public BoardCell getBoardCell(int i, int j) {
        return _board.getCell(i, j);
    }

    @Override
    public Node getNode() {
        return _boardPane;
    }
}
