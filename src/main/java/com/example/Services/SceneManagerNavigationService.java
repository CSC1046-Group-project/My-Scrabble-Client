package com.example.Services;

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

    @Override
    public void navigateToSettingsScene() {
        SceneManager.loadScene(SceneManager.SceneNames.SETTINGS_SCENE);
    }

    @Override
    public void navigateToJoinScene() {
        SceneManager.loadScene(SceneManager.SceneNames.JOIN_SCENE);
    }

    @Override
    public void navigateToGame() {
        SceneManager.loadScene(SceneManager.SceneNames.GAME_SCENE);
    }
}
