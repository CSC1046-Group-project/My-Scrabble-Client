package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("MyScrabble Client");
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 960, 540);
        scene.setFill(Color.web("0x16161E"));

        stage.setScene(scene);
        stage.show();
    }
}
