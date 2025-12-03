package com.example.Controllers;

import com.example.Game.WordPlacement;
import com.example.Interfaces.GameCallback;
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

        _gameService.ready(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
                _view.hideReadyButton();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void handleChallenge() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;
        _gameService.challenge(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void handleResign() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;
        _gameService.resign(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void handleSkip() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;
        _gameService.skip(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    public void handleSwap() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;
        // _gameService.swap(_userSession.getToken(), _userSession.getIdRoom());
    }

    public void handleSubmit() {
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;


        WordPlacement placement = WordPlacement.validateAndCreatePlacement();
        if (placement == null)
            return;

        _gameService.submit(
            _userSession.getToken(),
            _userSession.getIdRoom(),
            placement._word,
            String.valueOf(placement._x),
            String.valueOf(placement._y),
            String.valueOf(placement._isHorizontal),
            new GameCallback() {
                @Override
                public void onSuccess() {
                }

                @Override
                public void onFailure(String message) {
                }
            }
        );
    }
}
