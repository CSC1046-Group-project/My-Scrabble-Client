package com.example.Interfaces;

public interface IAuthenticationService {
    void login(String email, String password, AuthCallback callback);
    void register(String username, String email, String password, AuthCallback callback);
}
