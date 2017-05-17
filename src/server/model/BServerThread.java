package server.model;

//синглетон
public class BServerThread {
    private static ServerThread serverThread = new ServerThread();
    public  static IServerThread build()
    {
        return serverThread;
    }
    
}
