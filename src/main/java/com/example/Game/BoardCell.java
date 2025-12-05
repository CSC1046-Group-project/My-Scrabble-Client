package com.example.Game;

public class BoardCell {

    // Power type of the cell
    public enum TypePower {
        TW,
        DW,
        TL,
        DL,
        NONE,
        MIDDLE
    }

    private Tile _tile;                 // Tile in the cell
    private final TypePower _power;     // Power of the cell
    private final int _x;               // Position x in the board
    private final int _y;               // Position y in the board

    // Constructor of the cell
    public BoardCell(TypePower power, int x, int y) {
        _power = power;
        _tile = null;
        _x = x;
        _y = y;
    }

    // Add a tile
    public boolean addTile(Tile tile) {
        if (_tile != null) {
            return false;
        }
        _tile = tile;
        return true;
    }

    // Remove the tile
    public void removeTile() {
        _tile = null;
    }

    // Get the tile
    public Tile getTile() {
        return _tile;
    }

    // Get the power
    public TypePower getPower() {
        return _power;
    }

    // Get the position
    public int[] getPos() {
        return new int[]{_x, _y};
    }
}
