
package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JButton;


public class LockButtonClass  extends JButton implements IObserver {

    public LockButtonClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updateRangesState() {
        boolean enabled = this.isEnabled();
        this.setEnabled(!enabled);
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
        this.setEnabled(true);
    }

    @Override
    public void updateSavingState() {
    }
}
