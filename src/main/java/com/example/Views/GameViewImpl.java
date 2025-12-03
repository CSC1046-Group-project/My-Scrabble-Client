package com.example.Views;

import com.example.Interfaces.GameView;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameViewImpl implements GameView {

    private final StackPane _root;
    private TextBuilder _errorText;

    private final VBoxBuilder _usersBox;
    private final ButtonBuilder _readyButton;
    private final VBoxBuilder _tilesleft;
    private final ButtonBuilder _submitButton;
    private final ButtonBuilder _skipButton;
    private final ButtonBuilder _swapButton;

    public GameViewImpl(
        StackPane root,
        VBoxBuilder userBox,
        ButtonBuilder readyButton,
        VBoxBuilder tilesleft,
        ButtonBuilder submitButton,
        ButtonBuilder skipButton,
        ButtonBuilder swapButton
    ) {
        _root = root;
        _usersBox = userBox;
        _readyButton = readyButton;
        _tilesleft = tilesleft;
        _submitButton = submitButton;
        _skipButton = skipButton;
        _swapButton = swapButton;
    }

    @Override
    public void hideReadyButton() {
        Platform.runLater(() -> {
            _usersBox.remove(_readyButton.getNode());
        });
    }

    @Override
    public void showError(String message) {
        clearError();
        _errorText = WidgetFactory.text(message).setFill(Color.RED);
        _root.getChildren().add(_errorText.getNode());
    }

    @Override
    public void clearError() {
        if (_errorText != null) {
            _root.getChildren().remove(_errorText.getNode());
            _errorText = null;
        }
    }

    @Override
    public void updateTileBag(String tileBag) {
        _tilesleft.removeAll();
        for (int i = 0; i < tileBag.length(); i += 17) {
            int end = Math.min(i + 17, tileBag.length());
            TextBuilder line = WidgetFactory.text(String.join("  ", tileBag.substring(i, end).split(""))).setFont(16);
            _tilesleft.add(line.getNode());
        }
    }

    @Override
    public void addPlayer(String name) {
        HBoxBuilder user = createUserBox(name, "/assets/example-profilepic.png", "0", "15:00")
            .setStyle("-fx-background-color: #282833");

        _usersBox.add(user.getNode());
    }

    @Override
    public void updateTurn(boolean isPlayerTurn) {
        _submitButton.getNode().setDisable(!isPlayerTurn);
        _skipButton.getNode().setDisable(!isPlayerTurn);
        _swapButton.getNode().setDisable(!isPlayerTurn);
    }

    private HBoxBuilder createUserBox(String username, String profilePicPath, String score, String timer) {
        HBoxBuilder allusersBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
        HBoxBuilder userBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
        HBoxBuilder statsBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;").setAlignment(Pos.CENTER_RIGHT);

        TextBuilder usernameText = WidgetFactory.text(username).setFont(16);

        IconButtonBuilder profilePic = WidgetFactory.iconButton(
            "/assets/example-profilepic.png"
        ).setFitWidth(30).setFitHeight(30);

        TextBuilder scoreText = WidgetFactory.text(String.valueOf(score)).setFont(30);
        TextBuilder timeText = WidgetFactory.text(timer).setFont(16);

        userBox.add(profilePic.getNode(), usernameText.getNode());
        statsBox.add(scoreText.getNode(), timeText.getNode());

        allusersBox.add(userBox.getNode(), statsBox.getNode());
        return allusersBox;
    }
}
