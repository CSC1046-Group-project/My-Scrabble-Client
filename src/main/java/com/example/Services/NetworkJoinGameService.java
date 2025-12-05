package com.example.Services;

import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.JoinGameCallback;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;

// handle the send of join game messages to the server
public class NetworkJoinGameService implements IJoinGameService {

    // Handle join private game request
    @Override
    public void join(String token, String code, String password, JoinGameCallback callback) {
        Listener joinListener = new Listener();

        // Handle join private game success from server
        joinListener.on(MessageType.JOIN_PRIVATE_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                String roomPassword = msg.getArgs().get(1);
                callback.onSuccess(idRoom, roomPassword);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        // Handle join private game failed from server
        joinListener.on(MessageType.JOIN_PRIVATE_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Join private game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        // Set up the listener and send the join private game message
        Network.setListener(joinListener);
        Network.sendMessage(ProtocolFactory.joinPrivateGame(token, code, password));
    }

    // Handle start private game request
    @Override
    public void startPrivate(String token, JoinGameCallback callback) {
        Listener startPrivateListener = new Listener();

        // Handle start private game success from server
        startPrivateListener.on(MessageType.CREATE_PRIVATE_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                String roomPassword = msg.getArgs().get(1);
                callback.onSuccess(idRoom, roomPassword);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        // Handle start private game failed from server
        startPrivateListener.on(MessageType.CREATE_PRIVATE_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Create private game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        // Set up the listener and send the start private game message
        Network.setListener(startPrivateListener);
        Network.sendMessage(ProtocolFactory.createPrivateGame(token));
    }

    // Handle start public game request
    @Override
    public void startPublic(String token, JoinGameCallback callback) {
        Listener startPublicListener = new Listener();

        // Handle start public game success from server
        startPublicListener.on(MessageType.START_PUBLIC_GAME_SUCCESS, msg -> {
            try {
                String idRoom = msg.getArgs().get(0);
                callback.onSuccess(idRoom, "");
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        // Handle start public game failed from server
        startPublicListener.on(MessageType.START_PUBLIC_GAME_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Start public game failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        // Set up the listener and send the start public game message
        Network.setListener(startPublicListener);
        Network.sendMessage(ProtocolFactory.startPublicGame(token));
    }
}
