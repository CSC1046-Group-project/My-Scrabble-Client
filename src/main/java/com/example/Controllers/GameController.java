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

    // Handle the ready button
    public void handleReady() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        // Call the service to handle the ready request
        _gameService.ready(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
                // On success update the view
                _view.hideReadyButton();
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    // Handle the challenge button
    public void handleChallenge() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        // Call the service to handle the challenge request
        _gameService.challenge(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    // Handle the resign button
    public void handleResign() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        // Call the service to handle the resign request
        _gameService.resign(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    // Handle the skip button
    public void handleSkip() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        // Call the service to handle the skip request
        _gameService.skip(_userSession.getToken(), _userSession.getIdRoom(), new GameCallback() {

            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    // Handle the swap button
    public void handleSwap() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;

        // TODO: feature not finished
        // _gameService.swap(_userSession.getToken(), _userSession.getIdRoom());
    }

    // Handle the submit button
    public void handleSubmit() {

        // Check if user logged and in a room
        if (!_userSession.isLog())
            return;
        if (!_userSession.isInRoom())
            return;


        // Check for validation of the word placement
        WordPlacement placement = WordPlacement.validateAndCreatePlacement(_view.getBoard());
        if (placement == null)
            return;

        // Call the service to handle the submit request
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
