package com.example.Interfaces;

import com.example.RendereUI.Widgets.BoardBuilder;

public interface GameView {
    void showError(String message);
    void clearError();
    void hideReadyButton();
    void addPlayer(String token, String name);
    void removePlayer(String token);
    void updateTileRack();
    void updateTurn(boolean isPlayerTurn);
    void updateTileBag(String tileBag);
    void placeWord(String name, String[] parts, int x, int y, boolean isHorizontal);
    void blockChallengeButton();
    BoardBuilder getBoard();
}
