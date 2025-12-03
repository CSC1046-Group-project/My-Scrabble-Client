package com.example.Interfaces;

public interface GameView {
    void showError(String message);
    void clearError();
    // void showMessage(String message);
    void hideReadyButton();
    void addPlayer(String name);
    // void refreshTileRack();
    // void updateTurnControls(boolean isPlayerTurn);
    void updateTileBag(String tileBag);
}
