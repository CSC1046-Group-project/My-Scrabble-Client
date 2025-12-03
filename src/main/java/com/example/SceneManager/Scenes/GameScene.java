package com.example.SceneManager.Scenes;

import java.util.ArrayList;
import java.util.List;

import com.example.Controllers.GameController;
import com.example.Game.BoardCell;
import com.example.Game.Tile;
import com.example.Game.TileRack;
import com.example.Game.User;
import com.example.Interfaces.IGameService;
import com.example.Interfaces.IUserSession;
import com.example.Network.Network;
import com.example.Network.Protocol.ProtocolFactory;
import com.example.SceneManager.MyScene;
import com.example.Services.GameEventHandler;
import com.example.UIBuilder.GameViewBuilder;
import com.example.Views.GameViewImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class GameScene extends MyScene {

    private final List<BoardCell> _cellsPlaced = new ArrayList<>();
    private final TileRack _tileRack = new TileRack();

    private final GameEventHandler _eventHandler;

    public GameScene(
        IGameService gameService,
        IUserSession userSession
    ) {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);


        // Build UI
        GameViewBuilder viewBuilder = new GameViewBuilder();
        _root.getChildren().addAll(viewBuilder.build());

        // Create the view
        GameViewImpl gameView = new GameViewImpl(
            _root,
            viewBuilder.getBoard(),
            _tileRack,
            _cellsPlaced,
            viewBuilder.getUsersBox(),
            viewBuilder.getReadyButton(),
            viewBuilder.getTilesLeft(),
            viewBuilder.getSubmitButton(),
            viewBuilder.getSkipButton(),
            viewBuilder.getSwapButton(),
            viewBuilder.getRack()
        );

        // Create the controller
        GameController controller = new GameController(userSession, gameService, gameView);
        viewBuilder.setController(controller);

        // Init event handler to listen network
        _eventHandler = new GameEventHandler(gameView, userSession, _tileRack);
    }

    @Override
    public void initRoot() {
    }

    @Override
    public void initListener() {
        _eventHandler.run();
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
                Tile tile = _cellsPlaced.get(i).getTile();
                if (tile == null)
                    continue;

                if (i > 0)
                    word += "|";
                word += _cellsPlaced.get(i).getTile().getLetter();
                word += String.valueOf(tile.getPoint());
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

    // private void onHavePlayed(ProtocolMessage message) {

    //     _cellsPlaced.clear();
    //     try {

    //         String name = message.getArgs().get(0);
    //         String word = message.getArgs().get(1);
    //         String[] parts = word.split("\\|");
    //         int x = Integer.parseInt(message.getArgs().get(2));
    //         int y = Integer.parseInt(message.getArgs().get(3));
    //         boolean isHorizontal = Boolean.parseBoolean(message.getArgs().get(4));

    //         Platform.runLater(() -> {
    //             int idx = 0;
    //             for (String p : parts) {
    //                 String letter = String.valueOf(p.charAt(0));
    //                 int value = Integer.parseInt(p.substring(1));

    //                 Tile tile = new Tile();
    //                 tile.setLetter(letter);
    //                 tile.setPoint(value);
    //                 TileBuilder tileBuilder= WidgetFactory.tile(tile, (isHorizontal) ? x+idx : x, (isHorizontal) ? y : y+idx, false, -1);
    //                 _board.addTile(tileBuilder, (isHorizontal) ? x+idx : x, (isHorizontal) ? y : y+idx);
    //                 idx++;
    //             }
    //         });
    //     } catch (Exception e) {
    //     }
    // }
}
