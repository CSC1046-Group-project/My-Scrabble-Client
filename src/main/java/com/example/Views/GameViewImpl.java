package com.example.Views;

import com.example.Game.Game;
import com.example.Game.Tile;
import com.example.Game.TileRack;
import com.example.Game.WordPlacement;
import com.example.Interfaces.GameView;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.BoardBuilder;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TileBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameViewImpl implements GameView {

    private final StackPane _root;
    private TextBuilder _errorText;

    private final BoardBuilder _board;
    private final TileRack _tileRack;

    private final VBoxBuilder _usersBox;
    private final ButtonBuilder _readyButton;
    private final VBoxBuilder _tilesleft;
    private final ButtonBuilder _submitButton;
    private final ButtonBuilder _skipButton;
    private final ButtonBuilder _swapButton;
    private final ButtonBuilder _challengeButton;
    private final Pane _rack;

    public GameViewImpl(
        StackPane root,
        BoardBuilder board,
        TileRack tileRack,
        VBoxBuilder userBox,
        ButtonBuilder readyButton,
        VBoxBuilder tilesleft,
        ButtonBuilder submitButton,
        ButtonBuilder skipButton,
        ButtonBuilder swapButton,
        ButtonBuilder challengeButton,
        Pane rack
    ) {
        _root = root;
        _board = board;
        _tileRack = tileRack;
        _usersBox = userBox;
        _readyButton = readyButton;
        _tilesleft = tilesleft;
        _submitButton = submitButton;
        _skipButton = skipButton;
        _swapButton = swapButton;
        _challengeButton = challengeButton;
        _rack = rack;
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
    public void addPlayer(String token, String name) {
        HBoxBuilder user = createUserBox(token, name, "/assets/example-profilepic.png", "0", "15:00")
            .setStyle("-fx-background-color: #282833");

        _usersBox.add(user.getNode());
    }

    @Override
    public void removePlayer(String token) {
        HBoxBuilder box = Game.getPlayerUserBox(token);
        if (box != null) {
            _usersBox.remove(box.getNode());
        }
        Game.removePlayer(token);
    }

    @Override
    public void updateTurn(boolean isPlayerTurn) {
        _submitButton.getNode().setDisable(!isPlayerTurn);
        _skipButton.getNode().setDisable(!isPlayerTurn);
        _swapButton.getNode().setDisable(!isPlayerTurn);
        _challengeButton.getNode().setDisable(!isPlayerTurn);
    }

    @Override
    public void updateTileRack() {
        displayTileRack();
    }

    @Override
    public void placeWord(String name, String[] parts, int x, int y, boolean isHorizontal) {

        tilesGoBackToRack();

        int idx = 0;
        for (String p : parts) {
            String letter = String.valueOf(p.charAt(0));
            int value = Integer.parseInt(p.substring(1));

            Tile tile = new Tile();
            tile.setLetter(letter);
            tile.setPoint(value);
            TileBuilder tileBuilder= WidgetFactory.tile(tile, (isHorizontal) ? x : x+idx, (isHorizontal) ? y+idx : y, false, -1);
            _board.addTile(tileBuilder, (isHorizontal) ? x : x+idx, (isHorizontal) ? y+idx : y);
            idx++;
        }
    }

    @Override
    public void blockChallengeButton() {
        _challengeButton.getNode().setDisable(true);
    }

    private HBoxBuilder createUserBox(String token, String username, String profilePicPath, String score, String timer) {
        HBoxBuilder allusersBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
        HBoxBuilder userBox = WidgetFactory.hbox().setStyle("-fx-background-color: transparent;");
        HBoxBuilder statsBox = WidgetFactory.hbox().setStyle("-fx-background-color: transparent;").setAlignment(Pos.CENTER_RIGHT);

        TextBuilder usernameText = WidgetFactory.text(username).setFont(16);

        IconButtonBuilder profilePic = WidgetFactory.iconButton(
            profilePicPath
        ).setFitWidth(30).setFitHeight(30);

        TextBuilder scoreText = WidgetFactory.text(String.valueOf(score)).setFont(30);
        TextBuilder timeText = WidgetFactory.text(timer).setFont(16);

        userBox.add(profilePic.getNode(), usernameText.getNode());
        statsBox.add(scoreText.getNode(), timeText.getNode());

        allusersBox.add(userBox.getNode(), statsBox.getNode());

        Game.addPlayer(token, username, scoreText, timeText, allusersBox);
        return allusersBox;
    }

    private void displayTileRack() {
        _rack.getChildren().clear();

        // Shuffle button (left)
        IconButtonBuilder shuffleIcon = WidgetFactory.iconButton(
            "/assets/shuffle.png",
            e -> shuffleOrder()
        ).setFitWidth(32).setFitHeight(32)
         .setStyle("-fx-background-color: #282833");

        shuffleIcon.getNode().setLayoutX(13);
        shuffleIcon.getNode().setLayoutY(16);

        // Rotate icon (right)
        IconButtonBuilder rotateIcon = WidgetFactory.iconButton(
            "/assets/rotate.png",
            e -> tilesGoBackToRack()
        ).setFitWidth(32).setFitHeight(32)
        .setStyle("-fx-background-color: #282833");

        rotateIcon.getNode().setLayoutX(480);
        rotateIcon.getNode().setLayoutY(16);

        _rack.getChildren().addAll(
            shuffleIcon.getNode(),
            rotateIcon.getNode()
        );

        int x = 82;
        for (int i = 0; i < _tileRack.getTiles().size(); i++) {
            Tile t = _tileRack.getTiles().get(i);
            if (t == null) {
                x += 56;
                continue;
            }
            TileBuilder tile = WidgetFactory.tile(t, x, 16, true, i);
            tile.setOnRelease((a,b) -> releaseTile(a, b));
            _rack.getChildren().add(tile.getNode());
            x += 56;
        }
    }

    private boolean releaseTile(TileBuilder tile, double[] pos) {
        Integer[] mousePosOnBoard = _board.getCellHover(pos[0], pos[1]);
        TileBuilder newTile = WidgetFactory.tile(tile.getTile(), 0, 0, false, tile.getRackIndex());

        if (_board.addTile(newTile, mousePosOnBoard[0], mousePosOnBoard[1])) {
            _tileRack.removeTile(tile.getRackIndex());
            WordPlacement.add(_board.getBoardCell(mousePosOnBoard[0], mousePosOnBoard[1]));
            return true;
        } else {
            return false;
        }
    }

    private void tilesGoBackToRack() {

        for (int i = 0; i < WordPlacement.getSize(); i++) {
            _tileRack.addTile(WordPlacement.getBoardCell(i).getTile());
            _board.removeTile(WordPlacement.getBoardCell(i));
        }
        WordPlacement.clear();
        displayTileRack();
    }

    private void shuffleOrder() {
        _tileRack.shuffleOrder();
        displayTileRack();
    }
}
