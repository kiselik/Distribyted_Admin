package server.model;

public interface IObserver {
    void updateLastConnected();
    void updateLastDisconnected();
}
