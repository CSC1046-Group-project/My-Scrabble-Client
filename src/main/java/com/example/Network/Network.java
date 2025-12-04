package com.example.Network;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.example.Network.Protocol.ProtocolFactory;
import com.example.Network.Protocol.ProtocolMessage;

public class Network {

    private static final String URL = "ws://localhost:8080";
    private static WebSocketClient _client;
    private static Listener _listener;

    private static java.util.Timer _ping = new java.util.Timer();

    public static void run() {
        try {
            _client = new WebSocketClient(new URI(URL)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected to Scrabble server!");

                    _ping.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            if (_client.isClosed())
                                _ping.cancel();

                            Network.sendMessage(ProtocolFactory.ping());
                        }
                    }, 0, 20000);
                }

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

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Connection closed: " + reason);
                    _ping.cancel();
                }

                @Override
                public void onError(Exception ex) {
                    System.err.println("Error: " + ex);
                }
            };
            _client.connect();
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
    }

    public static void close() {
        if (_client != null && _client.isOpen()) {
            _client.close();
        }
    }

    public static void sendMessage(ProtocolMessage message) {
        if (_client != null && _client.isOpen()) {
            _client.send(message.serialize());
        } else {
            System.err.println("Error: Websocket is not connected. Try to reconnect...");
            run();
        }
    }

    public static void setListener(Listener listener) {
        _listener = listener;
    }
}
