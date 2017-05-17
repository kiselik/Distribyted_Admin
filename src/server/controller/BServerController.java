package server.controller;

public class BServerController {
    private static ServerController serverController = new ServerController();
    
    public static IServerController build() {
        return serverController;
    }
}
