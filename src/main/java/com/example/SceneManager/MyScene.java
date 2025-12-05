package com.example.SceneManager;

import com.example.RendereUI.RendererUI;

import javafx.scene.layout.StackPane;

// Scene abstract class to handle all scenes
public abstract class MyScene {

    protected StackPane _root;      // root window

    // init the root
    public MyScene () {
        _root = new StackPane();
    }

    // Draw the scene
    public void onDraw() {
        initListener();
        RendererUI.draw(_root);
    }

    // Add a listener to the network for the current scene
    public abstract void initListener();
}
