package com.example.SceneManager.Scenes;

import com.example.Controllers.JoinController;
import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.JoinViewBuilder;
import com.example.Views.JoinViewImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

// join scene
public class JoinScene extends MyScene {

    public JoinScene(
        IJoinGameService joinGameService,
        INavigationService navigationService,
        IUserSession userSession
    ) {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        // Create view implementation
        JoinViewImpl viewImpl = new JoinViewImpl(_root);

        // Create controller
        JoinController controller = new JoinController(
            joinGameService,
            navigationService,
            userSession,
            viewImpl
        );

        // Build UI
        JoinViewBuilder viewBuilder = new JoinViewBuilder(navigationService, controller);
        _root.getChildren().add(viewBuilder.build());
    }

    @Override
    public void initListener() {
    }
}
