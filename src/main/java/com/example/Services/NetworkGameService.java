package com.example.Services;

import com.example.Interfaces.IGameService;
import com.example.Network.Network;
import com.example.Network.Protocol.ProtocolFactory;

public class NetworkGameService implements IGameService{

    @Override
    public void ready(String token, String roomId) {
        Network.sendMessage(ProtocolFactory.ready(token, roomId));
    }
}
