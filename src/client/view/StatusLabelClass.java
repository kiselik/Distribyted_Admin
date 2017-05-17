package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JLabel;

public class StatusLabelClass extends JLabel implements IObserver {

    private String status;
    public StatusLabelClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void invalidUsername() {
        status = "Please, choose another username";
        this.setText(status);
    }

    @Override
    public void invalidRange() {
        status = "Invalid range, please, choose another range";
        this.setText(status);  
    }

    @Override
    public void updateId() {
        status = "Id successfully loaded";
        this.setText(status);
    }

    @Override
    public void updatePrevFilename() {
        status = "Logged in, previous filename successfully loaded";
        this.setText(status);
    }

    @Override
    public void updateFileList() {
        status = "List of files successfully loaded";
        this.setText(status);
    }

    @Override
    public void updateFileContent() {
        status = "File content loaded. Ready to lock";
        this.setText(status);
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        status = "Ranges set successfully. Lock activated";
        this.setText(status);
    }
}
