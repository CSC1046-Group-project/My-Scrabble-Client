package com.example.Game;

import java.util.ArrayList;
import java.util.List;

import static com.example.Game.BoardCell.TypePower.DL;
import static com.example.Game.BoardCell.TypePower.DW;
import static com.example.Game.BoardCell.TypePower.MIDDLE;
import static com.example.Game.BoardCell.TypePower.NONE;
import static com.example.Game.BoardCell.TypePower.TL;
import static com.example.Game.BoardCell.TypePower.TW;

public class Board {

    private final List<BoardCell> _cells = new ArrayList<>();
    public static final int SIZE = 15;

    public Board() {

        // List of power types of board cells
        BoardCell.TypePower[][] layout = {
            { TW, NONE, NONE, DL, NONE, NONE, NONE, TW, NONE, NONE, NONE, DL, NONE, NONE, TW },
            { NONE, DW, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, DW, NONE },
            { NONE, NONE, DW, NONE, NONE, NONE, DL, NONE, DL, NONE, NONE, NONE, DW, NONE, NONE },
            { DL, NONE, NONE, DW, NONE, NONE, NONE, DL, NONE, NONE, NONE, DW, NONE, NONE, DL },
            { NONE, NONE, NONE, NONE, DW, NONE, NONE, NONE, NONE, NONE, DW, NONE, NONE, NONE, NONE },
            { NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE },
            { NONE, NONE, DL, NONE, NONE, NONE, DL, NONE, DL, NONE, NONE, NONE, DL, NONE, NONE },
            { TW, NONE, NONE, DL, NONE, NONE, NONE, MIDDLE, NONE, NONE, NONE, DL, NONE, NONE, TW },
            { NONE, NONE, DL, NONE, NONE, NONE, DL, NONE, DL, NONE, NONE, NONE, DL, NONE, NONE },
            { NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE },
            { NONE, NONE, NONE, NONE, DW, NONE, NONE, NONE, NONE, NONE, DW, NONE, NONE, NONE, NONE },
            { DL, NONE, NONE, DW, NONE, NONE, NONE, DL, NONE, NONE, NONE, DW, NONE, NONE, DL },
            { NONE, NONE, DW, NONE, NONE, NONE, DL, NONE, DL, NONE, NONE, NONE, DW, NONE, NONE },
            { NONE, DW, NONE, NONE, NONE, TL, NONE, NONE, NONE, TL, NONE, NONE, NONE, DW, NONE },
            { TW, NONE, NONE, DL, NONE, NONE, NONE, TW, NONE, NONE, NONE, DL, NONE, NONE, TW }
        };

        // Create the board cells using the list of power
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                _cells.add(new BoardCell(layout[row][col], row, col));
            }
        }
    }

    // Get the cell at the index row and col
    public BoardCell getCell(int row, int col) {
        return _cells.get(row * SIZE + col);
    }

    // Add a tile on the board at the index row and col
    public boolean addTile(int row, int col, Tile tile) {
        if (row >= SIZE || col >= SIZE || row < 0 || col < 0) {
            return false;
        }
        return _cells.get(row * SIZE + col).addTile(tile);
    }

    // Remove a tile from a cell
    public void removeTile(int row, int col) {
        if (row >= SIZE || col >= SIZE || row < 0 || col < 0) {
            return;
        }
        _cells.get(row * SIZE + col).removeTile();
    }
}
