package com.example.Game;

public abstract class User {

    private static String _token;
    private static String _roomId;

    public static void setToken(String token) {
        _token = token;
    }

    public static String getToken() {
        return _token;
    }

    public static String getRoomId() {
        return _roomId;
    }

    public static void setRoomId(String roomId) {
        _roomId = roomId;
    }

    public static void logout() {
        _token = null;
        _roomId = null;
    }
}
