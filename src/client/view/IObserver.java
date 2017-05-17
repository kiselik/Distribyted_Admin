
package client.view;

public interface IObserver {
    void updateId();
    void updatePrevFilename();
    void updateFileList();
    void updateFileContent();
    void updateSavingState();
    void updateRangesState();
    void invalidUsername();
    void invalidRange();
}
