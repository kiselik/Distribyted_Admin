package client.controller;

//паттерн синглетон, только один контроллер может быть у нас в прожке
public class BClientController {
    private static ClientController clientController = new ClientController();
    
    public static IClientController build() {
        return clientController;
    }
}
