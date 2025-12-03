package com.example.Game;

import com.example.RendereUI.Widgets.TextBuilder;

public class Player extends User {

    private final String _name;
    private final Timer _timer;

    private final TextBuilder _textScore;
    private final TextBuilder _textTimer;

    public Player(String name, TextBuilder textScore, TextBuilder textTimer) {
        _name = name;
        _textScore = textScore;
        _textTimer = textTimer;
        _timer = new Timer(textTimer);
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
}
