package com.example.SceneManager.Scenes;

import com.example.Controllers.ChooseController;
import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.ChooseViewBuilder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

// Choose scene
public class ChooseScene extends MyScene {

    public ChooseScene(
        INavigationService navigationService,
        IJoinGameService joiGameService,
        IUserSession userSession
    ) {

        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        ChooseController controller = new ChooseController(joiGameService, navigationService, userSession);

        // Build UI
        ChooseViewBuilder viewBuilder = new ChooseViewBuilder(navigationService, controller);
        Node panel = viewBuilder.build();

        _root.getChildren().add(panel);
    }

    @Override
    public void initListener() {
    }
}
