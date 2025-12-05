package com.example.Services;

import com.example.Game.User;
import com.example.Interfaces.IUserSession;

// User session implementation
public class UserSessionImpl implements IUserSession {

    // Set the user token
    @Override
    public void setToken(String token) {
        User.setToken(token);
    }

    // Get the user token
    @Override
    public String getToken() {
        return User.getToken();
    }

    // Set the id room
    @Override
    public void setIdRoom(String id) {
        User.setRoomId(id);
    }

    // Get the id room
    @Override
    public String getIdRoom() {
        return User.getRoomId();
    }

    // Check if the user is logged
    @Override
    public boolean isLog() {
        return (User.getToken() != null && !User.getToken().equals(""));
    }

    // Set the user is in a room
    @Override
    public boolean isInRoom() {
        return (User.getRoomId() != null && !User.getRoomId().equals(""));
    }

    // Logout the user
    @Override
    public void logout() {
        User.logout();
    }

    // Set the room password
    @Override
    public void setRoomPassword(String roomPassword) {
        User.setRoomPassword(roomPassword);
    }

    // Get the room password
    @Override
    public String getRoomPassword() {
        return User.getRoomPassword();
    }
}
