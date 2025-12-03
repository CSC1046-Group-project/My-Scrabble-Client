package com.example.Game;

import java.util.HashMap;

import com.example.RendereUI.Widgets.TextBuilder;

public class Game {

    private static final HashMap<String, Player> _players = new HashMap<>();
    private static String _playerTurn;

    public static void start() {
        _players.clear();
        _playerTurn = "";
    }

    public static void addPlayer(String token, String name, TextBuilder scoreText, TextBuilder timerText) {
        Player p = new Player(name, scoreText, timerText);
        _players.put(token, p);
    }

    public static void setTurn(String token) {
        Player playerToPause = _players.get(_playerTurn);
        if (playerToPause != null)
            playerToPause.pauseTimer();

        Player playerToStart = _players.get(token);
        if (playerToStart != null) {
            playerToStart.startTimer();
        }
        _playerTurn = token;
    }

    public static void setScore(String token, String score) {
        Player player = _players.get(token);
        player.setScore(score);
    }

    public static HashMap<String, Player> getPlayers() {
        return _players;
    }
}
