package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JButton;


public class UnlockSaveButtonClass   extends JButton implements IObserver {

    public UnlockSaveButtonClass() {
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
    }

    @Override
    public void updateFileList() {
    }

    @Override
    public void updateFileContent() {
        this.setEnabled(false);
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        boolean enabled = this.isEnabled();
        this.setEnabled(!enabled);
    }

}
