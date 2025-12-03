package com.example.Interfaces;

public interface GameView {
    void showError(String message);
    void clearError();
    // void showMessage(String message);
    void hideReadyButton();
    void addPlayer(String token, String name);
    void updateTileRack();
    void updateTurn(boolean isPlayerTurn);
    void updateTileBag(String tileBag);
    void placeWord(String name, String[] parts, int x, int y, boolean isHorizontal);
}
