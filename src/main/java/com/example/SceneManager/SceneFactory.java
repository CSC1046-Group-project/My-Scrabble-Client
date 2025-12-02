package com.example.SceneManager;

import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.Scenes.LoginScene;
import com.example.Services.Login.NetworkAuthenticationService;
import com.example.Services.Login.SceneManagerNavigationService;
import com.example.Services.Login.UserSessionImpl;

public class SceneFactory {

    public static LoginScene createLoginScene() {
        IAuthenticationService authService = new NetworkAuthenticationService();
        INavigationService navigationService = new SceneManagerNavigationService();
        IUserSession userSession = new UserSessionImpl();

        return new LoginScene(authService, navigationService, userSession);
    }

}
