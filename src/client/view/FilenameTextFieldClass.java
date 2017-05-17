package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JTextField;

public class FilenameTextFieldClass extends JTextField implements IObserver{


    public FilenameTextFieldClass() {
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
        this.setEnabled(true);
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        this.setEnabled(false);
    }
}
