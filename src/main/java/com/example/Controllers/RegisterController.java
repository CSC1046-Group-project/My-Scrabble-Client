package com.example.Controllers;

import com.example.Interfaces.AuthCallback;
import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.Interfaces.View;

public class RegisterController {
    private final IAuthenticationService authService;
    private final INavigationService navigationService;
    private final IUserSession userSession;
    private final View view;

    public RegisterController(
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

    // Handle the register button
    public void handleRegister(String username, String email, String password) {

        // Check if the username, email and password are not empty
        if (!validateInput(username, email, password)) {
            view.showError("Username, email and password are required");
            return;
        }

        // Call the service to handle the register request
        authService.register(username, email, password, new AuthCallback() {
            @Override
            public void onSuccess(String token) {
                // On success get the user token and go to the choose scene
                userSession.setToken(token);
                navigationService.navigateToChooseScene();
            }

            @Override
            public void onFailure(String message) {
                // On error, update the view with the error message
                view.showError(message);
            }
        });
    }

    // Check if the username, email and password fields are not empty
    private boolean validateInput(String username, String email, String password) {
        return email != null && !email.trim().isEmpty()
            && password != null && !password.trim().isEmpty()
            && username != null && !username.trim().isEmpty();
    }
}
