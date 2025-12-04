package com.example.Services;

import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.JoinGameCallback;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;

public class NetworkJoinGameService implements IJoinGameService {

    @Override
    public void join(String token, String code, String password, JoinGameCallback callback) {
        Listener joinListener = new Listener();

        joinListener.on(MessageType.JOIN_PRIVATE_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                String roomPassword = msg.getArgs().get(1);
                callback.onSuccess(idRoom, roomPassword);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        joinListener.on(MessageType.JOIN_PRIVATE_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Join private game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        Network.setListener(joinListener);
        Network.sendMessage(ProtocolFactory.joinPrivateGame(token, code, password));
    }

    @Override
    public void startPrivate(String token, JoinGameCallback callback) {
        Listener startPrivateListener = new Listener();

        startPrivateListener.on(MessageType.CREATE_PRIVATE_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                String roomPassword = msg.getArgs().get(1);
                callback.onSuccess(idRoom, roomPassword);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        startPrivateListener.on(MessageType.CREATE_PRIVATE_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Create private game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        Network.setListener(startPrivateListener);
        Network.sendMessage(ProtocolFactory.createPrivateGame(token));
    }

    @Override
    public void startPublic(String token, JoinGameCallback callback) {
        Listener startPublicListener = new Listener();

        startPublicListener.on(MessageType.START_PUBLIC_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                callback.onSuccess(idRoom, "");
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        startPublicListener.on(MessageType.START_PUBLIC_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Start public game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        Network.setListener(startPublicListener);
        Network.sendMessage(ProtocolFactory.startPublicGame(token));
    }
}
