package com.example.Services;

import com.example.Game.Game;
import com.example.Game.Tile;
import com.example.Game.TileRack;
import com.example.Interfaces.GameView;
import com.example.Interfaces.IUserSession;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolMessage;

import javafx.application.Platform;

public class GameEventHandler {

    private final GameView _view;
    private final IUserSession _userSession;
    private final Listener _gameListener = new Listener();
    private final TileRack _tileRack;

    public GameEventHandler(
        GameView view,
        IUserSession userSession,
        TileRack tileRack
    ) {
        _view = view;
        _userSession = userSession;
        _tileRack = tileRack;

        _gameListener.on(MessageType.TILEBAG, msg -> onTileBagUpdate(msg));
        _gameListener.on(MessageType.PLAYER_IS_READY, msg -> onPlayerIsReady(msg));
        _gameListener.on(MessageType.PLAYER_HAVE_PLAYED, msg -> onPlayerHavePlayed(msg));
        _gameListener.on(MessageType.GAME_START, msg -> onGameStart(msg));
        _gameListener.on(MessageType.PLAYER_TURN, msg -> onPlayerTurn(msg));
        _gameListener.on(MessageType.PLAYER_SCORE, msg -> onPlayerScore(msg));
    }

    public void run() {
        Network.setListener(_gameListener);
    }

    public void onTileBagUpdate(ProtocolMessage msg) {
        try {
            String tileBag = msg.getArgs().get(0);
            Platform.runLater(() -> _view.updateTileBag(tileBag));
        } catch (Exception e) {
        }
    }

    public void onPlayerIsReady(ProtocolMessage msg) {
         try {
            String token = msg.getArgs().get(0);
            String name = msg.getArgs().get(1);

            Platform.runLater(() -> _view.addPlayer(token, name));
        } catch (Exception e) {
        }
    }

    public void onPlayerHavePlayed(ProtocolMessage msg) {
         try {
            String name = msg.getArgs().get(0);
            String word = msg.getArgs().get(1);
            String[] parts = word.split("\\|");
            int x = Integer.parseInt(msg.getArgs().get(2));
            int y = Integer.parseInt(msg.getArgs().get(3));
            boolean isHorizontal = Boolean.parseBoolean(msg.getArgs().get(4));

            Platform.runLater(() -> _view.placeWord(name, parts, x, y, isHorizontal));

        } catch (Exception e) {
        }
    }

    public void onGameStart(ProtocolMessage msg) {
         try {
            String tiles = msg.getArgs().get(0);
            String[] tilesParts = tiles.split("\\|");

            for (String t : tilesParts) {
                String letter = String.valueOf(t.charAt(0));
                int value = Integer.parseInt(t.substring(1));

                Tile tile = new Tile();
                tile.setLetter(letter);
                tile.setPoint(value);

                _tileRack.addTile(tile);
            }

            Platform.runLater(() -> _view.updateTileRack());
        } catch (Exception e) {
        }
    }

    public void onPlayerTurn(ProtocolMessage msg) {
        try {
            String player = msg.getArgs().get(0);
            Platform.runLater(() -> {
                _view.updateTurn(_userSession.getToken().equals(player));
                Game.setTurn(player);
            });
        } catch (Exception e) {
        }
    }

    public void onPlayerScore(ProtocolMessage msg){
        try {
            String token = msg.getArgs().get(0);
            String score = msg.getArgs().get(1);
            Game.setScore(token, score);
        } catch (Exception e) {
        }
    }
}
