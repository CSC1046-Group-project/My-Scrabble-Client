package com.example.SceneManager;
public class SceneManager {

    // Name of scenes
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

    private static MyScene _currentScene;           // Current scene displayed
    private static SceneNames _currentSceneName;    // Current scene name

    // Init the scene manager by loading the first scene
    public static void init() {
        _currentSceneName = SceneNames.FIRST_SCENE;
        loadScene(_currentSceneName);
    }

    // Load a scen on screen
    public static void loadScene(SceneNames sceneName) {
        System.out.println("Loading scene: " + sceneName.name());

        MyScene nextScene = null;

        switch (sceneName) {
            case FIRST_SCENE :
                nextScene = SceneFactory.createFirstScene();
                break;
            case LOGIN_SCENE :
                nextScene = SceneFactory.createLoginScene();
                break;
            case REGISTER_SCENE :
                nextScene = SceneFactory.createRegisterScene();
                break;
            case CHOOSE_SCENE :
                nextScene = SceneFactory.createChooseScene();
                break;
            case JOIN_SCENE :
                nextScene = SceneFactory.createJoinScene();
                break;
            case GAME_SCENE :
                nextScene = SceneFactory.createGameScene();
                break;
            case SETTINGS_SCENE :
                nextScene = SceneFactory.createSettingsScene();
                break;
            case WINNING_SCENE :
                nextScene = SceneFactory.createWinningScene();
                break;
            default:
                break;
        }

        if (nextScene == null) {
            System.err.println("Loading scene failed");
            return;
        }

        // change the scene and draw it on screen
        _currentSceneName = sceneName;
        _currentScene = nextScene;
        _currentScene.onDraw();
    }
}
