package com.example.Interfaces;

// Game service interface
public interface IGameService {
    void ready(String token, String roomId, GameCallback callback);
    void challenge(String token, String roomId, GameCallback callback);
    void resign(String token, String roomId, GameCallback callback);
    void skip(String token, String roomId, GameCallback callback);
    void swap(String token, String roomId, String letters, GameCallback callback);
    void submit(String token, String roomId, String word, String x, String y, String isHorizontal, GameCallback callback);
}
