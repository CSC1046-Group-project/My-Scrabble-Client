package com.example.Interfaces;

public interface GameView {
    void showError(String message);
    void clearError();
    // void showMessage(String message);
    void hideReadyButton();
    void addPlayer(String name);
    void updateTileRack();
    void updateTurn(boolean isPlayerTurn);
    void updateTileBag(String tileBag);
}
