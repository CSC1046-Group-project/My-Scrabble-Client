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

    public void handleLogout() {
        _userSession.logout();
        _navigationService.navigateToFirstScene();
    }

    public void handleCreatePrivateGame() {
        if (!_userSession.isLog())
            return;
        _joinGameService.startPrivate(_userSession.getToken(), new JoinGameCallback() {

            @Override
            public void onSuccess(String idRoom, String password) {
                _userSession.setIdRoom(idRoom);
                _userSession.setRoomPassword(password);
                _navigationService.navigateToGame();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void handleStartPublicGame() {
        if (!_userSession.isLog())
            return;
        _joinGameService.startPublic(_userSession.getToken(), new JoinGameCallback() {

            @Override
            public void onSuccess(String idRoom, String password) {
                _userSession.setIdRoom(idRoom);
                _navigationService.navigateToGame();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }
}
