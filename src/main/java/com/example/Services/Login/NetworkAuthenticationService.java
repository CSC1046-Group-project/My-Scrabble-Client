package com.example.Services.Login;

import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.LoginCallback;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;

public class NetworkAuthenticationService implements IAuthenticationService {
    @Override
    public void login(String email, String password, LoginCallback callback) {
        Listener loginListener = new Listener();

        loginListener.on(MessageType.LOGIN_SUCCESS, msg -> {
            try {
                String token = msg.getArgs().get(0);
                callback.onSuccess(token);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        loginListener.on(MessageType.LOGIN_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Login failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        Network.setListener(loginListener);
        Network.sendMessage(ProtocolFactory.login(email, password));
    }

    @Override
    public void register(String username, String email, String password, LoginCallback callback) {
        Listener registerListener = new Listener();

        registerListener.on(MessageType.REGISTER_SUCCESS, msg -> {
            try {
                String token = msg.getArgs().get(0);
                callback.onSuccess(token);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        registerListener.on(MessageType.REGISTER_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Register failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        Network.setListener(registerListener);
        Network.sendMessage(ProtocolFactory.register(username, email, password));
    }
}
