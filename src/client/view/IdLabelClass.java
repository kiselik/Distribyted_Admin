package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JLabel;

public class IdLabelClass  extends JLabel implements IObserver {

    public IdLabelClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updateId() {
        IClientController clientController = BClientController.build();
        this.setText(clientController.getId());
    }

    @Override
    public void invalidUsername() {
    }

    @Override
    public void invalidRange() {
    }

    @Override
    public void updatePrevFilename() {
    }

    @Override
    public void updateFileList() {    }

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
