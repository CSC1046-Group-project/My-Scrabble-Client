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

    public BoardCell(TypePower power) {
        _power = power;
        _tile = null;
    }

    public boolean addTile(Tile tile) {
        if (_tile != null)
            return false;
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
}
