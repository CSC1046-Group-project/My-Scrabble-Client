package com.example.Interfaces;

public interface IUserSession {
    void setToken(String token);
    String getToken();
    void setIdRoom(String id);
    String getIdRoom();
    boolean isLog();
    boolean isInRoom();
    void logout();
    void setRoomPassword(String password);
    String getRoomPassword();
}
