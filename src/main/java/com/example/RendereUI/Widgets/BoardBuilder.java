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

    private static int SIZE = 15;
    private final Pane _boardPane;
    private final List<TileBuilder> _tiles = new ArrayList<>(java.util.Collections.nCopies(SIZE*SIZE, null));
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

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
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
        _tiles.set(row * SIZE + col, tile);
        _boardPane.getChildren().add(tile.getNode());
        return true;
    }

    public Integer[] getCellHover(double mouseX, double mouseY) {
        javafx.geometry.Point2D local = _boardPane.screenToLocal(mouseX, mouseY);
        int col = (int)(local.getX() / tileSize);
        int row = (int)(local.getY() / tileSize);
        return new Integer[]{row, col};
    }

    public void removeTile(BoardCell cell) {
        _boardPane.getChildren().remove(_tiles.get(cell.getPos()[0] * SIZE + cell.getPos()[1]).getNode());
        _board.removeTile(cell.getPos()[0], cell.getPos()[1]);
    }

    public BoardCell getBoardCell(int i, int j) {
        return _board.getCell(i, j);
    }

    @Override
    public Node getNode() {
        return _boardPane;
    }

    public int getSize() {
        return SIZE;
    }
}
