package client.model;

public class BClientModel {
    private static ClientModel clientModel = new ClientModel();
    
    public  static IClientModel build() {
        return clientModel;
    }
}
