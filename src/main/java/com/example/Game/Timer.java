
package com.example.Game;

import com.example.RendereUI.Widgets.TextBuilder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

// Class to handle a timer for player's moves
public class Timer {

    private Timeline _timer;                // Timer
    private boolean _isRunning = false;     // True if the timer is running
    private int _time = 15*60;              // Based time of the timer (15min)

    private final TextBuilder _textTimer;   // Timer text in the UI

    // Constructor of the timer class, get the UI text to update the timer
    public Timer(TextBuilder textTimer) {
        _textTimer = textTimer;
    }

    // Start the timer
    public void startTimer() {

        // Check if already running
        if (_isRunning) {
            return;
        }

        // Create a timer on a thread calling a function every seconds
        _timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            // Decrease the timer
            _time -= 1;

            // If timer set to 0, stop the thread (timer)
            if (_time == 0) {
                _timer.stop();
                _isRunning = false;
            }

            // Update the ui with the timer value
            _textTimer.setText(timerToString());
        }));

        // Start the timer thread
        _timer.setCycleCount(Timeline.INDEFINITE);
        _timer.play();
        _isRunning = true;
    }

    // Pause the timer
    public void pauseTimer() {
        if (_timer != null) {
            _timer.pause();
            _isRunning = false;
        }
    }

    // Reset the timer
    public void resetTimer() {
        if (_timer != null) {
            _timer.stop();
        }
        _isRunning = false;
        _time = 15*60;
    }

    // Convert the timer integer into a string (min:sec)
    private String timerToString() {
        int minute = _time / 60;
        int seconds = _time - (minute * 60);

        String time = String.valueOf(minute) + ":" + String.valueOf(seconds);
        return time;
    }
}
