package com.example.Network.Protocol;

import java.util.Arrays;
import java.util.List;

public class ProtocolMessage {
    private final MessageType _type;
    private final List<String> _args;

    public ProtocolMessage(MessageType type, String... args) {
        _type = type;
        _args = Arrays.asList(args);
    }

    public MessageType getType() {
        return _type;
    }

    public List<String> getArgs() {
        return _args;
    }

    public String serialize() {
        String joinedArgs = String.join(" ", _args);
        return _type.name() + (joinedArgs.isEmpty() ? "" : " " + joinedArgs);
    }

    public static ProtocolMessage parse(String message) throws IllegalArgumentException {
        String[] parts = message.split(" ");
        if (parts.length == 0)
            throw new IllegalArgumentException("Error Protocol: Empty message.");

        try {
            MessageType type = MessageType.valueOf(parts[0]);
            String [] args = Arrays.copyOfRange(parts, 1, parts.length);
            return new ProtocolMessage(type, args);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error Protocol: Unknown message.");
        }
    }
}
