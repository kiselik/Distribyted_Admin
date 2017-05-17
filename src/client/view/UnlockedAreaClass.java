package client.view;

import client.controller.BClientController;
import client.controller.IClientController;
import javax.swing.JTextArea;

public class UnlockedAreaClass extends JTextArea implements IObserver {

    @Override
    public void invalidUsername() {
    }

    @Override
    public void invalidRange() {
    }

    public UnlockedAreaClass() {
        IClientController clientController = BClientController.build();
        clientController.addObserver(this);
    }

    @Override
    public void updateId() {
    }

    @Override
    public void updatePrevFilename() {
    }

    @Override
    public void updateFileList() {
        IClientController clientController = BClientController.build();
        String fileList = clientController.getFileList();
        this.setText(fileList);
        this.setEditable(false);
    }

    @Override
    public void updateFileContent() {
        IClientController clientController = BClientController.build();
        String content = clientController.getFileContent();
        this.setText(content);
        this.setEditable(false);
    }

    @Override
    public void updateSavingState() {
    }

    @Override
    public void updateRangesState() {
        IClientController clientController = BClientController.build();
        String unlockedPart = clientController.getUnlockedPart();
        this.setText(unlockedPart);
        this.setEditable(true);
    }
}
