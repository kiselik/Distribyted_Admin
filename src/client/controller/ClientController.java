package client.controller;

import client.model.BClientModel;
import client.model.IClientModel;
import client.view.IObserver;


public class ClientController implements IClientController {

    private IClientModel clientModel;

    @Override
    public void sendName(String name) {
        clientModel = BClientModel.build();
        clientModel.sendName(name);
    }


    @Override
    public void sendFileListRequest() {
        clientModel = BClientModel.build();
        clientModel.sendFileListRequest();
    }

    @Override
    public void sendFileContentRequest(String filename) {
        clientModel = BClientModel.build();
        clientModel.sendFileContentRequest(filename);
    }

    @Override
    public void sendRangesAndLock(String start, String end) {
        clientModel = BClientModel.build();
        clientModel.sendRangesAndLock(start, end);
    }

    @Override
    public void sendUnlocking() {
        clientModel = BClientModel.build();
        clientModel.sendUnlocking();
    }

    @Override
    public void sendSaveRequest(String content) {
        clientModel = BClientModel.build();
        clientModel.sendSaveRequest(content);
    }

    @Override
    public String getId() {
        clientModel = BClientModel.build();
        return clientModel.getId();
    }

    @Override
    public String getPrevFilename() {
        clientModel = BClientModel.build();
        return clientModel.getPrevFilename();
    }

    @Override
    public String getFileList() {
        clientModel = BClientModel.build();
        return clientModel.getFileList();
    }

    @Override
    public String getFileContent() {
        clientModel = BClientModel.build();
        return clientModel.getFileContent();
    }

    @Override
    public String getLockedPart1() {
        clientModel = BClientModel.build();
        return clientModel.getLockedPart1();
    }

    @Override
    public String getUnlockedPart() {
        clientModel = BClientModel.build();
        return clientModel.getUnlockedPart();
    }

    @Override
    public String getLockedPart2() {
        clientModel = BClientModel.build();
        return clientModel.getLockedPart2();
    }

    @Override
    public void addObserver(IObserver o) {
        clientModel = BClientModel.build();
        clientModel.addObserver(o);
    }

    @Override
    public void connect() {
        clientModel = BClientModel.build();
        clientModel.connect();
    }
}
