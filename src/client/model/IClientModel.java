package client.model;

import client.view.IObserver;

public interface IClientModel {
    void connect();
    
    
    void sendName(String name);
    void sendFileListRequest();
    void sendFileContentRequest(String filename);
    void sendRangesAndLock(String start, String end);
    void sendUnlocking();
    void sendSaveRequest(String content);
    
    
    String getId();
    String getPrevFilename();
    String getFileList();
    String getFileContent();
    String getLockedPart1();
    String getUnlockedPart();
    String getLockedPart2();
    void addObserver(IObserver o);
}
