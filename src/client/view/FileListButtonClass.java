package client.view;

import client.controller.BClientController;
import client.controller.IClientController;

import javax.swing.JButton;

public class FileListButtonClass extends JButton implements IObserver {

    public FileListButtonClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void invalidUsername() {
    }

    @Override
    public void invalidRange() {
    }

    @Override
    public void updatePrevFilename() {
        this.setEnabled(true);
    }

    @Override
    public void updateId() {
    }

    @Override
    public void updateFileList() {
    }

    @Override
    public void updateFileContent() {
        this.setEnabled(true); // При unlock
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        this.setEnabled(false);
    }

}
