package client.view;

import client.controller.BClientController;
import client.controller.IClientController;

import javax.swing.JTextArea;


public class LockedArea1Class extends JTextArea implements IObserver {

    public LockedArea1Class() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updateRangesState() {
        IClientController clientController = BClientController.build();
        String lockedPart1 = clientController.getLockedPart1();
        this.setText(lockedPart1);
    }

    @Override
    public void invalidUsername() {
    }

    @Override
    public void invalidRange() {
    }

    @Override
    public void updateId() {
    }

    @Override
    public void updatePrevFilename() {
    }

    @Override
    public void updateFileList() {
    }

    @Override
    public void updateFileContent() {
        this.setText("");
    }

    @Override
    public void updateSavingState() {
    }

}
