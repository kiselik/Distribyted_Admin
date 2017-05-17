package server.controller;

import server.model.IObserver;

public interface IServerController {
    void start();
    void stop();

    String getLastConnectedFilename();
    String getLastConnectedUsername();
    String getLastConnectedStartRange();
    String getLastConnectedEndRange();
    String getLastConnectedId();
    String getLastDisconnectedFilename();
    String getLastDisconnectedUsername();

    void addObserver(IObserver o);
}
