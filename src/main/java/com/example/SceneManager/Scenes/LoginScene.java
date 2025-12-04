package com.example.SceneManager.Scenes;

import com.example.Controllers.LoginController;
import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.LoginViewBuilder;
import com.example.Views.LoginViewImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class LoginScene extends MyScene {
    public LoginScene(
        IAuthenticationService authService,
        INavigationService navigationService,
        IUserSession userSession
    ) {
        // Configure root
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(
            new BackgroundFill(Color.web("0x16161E"), null, null)
        ));
        _root.setAlignment(Pos.CENTER);

        // Create view implementation
        LoginViewImpl viewImpl = new LoginViewImpl(_root);

        // Create controller
        LoginController controller = new LoginController(
            authService,
            navigationService,
            userSession,
            viewImpl
        );

        // Build UI
        LoginViewBuilder viewBuilder = new LoginViewBuilder(navigationService, controller);
        Node panel = viewBuilder.build();

        _root.getChildren().add(panel);
    }

    @Override
    public void initListener() {
    }
}
