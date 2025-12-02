package com.example.Services.Login;

import com.example.Game.User;
import com.example.Interfaces.IUserSession;

public class UserSessionImpl implements IUserSession {
    @Override
    public void setToken(String token) {
        User.setToken(token);
    }
}
