package com.example.Services.Login;

import com.example.Interfaces.INavigationService;
import com.example.SceneManager.SceneManager;

public class SceneManagerNavigationService implements INavigationService {
    @Override
    public void navigateToChooseScene() {
        SceneManager.loadScene(SceneManager.SceneNames.CHOOSE_SCENE);
    }

    @Override
    public void navigateToRegisterScene() {
        SceneManager.loadScene(SceneManager.SceneNames.REGISTER_SCENE);
    }

    @Override
    public void navigateToFirstScene() {
        SceneManager.loadScene(SceneManager.SceneNames.FIRST_SCENE);
    }

    @Override
    public void navigateToLoginScene() {
        SceneManager.loadScene(SceneManager.SceneNames.LOGIN_SCENE);
    }
}
