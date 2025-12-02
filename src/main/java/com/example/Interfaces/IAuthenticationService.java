package com.example.Interfaces;

public interface IAuthenticationService {
    void login(String email, String password, LoginCallback callback);
}
