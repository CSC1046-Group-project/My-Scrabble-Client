package com.example.Game;

import java.util.ArrayList;
import java.util.List;

public class WordPlacement {

    private static final List<BoardCell> _cellsPlaced = new ArrayList<>();

    public int _x;
    public int _y;
    public String _word;
    public boolean _isHorizontal;

    public WordPlacement(int x, int y, String word, boolean isHorizontal) {
        _x = x;
        _y = y;
        _word = word;
        _isHorizontal = isHorizontal;
    }

    public static void add(BoardCell boardCell) {
        _cellsPlaced.add(boardCell);
    }

    public static void clear() {
        _cellsPlaced.clear();
    }

    public static BoardCell getBoardCell(int idx) {
        return _cellsPlaced.get(idx);
    }

    public static int getSize() {
        return _cellsPlaced.size();
    }

    private static WordPlacement tryVerticalPlacement() {
        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[0], b.getPos()[0]));

        if (!isConsecutiveVertical() || !isAlignedVertically())
            return null;

        String word = buildWord();
        BoardCell first = _cellsPlaced.get(0);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, false);
    }

    private static boolean isConsecutiveVertical() {
        for (int i = 1; i < _cellsPlaced.size(); i++) {
            if (_cellsPlaced.get(i).getPos()[0] != _cellsPlaced.get(i-1).getPos()[0] + 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAlignedVertically() {
        int firstCol = _cellsPlaced.get(0).getPos()[1];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[1] == firstCol);
    }

    private static WordPlacement tryHorizontalPlacement() {
        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[1], b.getPos()[1]));

        if (!isConsecutiveHorizontal() || !isAlignedHorizontally())
            return null;

        String word = buildWord();
        BoardCell first = _cellsPlaced.get(0);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, true);
    }

    private static boolean isConsecutiveHorizontal() {
        for (int i = 1; i < _cellsPlaced.size(); i++) {
            if (_cellsPlaced.get(i).getPos()[1] != _cellsPlaced.get(i-1).getPos()[1] + 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAlignedHorizontally() {
        int firstRow = _cellsPlaced.get(0).getPos()[0];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[0] == firstRow);
    }

    private static String buildWord() {
        String word = "";
        for (int i = 0; i < _cellsPlaced.size(); i++) {
            Tile tile = _cellsPlaced.get(i).getTile();
            if (tile == null)
                continue;

            if (i > 0)
                word += "|";
            word += _cellsPlaced.get(i).getTile().getLetter();
            word += String.valueOf(tile.getPoint());
        }
        return word;
    }

    public static WordPlacement validateAndCreatePlacement() {

        if (_cellsPlaced == null || _cellsPlaced.isEmpty())
            return null;

        WordPlacement vertical = tryVerticalPlacement();
        if (vertical != null) {
            return vertical;
        }

        return tryHorizontalPlacement();
    }
}
