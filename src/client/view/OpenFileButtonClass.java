package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JButton;

public class OpenFileButtonClass extends JButton implements IObserver  {

    public OpenFileButtonClass() {
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
        this.setEnabled(true);
    }

    @Override
    public void updateFileContent() {
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        this.setEnabled(false);
    }
}
