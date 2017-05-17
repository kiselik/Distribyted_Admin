package server.model;

public interface IServerThread {
    void start();
    void stopServer();
    FileElement getLastFileElement();
    String getLastFilename();
    void sendToAll();
    void addObserver(IObserver o);
}
