package com.example.Services;

import com.example.Game.User;
import com.example.Interfaces.IUserSession;

public class UserSessionImpl implements IUserSession {

    @Override
    public void setToken(String token) {
        User.setToken(token);
    }

    @Override
    public String getToken() {
        return User.getToken();
    }

    @Override
    public void setIdRoom(String id) {
        User.setRoomId(id);
    }

    @Override
    public String getIdRoom() {
        return User.getRoomId();
    }

    @Override
    public boolean isLog() {
        return (User.getToken() != null && !User.getToken().equals(""));
    }

    @Override
    public boolean isInRoom() {
        return (User.getRoomId() != null && !User.getRoomId().equals(""));
    }

    @Override
    public void logout() {
        User.logout();
    }
}
