package com.example.SceneManager.Scenes;

import com.example.Controllers.GameController;
import com.example.Game.TileRack;
import com.example.Interfaces.IGameService;
import com.example.Interfaces.IUserSession;
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
}
