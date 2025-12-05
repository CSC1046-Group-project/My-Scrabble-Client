package com.example.Controllers;

import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.Interfaces.JoinGameCallback;

public class ChooseController {

    private final IJoinGameService _joinGameService;
    private final INavigationService _navigationService;
    private final IUserSession _userSession;

    public ChooseController(
        IJoinGameService joinGameService,
        INavigationService navigationService,
        IUserSession userSession
    ) {
        _joinGameService = joinGameService;
        _navigationService = navigationService;
        _userSession = userSession;
    }

    // Handle the logout button
    public void handleLogout() {
        // Logout the session
        _userSession.logout();
        // Navigate to first scene
        _navigationService.navigateToFirstScene();
    }

    // Handle the creation of a private game
    public void handleCreatePrivateGame() {
        // Check if user logged
        if (!_userSession.isLog())
            return;

        // call service to start the private game
        _joinGameService.startPrivate(_userSession.getToken(), new JoinGameCallback() {

            @Override
            public void onSuccess(String idRoom, String password) {
                // On success get the id room and password and go to game
                _userSession.setIdRoom(idRoom);
                _userSession.setRoomPassword(password);
                _navigationService.navigateToGame();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    // Handle the start of a public game
    public void handleStartPublicGame() {

        // Check if user logged
        if (!_userSession.isLog())
            return;

        // call service to start the public game
        _joinGameService.startPublic(_userSession.getToken(), new JoinGameCallback() {

            @Override
            public void onSuccess(String idRoom, String password) {
                // On success get room id and go to the game
                _userSession.setIdRoom(idRoom);
                _navigationService.navigateToGame();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }
}
