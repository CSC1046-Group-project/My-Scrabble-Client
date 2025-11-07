package com.example;

// import java.net.URI;

// import org.java_websocket.client.WebSocketClient;
// import org.java_websocket.handshake.ServerHandshake;

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.BorderPane;
// import javafx.stage.Stage;

public class App extends Application {

    // private WebSocketClient client;

    // @Override
    // public void start(Stage stage) {
    //     stage.setTitle("Scrabble Client");

    //     BorderPane root = new BorderPane();
    //     Button connectButton = new Button("Connect to Server");

    //     connectButton.setOnAction(e -> connectToServer());
    //     root.setCenter(connectButton);

    //     Scene scene = new Scene(root, 400, 300);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // private void connectToServer() {
    //     try {
    //         String url = "wss://my-scrabble-backend.onrender.com";

    //         client = new WebSocketClient(new URI(url)) {
    //             @Override
    //             public void onOpen(ServerHandshake handshake) {
    //                 System.out.println("Connected to Scrabble server!");
    //             }

    //             @Override
    //             public void onMessage(String message) {
    //                 System.out.println("Message: " + message);
    //             }

    //             @Override
    //             public void onClose(int code, String reason, boolean remote) {
    //                 System.out.println("Connection closed: " + reason);
    //             }

    //             @Override
    //             public void onError(Exception ex) {
    //                 ex.printStackTrace();
    //             }
    //         };
    //         client.connect();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {

        Application myApp = new Application();

        launch(args);
    }
}
