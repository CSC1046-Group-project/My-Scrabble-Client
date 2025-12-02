package com.example.Interfaces;

public interface IAuthenticationService {
    void login(String email, String password, LoginCallback callback);
    void register(String username, String email, String password, LoginCallback callback);
}
