package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JLabel;


public class PrevFilenameLabelClass extends JLabel implements IObserver {
    
    private String prevFilename;

    public PrevFilenameLabelClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updatePrevFilename() {
        IClientController clientController = BClientController.build();
        prevFilename = clientController.getPrevFilename();
        this.setText(prevFilename);
    }

    @Override
    public void invalidUsername() {
        prevFilename = "Invalid username";
        this.setText(prevFilename);
    }

    @Override
    public void invalidRange() {
    }


    @Override
    public void updateId() {
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
