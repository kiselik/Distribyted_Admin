package server.controller;

import server.model.BServerThread;
import server.model.FileElement;
import server.model.IObserver;
import server.model.IServerThread;


public class ServerController implements IServerController{

    private IServerThread serverThread;

    @Override
    public String getLastConnectedFilename() {
        serverThread = BServerThread.build();
        return serverThread.getLastFilename();
    }

    @Override
    public String getLastDisconnectedFilename() {
        return getLastConnectedFilename();
    }


    @Override
    public String getLastConnectedUsername() {
        serverThread = BServerThread.build();
        FileElement lastConnected = serverThread.getLastFileElement();
        return  lastConnected.getUsername();
     }

    @Override
    public String getLastConnectedStartRange() {
        serverThread = BServerThread.build();
        FileElement lastConnected = serverThread.getLastFileElement();
        return Integer.toString(lastConnected.getStart());
     }

    @Override
    public String getLastConnectedEndRange() {
        serverThread = BServerThread.build();
        FileElement lastConnected = serverThread.getLastFileElement();
        return Integer.toString(lastConnected.getEnd());
     }

    @Override
    public String getLastConnectedId() {
        serverThread = BServerThread.build();
        FileElement lastConnected = serverThread.getLastFileElement();
        return lastConnected.getUUID().toString();
     }

    @Override
    public String getLastDisconnectedUsername() {
        return getLastConnectedUsername();
    }

    @Override
    public void start() {
        serverThread = BServerThread.build();
        serverThread.start();
    }

    @Override
    public void stop() {
        serverThread = BServerThread.build();
        serverThread.stopServer();
    }

    @Override
    public void addObserver(IObserver o) {
        serverThread = BServerThread.build();
        serverThread.addObserver(o);
    }

}
