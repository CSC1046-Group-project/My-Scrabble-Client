package com.example.Controllers;

import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.Interfaces.JoinGameCallback;
import com.example.Interfaces.View;

public class JoinController {

    private final IJoinGameService _joinGameService;
    private final INavigationService _navigationService;
    private final IUserSession _userSession;
    private final View _view;

    public JoinController(
        IJoinGameService joinGameService,
        INavigationService navigationService,
        IUserSession userSession,
        View view
    ) {
        _joinGameService = joinGameService;
        _navigationService = navigationService;
        _userSession = userSession;
        _view = view;
    }

    public void handleJoin(String code, String password) {
        if (!_userSession.isLog())
            return;

        if (!validateInput(code, password)) {
            _view.showError("Code and password are required");
            return;
        }

        _joinGameService.join(_userSession.getToken(), code, password, new JoinGameCallback() {

            @Override
            public void onSuccess(String idRoom, String password) {
                _userSession.setIdRoom(idRoom);
                _userSession.setRoomPassword(password);
                _navigationService.navigateToGame();
            }

            @Override
            public void onFailure(String message) {
                _view.showError(message);
            }
        });
    }

    private boolean validateInput(String code, String password) {
        return code != null && !code.trim().isEmpty()
            && password != null && !password.trim().isEmpty();
    }
}
