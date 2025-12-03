package com.example.Views;

import java.util.List;

import com.example.Game.BoardCell;
import com.example.Game.Tile;
import com.example.Game.TileRack;
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
    private final List<BoardCell> _cellsPlaced;

    private final VBoxBuilder _usersBox;
    private final ButtonBuilder _readyButton;
    private final VBoxBuilder _tilesleft;
    private final ButtonBuilder _submitButton;
    private final ButtonBuilder _skipButton;
    private final ButtonBuilder _swapButton;
    private final Pane _rack;

    public GameViewImpl(
        StackPane root,
        BoardBuilder board,
        TileRack tileRack,
        List<BoardCell> cellsPlaced,
        VBoxBuilder userBox,
        ButtonBuilder readyButton,
        VBoxBuilder tilesleft,
        ButtonBuilder submitButton,
        ButtonBuilder skipButton,
        ButtonBuilder swapButton,
        Pane rack
    ) {
        _root = root;
        _board = board;
        _tileRack = tileRack;
        _cellsPlaced = cellsPlaced;
        _usersBox = userBox;
        _readyButton = readyButton;
        _tilesleft = tilesleft;
        _submitButton = submitButton;
        _skipButton = skipButton;
        _swapButton = swapButton;
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

    @Override
    public void updateTileRack() {
        displayTileRack();
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

    private void displayTileRack() {
        _rack.getChildren().clear();

        // Shuffle button (left)
        IconButtonBuilder shuffleIcon = WidgetFactory.iconButton(
            "/assets/shuffle.png",
            e -> {}
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

        _tileRack.removeTile(tile.getRackIndex());

        if (_board.addTile(newTile, mousePosOnBoard[0], mousePosOnBoard[1])) {
            _cellsPlaced.add(_board.getBoardCell(mousePosOnBoard[0], mousePosOnBoard[1]));
            return true;
        } else {
            return false;
        }
    }

    private void tilesGoBackToRack() {

        for (int i = 0; i < _cellsPlaced.size(); i++) {
            _tileRack.addTile(_cellsPlaced.get(i).getTile());
            _board.removeTile(_cellsPlaced.get(i));
        }
        _cellsPlaced.clear();
        displayTileRack();
    }
}
