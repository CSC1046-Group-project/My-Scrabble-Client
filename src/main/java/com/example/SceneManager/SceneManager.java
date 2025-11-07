package com.example.SceneManager;

import java.util.HashMap;

import com.example.SceneManager.Scenes.FirstScene;

public class SceneManager {

    public static enum SceneNames {
        FIRST_SCENE
    }

    private static final HashMap<SceneNames, MyScene> _scenes = new HashMap<>();

    private static MyScene _currentScene;

    public static void init() {
        _scenes.put(SceneNames.FIRST_SCENE, new FirstScene());
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
