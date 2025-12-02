package com.example.Interfaces;

public interface JoinGameCallback {
    void onSuccess(String token);
    void onFailure(String message);
}
