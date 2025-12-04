package com.example.Game;

import java.util.ArrayList;
import java.util.List;

import com.example.RendereUI.Widgets.BoardBuilder;

public class WordPlacement {

    private static final List<BoardCell> _cellsPlaced = new ArrayList<>();
    private static BoardBuilder _board;

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

        if (!isAlignedVertically())
            return null;

        BoardCell first = _cellsPlaced.get(0);
        String word = buildWord(first.getPos()[0], first.getPos()[1], false);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, false);
    }

    private static boolean isAlignedVertically() {
        int firstCol = _cellsPlaced.get(0).getPos()[1];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[1] == firstCol);
    }

    private static WordPlacement tryHorizontalPlacement() {
        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[1], b.getPos()[1]));

        if (!isAlignedHorizontally())
            return null;

        BoardCell first = _cellsPlaced.get(0);
        String word = buildWord(first.getPos()[0], first.getPos()[1], true);
        return new WordPlacement(first.getPos()[0], first.getPos()[1], word, true);
    }

    private static boolean isAlignedHorizontally() {
        int firstRow = _cellsPlaced.get(0).getPos()[0];
        return _cellsPlaced.stream().allMatch(cell -> cell.getPos()[0] == firstRow);
    }

    private static String buildWord(int x, int y, boolean isHorizontal) {
        String word = "";

        int row = x;
        int column = y;

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

        while (startRow < _board.getSize() &&
                startRow >= 0 &&
                startCol < _board.getSize() &&
                startCol >= 0 &&
                _board.getBoardCell(startRow, startCol).getTile() != null
            )
        {
            Tile tile = _board.getBoardCell(startRow, startCol).getTile();

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

        return word;
    }

    public static WordPlacement validateAndCreatePlacement(BoardBuilder board) {

        // for (int i = 0; i < 15; i++) {
        //     for (int j = 0; j < 15; j++) {
        //         BoardCell cell = board.getBoardCell(i, j);

        //         if (cell == null)
        //             continue;

        //         Tile tile = cell.getTile();
        //         if (tile == null)
        //             System.out.print(" .");
        //         else
        //             System.out.print(" " + tile.getLetter());
        //     }
        //     System.out.println();
        // }

        _board = board;


        if (_cellsPlaced == null || _cellsPlaced.isEmpty())
            return null;

        WordPlacement vertical = tryVerticalPlacement();
        if (vertical != null) {
            return vertical;
        }

        return tryHorizontalPlacement();
    }
}
