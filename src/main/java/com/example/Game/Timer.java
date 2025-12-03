
package com.example.Game;

import com.example.RendereUI.Widgets.TextBuilder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {

    private Timeline _timer;
    private boolean _isRunning = false;
    private int _time = 15*60;

    private final TextBuilder _textTimer;

    public Timer(TextBuilder textTimer) {
        _textTimer = textTimer;
    }

    public void startTimer() {

        if (_isRunning) {
            return;
        }

        _timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            _time -= 1;

            if (_time == 0) {
                _timer.stop();
                _isRunning = false;
            }

            _textTimer.setText(timerToString());
            // String activeToken = playerManager.getActivePlayerToken();
            // if (activeToken != null) {
            //     PlayerUIComponent activePlayer = playerManager.getPlayer(activeToken);
            //     if (activePlayer != null) {
            //         activePlayer.decrementTimer();

            //         // Check if time is up
            //         if (activePlayer.getData().getTimeRemaining() <= 0) {
            //             onPlayerTimeUp(activeToken);
            //         }
            //     }
            // }
        }));

        _timer.setCycleCount(Timeline.INDEFINITE);
        _timer.play();
        _isRunning = true;
    }

    public void pauseTimer() {
        if (_timer != null) {
            _timer.pause();
            _isRunning = false;
        }
    }

    public void resetTimer() {
        if (_timer != null) {
            _timer.stop();
        }
        _isRunning = false;
        _time = 15*60;
    }

    private String timerToString() {
        int minute = _time / 60;
        int seconds = _time - (minute * 60);

        String time = String.valueOf(minute) + ":" + String.valueOf(seconds);
        return time;
    }
}
