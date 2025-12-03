package com.example.SceneManager;

import com.example.Interfaces.IAuthenticationService;
import com.example.Interfaces.IJoinGameService;
import com.example.Interfaces.INavigationService;
import com.example.Interfaces.IUserSession;
import com.example.SceneManager.Scenes.ChooseScene;
import com.example.SceneManager.Scenes.FirstScene;
import com.example.SceneManager.Scenes.JoinScene;
import com.example.SceneManager.Scenes.LoginScene;
import com.example.SceneManager.Scenes.RegisterScene;
import com.example.SceneManager.Scenes.SettingsScene;
import com.example.Services.NetworkAuthenticationService;
import com.example.Services.NetworkJoinGameService;
import com.example.Services.SceneManagerNavigationService;
import com.example.Services.UserSessionImpl;

public class SceneFactory {

    public static FirstScene createFirstScene() {
        INavigationService navigationService = new SceneManagerNavigationService();
        return new FirstScene(navigationService);
    }

    public static LoginScene createLoginScene() {
        IAuthenticationService authService = new NetworkAuthenticationService();
        INavigationService navigationService = new SceneManagerNavigationService();
        IUserSession userSession = new UserSessionImpl();
        return new LoginScene(authService, navigationService, userSession);
    }

    public static RegisterScene createRegisterScene() {
        IAuthenticationService authService = new NetworkAuthenticationService();
        INavigationService navigationService = new SceneManagerNavigationService();
        IUserSession userSession = new UserSessionImpl();
        return new RegisterScene(authService, navigationService, userSession);
    }

    public static ChooseScene createChooseScene() {
        IJoinGameService joinGameService = new NetworkJoinGameService();
        INavigationService navigationService = new SceneManagerNavigationService();
        IUserSession userSession = new UserSessionImpl();
        return new ChooseScene(navigationService, joinGameService, userSession);
    }

    public static JoinScene createJoinScene() {
        IJoinGameService joinGameService = new NetworkJoinGameService();
        INavigationService navigationService = new SceneManagerNavigationService();
        IUserSession userSession = new UserSessionImpl();
        return new JoinScene(joinGameService, navigationService, userSession);
    }

    public static SettingsScene createSettingsScene() {
        INavigationService navigationService = new SceneManagerNavigationService();
        return new SettingsScene(navigationService);
    }
}
