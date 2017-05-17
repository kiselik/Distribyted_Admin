package server.view;

import javax.swing.JTable;
import server.controller.BServerController;
import server.controller.IServerController;
import server.model.IObserver;

public class ClientsTableClass extends JTable implements IObserver {

    public ClientsTableClass() {
        IServerController serverController = BServerController.build();
        serverController.addObserver(this);
    }

    @Override
    public void updateLastConnected() {
        IServerController serverController = BServerController.build();
        String filename = serverController.getLastConnectedFilename();
        String username = serverController.getLastConnectedUsername();
        String start = serverController.getLastConnectedStartRange();
        String end = serverController.getLastConnectedEndRange();
        String id = serverController.getLastConnectedId();
        TableMethods.addToTable(this, filename, username, start, end, id);
    }

    @Override
    public void updateLastDisconnected() {
        IServerController serverController = BServerController.build();
        String filename = serverController.getLastDisconnectedFilename();
        String username = serverController.getLastDisconnectedUsername();
        TableMethods.delFromTable(this, username);
    }
    
}
