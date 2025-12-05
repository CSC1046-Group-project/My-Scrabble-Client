package com.example.Game;

// User class to handle a user status
public abstract class User {

    private static String _token;           // User token
    private static String _roomId;          // Current room id
    private static String _roomPassword;    // Current room password

    // Set the token
    public static void setToken(String token) {
        _token = token;
    }

    // get the token
    public static String getToken() {
        return _token;
    }

    // get the room id
    public static String getRoomId() {
        return _roomId;
    }

    // set the room id
    public static void setRoomId(String roomId) {
        _roomId = roomId;
    }

    // logout the user
    public static void logout() {
        _token = null;
        _roomId = null;
        _roomPassword = null;
    }

    // set the room password
    public static void setRoomPassword(String roomPassword) {
        _roomPassword = roomPassword;
    }

    // get the room password
    public static String getRoomPassword() {
        return _roomPassword;
    }
}
