package com.example.Services;

import com.example.Interfaces.GameView;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolMessage;

import javafx.application.Platform;

public class GameEventHandler {

    private final GameView _view;
    private final Listener _gameListener = new Listener();

    public GameEventHandler(
        GameView view
    ) {
        _view = view;

        _gameListener.on(MessageType.TILEBAG, msg -> onTileBagUpdate(msg));
        _gameListener.on(MessageType.PLAYER_IS_READY, msg -> onPlayerIsReady(msg));
        _gameListener.on(MessageType.PLAYER_HAVE_PLAYED, msg -> onPlayerHavePlayed(msg));
        _gameListener.on(MessageType.GAME_START, msg -> onGameStart(msg));
        _gameListener.on(MessageType.PLAYER_TURN, msg -> onPlayerTurn(msg));
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
            String name = msg.getArgs().get(0);
            Platform.runLater(() -> _view.addPlayer(name));
        } catch (Exception e) {
        }
    }

    public void onPlayerHavePlayed(ProtocolMessage msg) {
         try {
        } catch (Exception e) {
        }
    }

    public void onGameStart(ProtocolMessage msg) {
         try {
        } catch (Exception e) {
        }
    }

    public void onPlayerTurn(ProtocolMessage msg) {
         try {
        } catch (Exception e) {
        }
    }
}
