package com.example.Controllers;

import com.example.Interfaces.AuthCallback;
import com.example.Interfaces.CredentialsView;
import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;

public class RegisterController {
    private final IAuthenticationService authService;
    private final INavigationService navigationService;
    private final IUserSession userSession;
    private final CredentialsView view;

    public RegisterController(
        IAuthenticationService authService,
        INavigationService navigationService,
        IUserSession userSession,
        CredentialsView view
    ) {
        this.authService = authService;
        this.navigationService = navigationService;
        this.userSession = userSession;
        this.view = view;
    }

    public void handleRegister(String username, String email, String password) {
        if (!validateInput(username, email, password)) {
            view.showError("Username, email and password are required");
            return;
        }
        authService.register(username, email, password, new AuthCallback() {
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

    private boolean validateInput(String username, String email, String password) {
        return email != null && !email.trim().isEmpty()
            && password != null && !password.trim().isEmpty()
            && username != null && !username.trim().isEmpty();
    }
}
