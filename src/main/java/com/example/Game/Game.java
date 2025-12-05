package com.example.Game;

import java.util.HashMap;

import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.TextBuilder;

public class Game {

    private static final HashMap<String, Player> _players = new HashMap<>();        // List of players with token
    private static String _playerTurn;                                              // current player to play
    private static String _winner;                                                  // Winner of the game

    // Start the game
    public static void start() {
        _players.clear();
        _playerTurn = "";
        _winner = "";
    }

    // Add a player to the game
    public static void addPlayer(String token, String name, TextBuilder scoreText, TextBuilder timerText, HBoxBuilder userBox) {
        Player p = new Player(name, scoreText, timerText, userBox);
        _players.put(token, p);
    }

    // Remove a player to the game
    public static void removePlayer(String token) {
        _players.remove(token);
    }

    // Get the player userBox to update the view
    public static HBoxBuilder getPlayerUserBox(String token) {
        Player p = _players.get(token);
        if (p != null) {
            return p.getUserBox();
        }
        return null;
    }

    // Set the player's turn
    public static void setTurn(String token) {

        // Stop the timer of the current player
        Player playerToPause = _players.get(_playerTurn);
        if (playerToPause != null) {
            playerToPause.pauseTimer();
            playerToPause.setTurn(false);
        }

        // Start the timer of the new current player
        Player playerToStart = _players.get(token);
        if (playerToStart != null) {
            playerToStart.startTimer();
            playerToStart.setTurn(true);
        }
        _playerTurn = token;
    }

    // Set the score of a player
    public static void setScore(String token, String score) {
        Player player = _players.get(token);
        player.setScore(score);
    }

    // Get the players list
    public static HashMap<String, Player> getPlayers() {
        return _players;
    }

    // Set the winner of the game
    public static void setWinner(String name) {
        _winner = name;
    }

    // Get the winner of the game
    public static String getWinner() {
        return _winner;
    }
}
