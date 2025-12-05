package com.example.Game;

import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.TextBuilder;

// Class to handle a player state
public class Player extends User {

    private final String _name;             // Name of the player
    private final Timer _timer;             // Timer for time left in game

    private final TextBuilder _textScore;   // Text field in UI to update the score
    private final TextBuilder _textTimer;   // Text field in UI to update the timer
    private final HBoxBuilder _userBox;     // User box in the ui to update the connection

    // Constructor of player
    public Player(String name, TextBuilder textScore, TextBuilder textTimer, HBoxBuilder userBox) {
        _name = name;
        _textScore = textScore;
        _textTimer = textTimer;
        _timer = new Timer(textTimer);
        _userBox = userBox;
    }

    // Start the player timer
    public void startTimer() {
        _timer.startTimer();
    }

    // Pause the player timer
    public void pauseTimer() {
        _timer.pauseTimer();
    }

    // Reset the player timer
    public void resetTimer() {
        _timer.resetTimer();
    }

    // Update th score of the player in ui box
    public void setScore(String score) {
        _textScore.setText(score);
    }

    // Get the ui box of the player
    public HBoxBuilder getUserBox() {
        return _userBox;
    }

    // Update the ui if it's the player turn
    public void setTurn(boolean isTurn) {
        if (isTurn) {
            _userBox.setStyle("-fx-background-color: #415f41ff;");
        } else {
            _userBox.setStyle("-fx-background-color: #282833;");
        }
    }
}
