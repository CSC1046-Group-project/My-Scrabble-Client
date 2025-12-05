package com.example.Network;

import java.util.HashMap;
import java.util.function.Consumer;

import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolMessage;

// Class to link server messages to callback functions
public class Listener {

    private final HashMap<MessageType, Consumer<ProtocolMessage>> _map = new HashMap<>();   // List of callback functions link to a message type

    // Call the callback of a message based on its type
    public void listen(ProtocolMessage message) {
        Consumer<ProtocolMessage> handler = _map.get(message.getType());
        if (handler != null) {
            handler.accept(message);
        } else {
            onUnhandledMessage(message);
        }
    }

    // Add a callback link to a message type
    public void on(MessageType type, Consumer<ProtocolMessage> handler) {
        _map.put(type, handler);
    }

    // Log when unhandled message
    public void onUnhandledMessage(ProtocolMessage message) {
        System.out.println("Unhandled message type: " + message.getType());
    }
}
