package com.example.Game;

import java.util.ArrayList;
import java.util.List;

import com.example.RendereUI.Widgets.BoardBuilder;

// Word placement class to handle user wrod placement
public class WordPlacement {

    private static final List<BoardCell> _cellsPlaced = new ArrayList<>();  // List of current cells placed on board
    private static BoardBuilder _board;                                     // board

    public int _x;                  // x position of the first character
    public int _y;                  // y position of the first character
    public String _word;            // word placed
    public boolean _isHorizontal;   // is horizontal or vertical

    // Constructor to create a WordPlacement
    public WordPlacement(int x, int y, String word, boolean isHorizontal) {
        _x = x;
        _y = y;
        _word = word;
        _isHorizontal = isHorizontal;
    }

    // Add a cell
    public static void add(BoardCell boardCell) {
        _cellsPlaced.add(boardCell);
    }

    // Clear cells placed
    public static void clear() {
        _cellsPlaced.clear();
    }

    // Get the cell at an index
    public static BoardCell getBoardCell(int idx) {
        return _cellsPlaced.get(idx);
    }

    // get the size of the _cellsPlaced list
    public static int getSize() {
        return _cellsPlaced.size();
    }

    // Try to see if the word can be place on the vertical in the board
    // Retunrn a WorldPlacement if okay
    private static WordPlacement tryVerticalPlacement() {
        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[0], b.getPos()[0]));

        if (!isAlignedVertically())
            return null;

        BoardCell first = _cellsPlaced.get(0);
        String word = buildWord(first.getPos()[0], first.getPos()[1], false);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, false);
    }

    // check if all tiles placed are aligned vertically
    private static boolean isAlignedVertically() {
        int firstCol = _cellsPlaced.get(0).getPos()[1];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[1] == firstCol);
    }

    // Try to see if the word can be place on the horizontal in the board
    // Retunrn a WorldPlacement if okay
    private static WordPlacement tryHorizontalPlacement() {
        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[1], b.getPos()[1]));

        if (!isAlignedHorizontally())
            return null;

        BoardCell first = _cellsPlaced.get(0);
        String word = buildWord(first.getPos()[0], first.getPos()[1], true);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, true);
    }

    // check if all tiles placed are aligned horizontally
    private static boolean isAlignedHorizontally() {
        int firstRow = _cellsPlaced.get(0).getPos()[0];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[0] == firstRow);
    }

    // Build the word string based on the tiles positions in the board
    private static String buildWord(int x, int y, boolean isHorizontal) {
        String word = "";
        int row = x;
        int column = y;

        // Check if there is previous tiles on the board that can be used to create the word
        while (true) {
            int tempRow = isHorizontal ? row : row - 1;
            int tempColumn = isHorizontal ? column - 1 : column;
            if (tempRow >= _board.getSize() ||
                tempRow < 0 ||
                tempColumn >= _board.getSize() ||
                tempColumn < 0 ||
                _board.getBoardCell(tempRow, tempColumn).getTile() == null
            )
                break;
            row = tempRow;
            column = tempColumn;
        }

        int startRow = row;
        int startCol = column;

        int i = 0;

        // Create the word based on all tiles that can be used on the same line until it reached an empty cell
        while (startRow < _board.getSize() &&
                startRow >= 0 &&
                startCol < _board.getSize() &&
                startCol >= 0 &&
                _board.getBoardCell(startRow, startCol).getTile() != null
            )
        {
            Tile tile = _board.getBoardCell(startRow, startCol).getTile();

            // Create the word using the letter and point of tiles followed by the '|' char
            if (i > 0)
                word += "|";
            word += tile.getLetter();
            word += String.valueOf(tile.getPoint());

            i++;
            if (isHorizontal)
                startCol++;
            else
                startRow++;
        }

        // return the word
        return word;
    }

    // Validate if a word can be placed on the board
    // Return a WordPLacement class if true
    public static WordPlacement validateAndCreatePlacement(BoardBuilder board) {

        _board = board;

        // If no tiles placed, return null
        if (_cellsPlaced == null || _cellsPlaced.isEmpty())
            return null;

        // Check vertically
        WordPlacement vertical = tryVerticalPlacement();
        if (vertical != null) {
            return vertical;
        }

        // Check Horizontally
        return tryHorizontalPlacement();
    }
}
