package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JButton;

public class UsernameButtonClass  extends JButton implements IObserver {

    public UsernameButtonClass() {
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
    public void updateId() {
    }

    @Override
    public void updatePrevFilename() {
        this.setEnabled(false);
    }

    @Override
    public void updateFileList() {
    }

    @Override
    public void updateFileContent() {
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
    }
}
