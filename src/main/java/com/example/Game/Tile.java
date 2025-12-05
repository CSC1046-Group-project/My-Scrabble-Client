package com.example.Game;

// Class to handle tiles
public class Tile {

    private String _letter;     // Letter of the tile
    private int _point;         // Point given by the tile

    // Get the letter on the tile
    public String getLetter() {
        return _letter;
    }

    // Get the points of the tile
    public int getPoint() {
        return _point;
    }

    // Set the letter of the tile
    public void setLetter(String letter) {
        _letter = letter;
    }

    // Set the points of the tile
    public void setPoint(int point) {
        _point = point;
    }
}
