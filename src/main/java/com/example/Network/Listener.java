package com.example.Network;

import java.util.HashMap;
import java.util.function.Consumer;

import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolMessage;

public class Listener {

    private final HashMap<MessageType, Consumer<ProtocolMessage>> _map = new HashMap<>();

    public void listen(ProtocolMessage message) {
        Consumer<ProtocolMessage> handler = _map.get(message.getType());
        if (handler != null) {
            handler.accept(message);
        } else {
            onUnhandledMessage(message);
        }
    }

    public void on(MessageType type, Consumer<ProtocolMessage> handler) {
        _map.put(type, handler);
    }

    public void onUnhandledMessage(ProtocolMessage message) {
        System.out.println("Unhandled message type: " + message.getType());
    }
}
