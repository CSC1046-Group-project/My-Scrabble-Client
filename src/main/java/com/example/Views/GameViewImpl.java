package com.example.Views;

import com.example.Interfaces.GameView;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.application.Platform;

public class GameViewImpl implements GameView {

    private final VBoxBuilder _usersBox;
    private final ButtonBuilder _readyButton;

    public GameViewImpl(
        VBoxBuilder userBox,
        ButtonBuilder readyButton
    ) {
        _usersBox = userBox;
        _readyButton = readyButton;
    }

    @Override
    public void hideReadyButton() {
        Platform.runLater(() -> {
            _usersBox.remove(_readyButton.getNode());
        });
    }
}
