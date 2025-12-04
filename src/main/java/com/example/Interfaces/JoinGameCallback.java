package com.example.Interfaces;

public interface JoinGameCallback {
    void onSuccess(String token, String password);
    void onFailure(String message);
}
