package com.example.Network.Protocol;

public class ProtocolFactory {

    public static ProtocolMessage login(String email, String password) {
        return new ProtocolMessage(MessageType.LOGIN, email, password);
    }

    public static ProtocolMessage register(String username, String email, String password) {
        return new ProtocolMessage(MessageType.REGISTER, username, email, password);
    }

    public static ProtocolMessage createPrivateGame(String token) {
        return new ProtocolMessage(MessageType.CREATE_PRIVATE_GAME, token);
    }

    public static ProtocolMessage joinPrivateGame(String token, String code, String password) {
        return new ProtocolMessage(MessageType.JOIN_PRIVATE_GAME, token, code, password);
    }

    public static ProtocolMessage startPublicGame(String token) {
        return new ProtocolMessage(MessageType.START_PUBLIC_GAME, token);
    }

    public static ProtocolMessage ready(String token) {
        return new ProtocolMessage(MessageType.READY, token);
    }

    public static ProtocolMessage submit(String token, String word, String x, String y, String horizontal) {
        return new ProtocolMessage(MessageType.READY, token, word, x, y, horizontal);
    }

    public static ProtocolMessage challenge(String token) {
        return new ProtocolMessage(MessageType.READY, token);
    }

    public static ProtocolMessage skip(String token) {
        return new ProtocolMessage(MessageType.READY, token);
    }

    public static ProtocolMessage resign(String token) {
        return new ProtocolMessage(MessageType.READY, token);
    }

    public static ProtocolMessage swap(String token, String letters) {
        return new ProtocolMessage(MessageType.READY, token, letters);
    }
}
