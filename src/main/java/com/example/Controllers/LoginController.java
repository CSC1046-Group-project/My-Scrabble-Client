package com.example.Controllers;

import com.example.Interfaces.AuthCallback;
import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.Interfaces.View;

public class LoginController {
    private final IAuthenticationService authService;
    private final INavigationService navigationService;
    private final IUserSession userSession;
    private final View view;

    public LoginController(
        IAuthenticationService authService,
        INavigationService navigationService,
        IUserSession userSession,
        View view
    ) {
        this.authService = authService;
        this.navigationService = navigationService;
        this.userSession = userSession;
        this.view = view;
    }

    public void handleLogin(String email, String password) {
        if (!validateInput(email, password)) {
            view.showError("Email and password are required");
            return;
        }

        authService.login(email, password, new AuthCallback() {
            @Override
            public void onSuccess(String token) {
                userSession.setToken(token);
                navigationService.navigateToChooseScene();
            }

            @Override
            public void onFailure(String message) {
                view.showError(message);
            }
        });
    }

    private boolean validateInput(String email, String password) {
        return email != null && !email.trim().isEmpty()
            && password != null && !password.trim().isEmpty();
    }
}
