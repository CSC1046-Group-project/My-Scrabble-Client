package com.example.Network;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.example.Network.Protocol.ProtocolFactory;
import com.example.Network.Protocol.ProtocolMessage;

// Network class to receive and send messages to the server
public class Network {

    private static final String URL = "ws://localhost:8080";    // Server url
    private static WebSocketClient _client;                     // CLient socket
    private static Listener _listener;                          // Listener for callback based on message type

    private static java.util.Timer _ping = new java.util.Timer();   // Timer to ping the server every 20sec to keep the connection

    // Run the client connection
    public static void run() {
        try {
            // Create the client socket
            _client = new WebSocketClient(new URI(URL)) {

                // On connection with the server
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to Scrabble server!");

                    // Init the ping timer
                    _ping.scheduleAtFixedRate(new TimerTask() {

                        // callback every 20sec
                        @Override
                        public void run() {
                            if (_client.isClosed())
                                _ping.cancel();

                            // Send the ping to the server
                            Network.sendMessage(ProtocolFactory.ping());
                        }
                    }, 0, 20000);
                }

                // On message from the server, call the listener callback
                @Override
                public void onMessage(String message) {
                    try {
                        ProtocolMessage parsedMessage = ProtocolMessage.parse(message);
                        if (_listener != null) {
                            _listener.listen(parsedMessage);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                }

                // If the server close
                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Connection closed: " + reason);
                    _ping.cancel();
                }

                // On error from the server
                @Override
                public void onError(Exception ex) {
                    System.err.println("Error: " + ex);
                }
            };
            // connect the client to the server
            _client.connect();
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
    }

    // CLosee the connection
    public static void close() {
        if (_client != null && _client.isOpen()) {
            _client.close();
        }
    }

    // Send a message to the server
    public static void sendMessage(ProtocolMessage message) {
        if (_client != null && _client.isOpen()) {
            _client.send(message.serialize());
        } else {
            System.err.println("Error: Websocket is not connected. Try to reconnect...");
            run();
        }
    }

    // Set the current message listener
    public static void setListener(Listener listener) {
        _listener = listener;
    }
}
