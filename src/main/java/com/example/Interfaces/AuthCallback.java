package com.example.Interfaces;

// Callback for auth
public interface AuthCallback {
    void onSuccess(String token);
    void onFailure(String message);
}
