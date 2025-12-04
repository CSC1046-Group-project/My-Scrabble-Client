package com.example.Game;

import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.TextBuilder;

public class Player extends User {

    private final String _name;
    private final Timer _timer;

    private final TextBuilder _textScore;
    private final TextBuilder _textTimer;
    private final HBoxBuilder _userBox;

    public Player(String name, TextBuilder textScore, TextBuilder textTimer, HBoxBuilder userBox) {
        _name = name;
        _textScore = textScore;
        _textTimer = textTimer;
        _timer = new Timer(textTimer);
        _userBox = userBox;
    }

    public void startTimer() {
        _timer.startTimer();
    }

    public void pauseTimer() {
        _timer.pauseTimer();
    }

    public void resetTimer() {
        _timer.resetTimer();
    }

    public void setScore(String score) {
        _textScore.setText(score);
    }

    public HBoxBuilder getUserBox() {
        return _userBox;
    }

    public void setTurn(boolean isTurn) {
        if (isTurn) {
            _userBox.setStyle("-fx-background-color: #415f41ff;");
        } else {
            _userBox.setStyle("-fx-background-color: #282833;");
        }
    }
}
