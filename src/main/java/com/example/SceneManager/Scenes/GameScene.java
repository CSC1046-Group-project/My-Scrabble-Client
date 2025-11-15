package com.example.SceneManager.Scenes;

import java.util.ArrayList;
import java.util.List;

import com.example.Game.BoardCell;
import com.example.Game.Tile;
import com.example.Game.TileRack;
import com.example.Game.User;
import com.example.Network.Listener;
import com.example.Network.Network;
import com.example.Network.Protocol.MessageType;
import com.example.Network.Protocol.ProtocolFactory;
import com.example.Network.Protocol.ProtocolMessage;
import com.example.RendereUI.WidgetFactory;
import com.example.RendereUI.Widgets.BoardBuilder;
import com.example.RendereUI.Widgets.ButtonBuilder;
import com.example.RendereUI.Widgets.HBoxBuilder;
import com.example.RendereUI.Widgets.IconButtonBuilder;
import com.example.RendereUI.Widgets.TextBuilder;
import com.example.RendereUI.Widgets.TileBuilder;
import com.example.RendereUI.Widgets.VBoxBuilder;
import com.example.SceneManager.MyScene;
import com.example.SceneManager.SceneManager;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameScene extends MyScene {

    private BoardBuilder _board;
    private final List<BoardCell> _cellsPlaced = new ArrayList<>();
    // private List<Tile> _tiles;
    private TileRack _tileRack;
    private Pane _rack;

    private ButtonBuilder _readyButton;
    private VBoxBuilder _usersBox;

    @Override
    public void initRoot() {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        VBoxBuilder page = WidgetFactory.vbox()
            .setStyle("-fx-background-color: transparent;")
            .setPadding(0);
        HBoxBuilder header = WidgetFactory.hbox()
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE);

        HBoxBuilder gameView = WidgetFactory.hbox()
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(Double.MAX_VALUE)
            .setMaxHeight(1880)
            .setPrefHeight(1880);

        VBoxBuilder game = WidgetFactory.vbox()
            .setMaxWidth(1000)
            .setPrefWidth(1000)
            .setStyle("-fx-background-color: transparent;");

        VBoxBuilder panel = WidgetFactory.vbox()
            .setMaxWidth(550)
            .setMaxHeight(850)
            .setPrefWidth(550)
            .setAlignment(Pos.CENTER_RIGHT);

        header(header);
        boardView(game);
        rightSidePanel(panel);

        gameView.addWithFlex(game.getNode(), panel.getNode());
        page.add(header.getNode(), gameView.getNode());
        _root.getChildren().addAll(page.getNode());
    }

    private void header(HBoxBuilder header) {

        // Create settings icon
        IconButtonBuilder settingsIcon = WidgetFactory.iconButton(
            "/assets/settings.png",
            e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        ).setFitWidth(24).setFitHeight(24);
        // Create settings button
        ButtonBuilder settingsButton = WidgetFactory.button(
            "Settings",
            e -> SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE)
        ).setPrefHeight(34)
            .setFont(16)
            .setStyle("-fx-background-color: transparent;")
            .setMaxWidth(120)
            .setAlignment(Pos.CENTER_LEFT);
        HBoxBuilder settings = WidgetFactory.hbox().setSpacing(0).setStyle("-fx-background-color: transparent;");
        settings.add(settingsIcon.getNode(), settingsButton.getNode());

        // Game code/password
        TextBuilder gameCode = WidgetFactory.text("Private Game 56-54-24-12-43#Password");

        header.addWithFlex(settings.getNode(), gameCode.getNode());
    }

    private void boardView(VBoxBuilder game) {

        // REMOVE THIS SAMPLE EXAMPLE
        // _tiles = new ArrayList<>();
        // String[] letters = { "E", "E", "O", "R", "H", "D", "U" };
        // int[] points     = {  1,   1,   1,   1,   4,   2,   1 };
        // for (int i = 0; i < letters.length; i++) {
        //     Tile tile = new Tile();
        //     tile.setLetter(letters[i]);
        //     tile.setPoint(points[i]);
        //     _tiles.add(tile);
        // }

        _tileRack = new TileRack();
        // for (Tile t : _tiles) {
        //     _tileRack.addTile(t);
        // }

        _board = WidgetFactory.board(15, 15, 50);

        ButtonBuilder challengeButton = WidgetFactory.button("Challenge", e -> challenge())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #C04D4D; -fx-background-radius: 10;");

        initTileRack();
        HBoxBuilder challengeRackBlock = WidgetFactory.hbox()
            .setMaxWidth(696)
            .setPrefWidth(696)
            .setStyle("-fx-background-color: transparent;");
        challengeRackBlock.add(challengeButton.getNode(), _rack);

        HBoxBuilder buttons = WidgetFactory.hbox().setStyle("-fx-background-color: transparent;").setSpacing(20);
        ButtonBuilder resignButton = WidgetFactory.button("Resign", e -> resign())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder skipButton = WidgetFactory.button("Skip", e -> skip())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder swapButton = WidgetFactory.button("Swap", e -> swap())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5)
            .setStyle("-fx-background-color: #282833; -fx-background-radius: 10;");
        ButtonBuilder submitButton = WidgetFactory.button("Submit", e -> submit())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5);
        buttons.add(resignButton.getNode(), skipButton.getNode(), swapButton.getNode(), submitButton.getNode());
        game.add(_board.getNode(), challengeRackBlock.getNode(), buttons.getNode());
    }

    private void initTileRack() {

        _rack = new Pane();
        _rack.setPrefWidth(550);
        _rack.setPrefHeight(86);
        _rack.setMaxWidth(550);
        _rack.setMaxHeight(86);
        _rack.setStyle("-fx-background-color: #222226;");

        displayTileRack();
    }

    private void displayTileRack() {
        _rack.getChildren().clear();

        // Shuffle button (left)
        IconButtonBuilder shuffleIcon = WidgetFactory.iconButton(
            "/assets/shuffle.png",
            e -> {}
        ).setFitWidth(32).setFitHeight(32)
         .setStyle("-fx-background-color: #282833");

        shuffleIcon.getNode().setLayoutX(13);
        shuffleIcon.getNode().setLayoutY(16);

        // Rotate icon (right)
        IconButtonBuilder rotateIcon = WidgetFactory.iconButton(
            "/assets/rotate.png",
            e -> tilesGoBackToRack()
        ).setFitWidth(32).setFitHeight(32)
        .setStyle("-fx-background-color: #282833");

        rotateIcon.getNode().setLayoutX(480);
        rotateIcon.getNode().setLayoutY(16);

        _rack.getChildren().addAll(
            shuffleIcon.getNode(),
            rotateIcon.getNode()
        );

        int x = 82;
        for (int i = 0; i < _tileRack.getTiles().size(); i++) {
            Tile t = _tileRack.getTiles().get(i);
            if (t == null) {
                x += 56;
                continue;
            }
            TileBuilder tile = WidgetFactory.tile(t, x, 16, true, i);
            tile.setOnRelease((a,b) -> releaseTile(a, b));
            _rack.getChildren().add(tile.getNode());
            x += 56;
        }
    }

    private boolean releaseTile(TileBuilder tile, double[] pos) {
        Integer[] mousePosOnBoard = _board.getCellHover(pos[0], pos[1]);
        TileBuilder newTile = WidgetFactory.tile(tile.getTile(), 0, 0, false, tile.getRackIndex());

        _tileRack.removeTile(tile.getRackIndex());

        if (_board.addTile(newTile, mousePosOnBoard[0], mousePosOnBoard[1])) {
            _cellsPlaced.add(_board.getBoardCell(mousePosOnBoard[0], mousePosOnBoard[1]));
            return true;
        } else {
            return false;
        }
    }

    private void rightSidePanel(VBoxBuilder panel) {

        _usersBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833");

        _readyButton = WidgetFactory.button("Set Ready", e -> ready())
            .setFont(16)
            .setPrefWidth(122.5)
            .setMaxWidth(122.5);

        // HBoxBuilder user1 = createUserBox("Player1", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        // HBoxBuilder user2 = createUserBox("Player2", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        // HBoxBuilder user3 = createUserBox("Player3", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");
        // HBoxBuilder user4 = createUserBox("Player4", "/assets/example-profilepic.png").setStyle("-fx-background-color: #282833");

        // usersBox.add(user1.getNode(), user2.getNode(), user3.getNode(), user4.getNode());
        _usersBox.add(_readyButton.getNode());

        VBoxBuilder tilebagBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(1);
        HBoxBuilder tiletext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");

        TextBuilder tilebagText = WidgetFactory.text("Tile bag - 81 tiles left").setFont(16);
        tiletext.add(tilebagText.getNode());

        VBoxBuilder tilesleft = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        // TextBuilder line1 = WidgetFactory.text("A  A  A  A  A  A  A  A  B  C  C  D  D  D  ").setFont(16);
        // TextBuilder line2 = WidgetFactory.text("E  E  E  E  E  E  E  E  E  F  F  G  G  G  ").setFont(16);
        // TextBuilder line3 = WidgetFactory.text("I  I  I  I  I  I  I  I  I  J  K  L  L  L  L  M  M").setFont(16);
        // TextBuilder line4 = WidgetFactory.text("N  N  N  N  N  N  O  O  O  O  O  O  O  P  P").setFont(16);
        // TextBuilder line5 = WidgetFactory.text("Q  R  R  R  R  R  S  S  S  T  T  T  T  T").setFont(16);
        // TextBuilder line6 = WidgetFactory.text("U  U  U  V  V  W  W  X  Y  Y  Z  ?  ?  ").setFont(16);

        // tilesleft.add(line1.getNode(), line2.getNode(), line3.getNode(), line4.getNode(), line5.getNode(), line6.getNode());
        tilebagBox.add(tiletext.getNode(), tilesleft.getNode());

        VBoxBuilder turnhistoryBox = WidgetFactory.vbox().setStyle("-fx-background-color: #282833").setSpacing(2);

        HBoxBuilder turnhistorytext = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        TextBuilder turnhistoryText = WidgetFactory.text("Turn History").setFont(16);
        turnhistorytext.add(turnhistoryText.getNode());

        // IconButtonBuilder profilePic = WidgetFactory.iconButton(
        //     "/assets/example-profilepic.png"
        // ).setFitWidth(30).setFitHeight(30);

        // IconButtonBuilder profilePic2 = WidgetFactory.iconButton(
        //     "/assets/example-profilepic.png"
        // ).setFitWidth(30).setFitHeight(30);

        // HBoxBuilder turn1 = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        // TextBuilder usernameText = WidgetFactory.text("Player1").setFont(16);
        // TextBuilder points = WidgetFactory.text("6").setFont(16).setTextAlignment(TextAlignment.RIGHT);
        // turn1.add(usernameText.getNode(), profilePic.getNode(), points.getNode());

        // HBoxBuilder turn2 = WidgetFactory.hbox().setStyle("-fx-background-color: #282833");
        // TextBuilder username2Text = WidgetFactory.text("Player2").setFont(16);
        // TextBuilder points2 = WidgetFactory.text("9").setFont(16).setTextAlignment(TextAlignment.RIGHT);
        // turn2.add(username2Text.getNode(), profilePic2.getNode(), points2.getNode());

        // turnhistoryBox.add(turnhistorytext.getNode(), turn1.getNode(), turn2.getNode());

        panel.addWithFlex(_usersBox.getNode(), tilebagBox.getNode(), turnhistoryBox.getNode());
    }

    // we will change this to user.getUsername() and user.getProfilePicPath() later
    public static HBoxBuilder createUserBox(String username, String profilePicPath, String score, String timer) {

            HBoxBuilder allusersBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
            HBoxBuilder userBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;");
            HBoxBuilder statsBox = WidgetFactory.hbox().setStyle("-fx-background-color: #282833;").setAlignment(Pos.CENTER_RIGHT);

            TextBuilder usernameText = WidgetFactory.text(username).setFont(16);

            IconButtonBuilder profilePic = WidgetFactory.iconButton(
                "/assets/example-profilepic.png"
            ).setFitWidth(30).setFitHeight(30);

            // just using this to make the scores different for each user for now
            int n = (Character.getNumericValue(username.charAt(username.length() - 1)));
            // int score = 20 + (n * 11) + (n^2 * 2);
            // int time = 600 + Math.abs((n * 9282 + 874768) % 801);
            // String timeString = String.format("%02d:%02d", time / 60, time % 60);

            TextBuilder scoreText = WidgetFactory.text(String.valueOf(score)).setFont(30);
            TextBuilder timeText = WidgetFactory.text(timer).setFont(16);

            userBox.add(profilePic.getNode(), usernameText.getNode());
            statsBox.add(scoreText.getNode(), timeText.getNode());

            allusersBox.add(userBox.getNode(), statsBox.getNode());
            return allusersBox;
    }

    @Override
    public void initListener() {
        Listener gameListener = new Listener();
        gameListener.on(MessageType.PLAYER_HAVE_PLAYED, msg -> onHavePlayed(msg));
        gameListener.on(MessageType.TILEBAG, msg -> onTileBagMessage(msg));
        gameListener.on(MessageType.PLAYER_IS_READY, msg -> onPlayerIsReady(msg));
        gameListener.on(MessageType.GAME_START, msg -> onGameStart(msg));
        Network.setListener(gameListener);
    }

    private void ready() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        if (User.getRoomId() == null || User.getRoomId().equals(""))
            return;
        _usersBox.remove(_readyButton.getNode());
        Network.sendMessage(ProtocolFactory.ready(User.getToken(), User.getRoomId()));
    }

    private void resign() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.resign(User.getToken()));
    }

    private void skip() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.skip(User.getToken()));
    }

    private void swap() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.swap(User.getToken(), ""));
    }

    private void submit() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        if (User.getRoomId() == null || User.getRoomId().equals(""))
            return;

        if (_cellsPlaced == null || _cellsPlaced.isEmpty())
            return;

        boolean isVertical = true;
        boolean isHorizontal = true;
        boolean isHalign = true;
        boolean isValign = true;
        int currentPosV = -1;
        int currentPosH = -1;

        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[0], b.getPos()[0]));

        for (int i = 0; i < _cellsPlaced.size(); i++) {
            BoardCell c = _cellsPlaced.get(i);
            if (currentPosV == -1) {
                currentPosV = c.getPos()[0];
                currentPosH = c.getPos()[1];
                continue;
            }
            if (c.getPos()[0] != currentPosV + 1)
                isVertical = false;
            if (c.getPos()[1] != currentPosH)
                isValign = false;
            currentPosV = c.getPos()[0];
        }
        currentPosH = -1;
        currentPosV = -1;

        if (isVertical && isValign) {
            String word = "";
            for (int i = 0; i < _cellsPlaced.size(); i++) {
                word += _cellsPlaced.get(i).getTile().getLetter();
            }

            Network.sendMessage(ProtocolFactory.submit(
                User.getToken(),
                User.getRoomId(),
                word,
                String.valueOf(_cellsPlaced.get(0).getPos()[0]),
                String.valueOf(_cellsPlaced.get(0).getPos()[1]),
                "0"
            ));
        }

        _cellsPlaced.sort((a, b) -> Integer.compare(a.getPos()[1], b.getPos()[1]));
        for (int i = 0; i < _cellsPlaced.size(); i++) {
            BoardCell c = _cellsPlaced.get(i);
            if (currentPosH == -1) {
                currentPosH = c.getPos()[1];
                currentPosV = c.getPos()[0];
                continue;
            }
            if (c.getPos()[1] != currentPosH + 1)
                isHorizontal = false;
            if (c.getPos()[0] != currentPosV)
                isHalign = false;
            currentPosH = c.getPos()[1];
        }

        if (isHorizontal && isHalign) {
            String word = "";
            for (int i = 0; i < _cellsPlaced.size(); i++) {
                Tile tile = _cellsPlaced.get(i).getTile();
                if (tile == null)
                    continue;

                if (i > 0)
                    word += "|";
                word += tile.getLetter();
                word += String.valueOf(tile.getPoint());
            }

            Network.sendMessage(ProtocolFactory.submit(
                User.getToken(),
                User.getRoomId(),
                word,
                String.valueOf(_cellsPlaced.get(0).getPos()[0]),
                String.valueOf(_cellsPlaced.get(0).getPos()[1]),
                "1"
            ));
        }
    }

    private void challenge() {
        if (User.getToken() == null || User.getToken().equals(""))
            return;
        Network.sendMessage(ProtocolFactory.challenge(User.getToken()));
    }

    private void tilesGoBackToRack() {

        for (int i = 0; i < _cellsPlaced.size(); i++) {
            _tileRack.addTile(_cellsPlaced.get(i).getTile());
            _board.removeTile(_cellsPlaced.get(i));
        }
        _cellsPlaced.clear();
        displayTileRack();
    }

    private void onTileBagMessage(ProtocolMessage message) {

    }

    private void onHavePlayed(ProtocolMessage message) {
        try {

            String name = message.getArgs().get(0);
            String word = message.getArgs().get(1);
            String[] parts = word.split("\\|");
            int x = Integer.parseInt(message.getArgs().get(2));
            int y = Integer.parseInt(message.getArgs().get(3));
            boolean isHorizontal = Boolean.parseBoolean(message.getArgs().get(4));

            Platform.runLater(() -> {
                int idx = 0;
                for (String p : parts) {
                    String letter = String.valueOf(p.charAt(0));
                    int value = Integer.parseInt(p.substring(1));

                    Tile tile = new Tile();
                    tile.setLetter(letter);
                    tile.setPoint(value);
                    TileBuilder tileBuilder= WidgetFactory.tile(tile, (isHorizontal) ? x+idx : x, (isHorizontal) ? y : y+idx, false, -1);
                    _board.addTile(tileBuilder, (isHorizontal) ? x+idx : x, (isHorizontal) ? y : y+idx);
                    idx++;
                }
            });
        } catch (Exception e) {
        }
    }

    private void onPlayerIsReady(ProtocolMessage message) {
        try {

            String name = message.getArgs().get(0);
            Platform.runLater(() -> {
                HBoxBuilder user = createUserBox(name, "/assets/example-profilepic.png", "0", "15:00")
                    .setStyle("-fx-background-color: #282833");

                _usersBox.add(user.getNode());
            });

        } catch (Exception e) {
        }
    }

    private void onGameStart(ProtocolMessage message) {
        try {

            String tiles = message.getArgs().get(0);
            String[] tilesParts = tiles.split("\\|");

            for (String t : tilesParts) {
                String letter = String.valueOf(t.charAt(0));
                int value = Integer.parseInt(t.substring(1));

                Tile tile = new Tile();
                tile.setLetter(letter);
                tile.setPoint(value);

                _tileRack.addTile(tile);
            }

            Platform.runLater(() -> {
                displayTileRack();
            });

        } catch (Exception e) {
        }
    }
}
