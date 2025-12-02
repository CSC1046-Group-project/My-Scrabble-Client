package com.example.Interfaces;

public interface LoginCallback {
    void onSuccess(String token);
    void onFailure(String message);
}
