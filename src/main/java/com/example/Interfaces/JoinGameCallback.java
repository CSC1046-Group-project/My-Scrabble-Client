package com.example.Interfaces;

// Join game callback
public interface JoinGameCallback {
    void onSuccess(String token, String password);
    void onFailure(String message);
}
