package com.example.Game;

public abstract class User {

    private static String _token;

    public static void setToken(String token) {
        _token = token;
    }

    public static String getToken() {
        return _token;
    }

    public static void logout() {
        _token = null;
    }
}
