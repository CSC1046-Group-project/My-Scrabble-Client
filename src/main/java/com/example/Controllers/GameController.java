package com.example.Controllers;

import com.example.Interfaces.GameView;
import com.example.Interfaces.IGameService;
import com.example.Interfaces.IUserSession;

public class GameController {

    private final IUserSession _userSession;
    private final IGameService _gameService;
    private final GameView _view;

    public GameController(
        IUserSession userSession,
        IGameService gameService,
        GameView view
    ) {
        _userSession = userSession;
        _gameService = gameService;
        _view = view;
    }

    public void handleReady() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        _view.hideReadyButton();

        // _gameService.ready(_userSession.getToken(), _userSession.getIdRoom(), new JoinGameCallback() {
        //     @Override
        //     public void onSuccess(String idRoom) {
        //         // Handled by network listener
        //     }

        //     @Override
        //     public void onFailure(String message) {
        //         // _view.showError(message);
        //     }
        // });
    }
}
