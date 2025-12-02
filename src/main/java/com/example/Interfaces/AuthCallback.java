package com.example.Interfaces;

public interface AuthCallback {
    void onSuccess(String token);
    void onFailure(String message);
}
