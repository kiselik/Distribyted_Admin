
package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JTextArea;

public class LockedArea2Class extends JTextArea implements IObserver {

    public LockedArea2Class() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updateRangesState() {
        IClientController clientController = BClientController.build();
        String lockedPart2 = clientController.getLockedPart2();
        this.setText(lockedPart2);
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
