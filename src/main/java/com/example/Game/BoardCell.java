package com.example.Game;

public class BoardCell {

    public enum TypePower {
        TW,
        DW,
        TL,
        DL,
        NONE,
        MIDDLE
    }

    private Tile _tile;
    private final TypePower _power;
    private final int _x;
    private final int _y;

    public BoardCell(TypePower power, int x, int y) {
        _power = power;
        _tile = null;
        _x = x;
        _y = y;
    }

    public boolean addTile(Tile tile) {
        if (_tile != null) {
            return false;
        }
        _tile = tile;
        return true;
    }

    public void removeTile() {
        _tile = null;
    }

    public Tile getTile() {
        return _tile;
    }

    public TypePower getPower() {
        return _power;
    }

    public int[] getPos() {
        return new int[]{_x, _y};
    }
}
