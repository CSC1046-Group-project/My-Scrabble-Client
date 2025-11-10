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
}
