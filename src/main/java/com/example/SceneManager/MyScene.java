package com.example.SceneManager;

import com.example.RendereUI.RendererUI;

import javafx.scene.layout.StackPane;

public abstract class MyScene {

    protected StackPane _root;

    public MyScene () {
        _root = new StackPane();
        initRoot();
    }

    public void onDraw() {
        RendererUI.draw(_root);
    }

    public abstract void initRoot();
}
