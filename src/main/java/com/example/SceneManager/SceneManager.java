package com.example.SceneManager;

import java.util.HashMap;

public class SceneManager {

    public static enum SceneNames {
        FIRST_SCENE,
        LOGIN_SCENE,
        REGISTER_SCENE,
        CHOOSE_SCENE,
        JOIN_SCENE,
        GAME_SCENE,
        SETTINGS_SCENE,
        WINNING_SCENE
    }

    private static final HashMap<SceneNames, MyScene> _scenes = new HashMap<>();

    private static MyScene _currentScene;
    private static SceneNames _currentSceneName;

    public static void init() {
        _scenes.put(SceneNames.FIRST_SCENE, SceneFactory.createFirstScene());
        _scenes.put(SceneNames.LOGIN_SCENE, SceneFactory.createLoginScene());
        _scenes.put(SceneNames.REGISTER_SCENE, SceneFactory.createRegisterScene());
        _scenes.put(SceneNames.CHOOSE_SCENE, SceneFactory.createChooseScene());
        _scenes.put(SceneNames.JOIN_SCENE, SceneFactory.createJoinScene());
        _scenes.put(SceneNames.GAME_SCENE, SceneFactory.createGameScene());
        _scenes.put(SceneNames.SETTINGS_SCENE, SceneFactory.createSettingsScene());
        _scenes.put(SceneNames.WINNING_SCENE, SceneFactory.createWinningScene());
        _currentScene = _scenes.get(SceneNames.FIRST_SCENE);
        _currentSceneName = SceneNames.FIRST_SCENE;
    }

    public static void loadScene(SceneNames sceneName) {
        System.out.println("Loading scene: " + sceneName.name());

        MyScene nextScene = _scenes.get(sceneName);
        if (nextScene == null) {
            System.err.println("Loading scene failed");
            return;
        }

        reset(_currentSceneName);

        _currentSceneName = sceneName;
        _currentScene = nextScene;
        _currentScene.onDraw();
    }

    private static void reset(SceneNames name) {

        switch (name) {
            case FIRST_SCENE :
                _scenes.put(SceneNames.FIRST_SCENE, SceneFactory.createFirstScene());
                break;
            case LOGIN_SCENE :
                _scenes.put(SceneNames.LOGIN_SCENE, SceneFactory.createLoginScene());
                break;
            case REGISTER_SCENE :
                _scenes.put(SceneNames.REGISTER_SCENE, SceneFactory.createRegisterScene());
                break;
            case CHOOSE_SCENE :
                _scenes.put(SceneNames.CHOOSE_SCENE, SceneFactory.createChooseScene());
                break;
            case JOIN_SCENE :
                _scenes.put(SceneNames.JOIN_SCENE, SceneFactory.createJoinScene());
                break;
            case GAME_SCENE :
                _scenes.put(SceneNames.GAME_SCENE, SceneFactory.createGameScene());
                break;
            case SETTINGS_SCENE :
                _scenes.put(SceneNames.SETTINGS_SCENE, SceneFactory.createSettingsScene());
                break;
            case WINNING_SCENE :
                _scenes.put(SceneNames.WINNING_SCENE, SceneFactory.createWinningScene());
                break;
            default:
                break;
        }
    }
}
