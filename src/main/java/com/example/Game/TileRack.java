package com.example.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileRack {

    private List<Tile> _tiles = new ArrayList<>(java.util.Collections.nCopies(7, null));

    public void resetRack() {
        _tiles.clear();
        _tiles = new ArrayList<>(java.util.Collections.nCopies(7, null));
    }

    public int addTile(Tile tile) {
        for (int i = 0; i < _tiles.size(); i++) {
            if (_tiles.get(i) == null) {
                _tiles.set(i, tile);
                return i;
            }
        }
        return -1;
    }

    public boolean removeTile(int idx) {
        if (idx >= _tiles.size() || idx < 0)
            return false;
        if (_tiles.get(idx) == null)
            return false;
        _tiles.set(idx, null);
        return true;
    }

    public void shuffleOrder() {
        List<Tile> nonNullTiles = new ArrayList<>();
        for (Tile t : _tiles) {
            if (t != null)
                nonNullTiles.add(t);
        }
        Collections.shuffle(nonNullTiles);

        int index = 0;
        for (int i = 0; i < _tiles.size(); i++) {
            if (_tiles.get(i) != null) {
                _tiles.set(i, nonNullTiles.get(index));
                index++;
            }
        }
    }

    public List<Tile> getTiles() {
        return _tiles;
    }
}
