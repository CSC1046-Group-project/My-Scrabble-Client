package com.example.SceneManager.Scenes;

import com.example.Controllers.RegisterController;
import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.MyScene;
import com.example.UIBuilder.RegisterViewBuilder;
import com.example.Views.RegisterViewImpl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

// Register scene
public class RegisterScene extends MyScene {

    public RegisterScene(
        IAuthenticationService authService,
        INavigationService navigationService,
        IUserSession userSession
    ) {
        // Root container
        _root.setPadding(new Insets(20));
        _root.setBackground(new Background(new BackgroundFill(Color.web("0x16161E"), null, null)));
        _root.setAlignment(Pos.CENTER);

        // // Create view implementation
        RegisterViewImpl viewImpl = new RegisterViewImpl(_root);

        // Create controller
        RegisterController controller = new RegisterController(authService, navigationService, userSession, viewImpl);

        // Build UI
        RegisterViewBuilder viewBuilder = new RegisterViewBuilder(navigationService, controller);
        Node panel = viewBuilder.build();

        _root.getChildren().add(panel);
    }

    @Override
    public void initListener() {
    }
}
