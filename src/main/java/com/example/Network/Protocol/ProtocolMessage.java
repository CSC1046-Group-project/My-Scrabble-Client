package com.example.Network.Protocol;

import java.util.Arrays;
import java.util.List;

// protocol message class
public class ProtocolMessage {

    private final MessageType _type;            // Protocol message type
    private final List<String> _args;           // List of args in the message

    // Create the protocol message
    public ProtocolMessage(MessageType type, String... args) {
        _type = type;
        _args = Arrays.asList(args);
    }

    // Get the message type
    public MessageType getType() {
        return _type;
    }

    // get the list of arguments of the message
    public List<String> getArgs() {
        return _args;
    }

    // Serialize the message to a string
    public String serialize() {
        String joinedArgs = String.join(" ", _args);
        return _type.name() + (joinedArgs.isEmpty() ? "" : " " + joinedArgs);
    }

    // Deserialize the message to a ProtocolMessage class
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
