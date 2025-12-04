package com.example.Game;

import java.util.HashMap;

import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.TextBuilder;

public class Game {

    private static final HashMap<String, Player> _players = new HashMap<>();
    private static String _playerTurn;
    private static String _winner;

    public static void start() {
        _players.clear();
        _playerTurn = "";
        _winner = "";
    }

    public static void addPlayer(String token, String name, TextBuilder scoreText, TextBuilder timerText, HBoxBuilder userBox) {
        Player p = new Player(name, scoreText, timerText, userBox);
        _players.put(token, p);
    }

    public static void removePlayer(String token) {
        _players.remove(token);
    }

    public static HBoxBuilder getPlayerUserBox(String token) {
        Player p = _players.get(token);
        if (p != null) {
            return p.getUserBox();
        }
        return null;
    }

    public static void setTurn(String token) {
        Player playerToPause = _players.get(_playerTurn);
        if (playerToPause != null) {
            playerToPause.pauseTimer();
            playerToPause.setTurn(false);
        }

        Player playerToStart = _players.get(token);
        if (playerToStart != null) {
            playerToStart.startTimer();
            playerToStart.setTurn(true);
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

    public static void setWinner(String name) {
        _winner = name;
    }

    public static String getWinner() {
        return _winner;
    }
}
