package com.example.SceneManager;

import java.util.HashMap;

import com.example.SceneManager.Scenes.ChooseScene;
import com.example.SceneManager.Scenes.FirstScene;
import com.example.SceneManager.Scenes.GameScene;
import com.example.SceneManager.Scenes.JoinScene;
import com.example.SceneManager.Scenes.LoginScene;
import com.example.SceneManager.Scenes.RegisterScene;
import com.example.SceneManager.Scenes.SettingsScene;

public class SceneManager {

    public static enum SceneNames {
        FIRST_SCENE,
        LOGIN_SCENE,
        REGISTER_SCENE,
        CHOOSE_SCENE,
        JOIN_SCENE,
        GAME_SCENE,
        SETTINGS_SCENE
    }

    private static final HashMap<SceneNames, MyScene> _scenes = new HashMap<>();

    private static MyScene _currentScene;

    public static void init() {
        _scenes.put(SceneNames.FIRST_SCENE, new FirstScene());
        _scenes.put(SceneNames.LOGIN_SCENE, new LoginScene());
        _scenes.put(SceneNames.REGISTER_SCENE, new RegisterScene());
        _scenes.put(SceneNames.CHOOSE_SCENE, new ChooseScene());
        _scenes.put(SceneNames.JOIN_SCENE, new JoinScene());
        _scenes.put(SceneNames.GAME_SCENE, new GameScene());
        _scenes.put(SceneNames.SETTINGS_SCENE, new SettingsScene());
        _currentScene = _scenes.get(SceneNames.FIRST_SCENE);
    }

    public static void loadScene(SceneNames sceneName) {
        System.out.println("Loading scene: " + sceneName.name());

        MyScene nextScene = _scenes.get(sceneName);
        if (nextScene == null) {
            System.err.println("Loading scene failed");
            return;
        }
        _currentScene = nextScene;
        _currentScene.onDraw();
    }
}
