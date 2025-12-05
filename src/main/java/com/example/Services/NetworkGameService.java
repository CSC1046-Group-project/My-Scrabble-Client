package com.example.Services;

import com.example.Interfaces.GameCallback;
import com.example.Interfaces.IGameService;
import com.example.Network.Network;
import com.example.Network.Protocol.ProtocolFactory;

// handle the send of game messages to the server
public class NetworkGameService implements IGameService{

    // Send ready message
    @Override
    public void ready(String token, String roomId, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.ready(token, roomId));
        callback.onSuccess();
    }

    // Send challenge message
    @Override
    public void challenge(String token, String roomId, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.challenge(token, roomId));
        callback.onSuccess();
    }

    // Send resign message
    @Override
    public void resign(String token, String roomId, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.resign(token, roomId));
        callback.onSuccess();
    }

    // Send skip message
    @Override
    public void skip(String token, String roomId, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.skip(token, roomId));
        callback.onSuccess();
    }

    // Send swap message
    @Override
    public void swap(String token, String roomId, String letters, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.swap(token, roomId, letters));
    }

    // Send submit message
    @Override
    public void submit(String token, String roomId, String word, String x, String y, String isHorizontal, GameCallback callback) {
        Network.sendMessage(ProtocolFactory.submit(token, roomId, word, x, y, isHorizontal));
    }
}
