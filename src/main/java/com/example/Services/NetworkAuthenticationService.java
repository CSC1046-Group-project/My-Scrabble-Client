package com.example.Services;

import com.example.Interfaces.AuthCallback;
import com.example.Interfaces.IAuthenticationService;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;

// Handle the send of auth messages to the server
public class NetworkAuthenticationService implements IAuthenticationService {

    // Handle login request
    @Override
    public void login(String email, String password, AuthCallback callback) {
        Listener loginListener = new Listener();

        // Handle login success from server
        loginListener.on(MessageType.LOGIN_SUCCESS, msg -> {
            try {
                String token = msg.getArgs().get(0);
                callback.onSuccess(token);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        // Handle login failed from server
        loginListener.on(MessageType.LOGIN_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Login failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        // Set up the listener and send the login message
        Network.setListener(loginListener);
        Network.sendMessage(ProtocolFactory.login(email, password));
    }

    // Handle register request
    @Override
    public void register(String username, String email, String password, AuthCallback callback) {
        Listener registerListener = new Listener();

        // Handle register success from server
        registerListener.on(MessageType.REGISTER_SUCCESS, msg -> {
            try {
                String token = msg.getArgs().get(0);
                callback.onSuccess(token);
            } catch (Exception e) {
                callback.onFailure("Invalid server response");
            }
        });

        // Handle register failed from server
        registerListener.on(MessageType.REGISTER_FAILED, msg -> {
            String errorMsg = msg.getArgs().isEmpty()
                ? "Register failed"
                : msg.getArgs().get(0);
            callback.onFailure(errorMsg);
        });

        // Set up the listener and send the register message
        Network.setListener(registerListener);
        Network.sendMessage(ProtocolFactory.register(username, email, password));
    }
}
