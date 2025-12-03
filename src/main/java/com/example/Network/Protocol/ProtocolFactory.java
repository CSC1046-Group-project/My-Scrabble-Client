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

    public static ProtocolMessage ready(String token, String roomId) {
        return new ProtocolMessage(MessageType.READY, token, roomId);
    }

    public static ProtocolMessage submit(String token, String roomId, String word, String x, String y, String horizontal) {
        return new ProtocolMessage(MessageType.SUBMIT, token, roomId, word, x, y, horizontal);
    }

    public static ProtocolMessage challenge(String token, String roomId) {
        return new ProtocolMessage(MessageType.CHALLENGE, token, roomId);
    }

    public static ProtocolMessage skip(String token, String roomId) {
        return new ProtocolMessage(MessageType.SKIP, token, roomId);
    }

    public static ProtocolMessage resign(String token, String roomId) {
        return new ProtocolMessage(MessageType.RESIGN, token, roomId);
    }

    public static ProtocolMessage swap(String token, String roomId, String letters) {
        return new ProtocolMessage(MessageType.SWAP, token, roomId, letters);
    }
}
