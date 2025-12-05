package com.example.Interfaces;

// Join game service interface
public interface IJoinGameService {
    void join(String token, String code, String password, JoinGameCallback callback);
    void startPrivate(String token, JoinGameCallback callback);
    void startPublic(String token, JoinGameCallback callback);
}
