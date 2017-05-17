package client.view;

import client.controller.BClientController;
import client.controller.IClientController;

public class MainClientFormMVC extends javax.swing.JFrame {

    //в новом потоке открыть форму для потока
    private MainClientFormMVC() {
        initComponents();
        IClientController clientController = BClientController.build();
        clientController.connect();
    }

    //начальная инициализация
    private void initComponents() {

        FileListButtonClass fileListButton = new FileListButtonClass();
        IdLabelClass idLabel = new IdLabelClass();
        StatusLabelClass statusLabel = new StatusLabelClass();
        PrevFilenameLabelClass prevFilenameLabel = new PrevFilenameLabelClass();
        javax.swing.JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        unlockedArea = new client.view.UnlockedAreaClass();
        UsernameButtonClass usernameButton = new UsernameButtonClass();
        usernameField = new client.view.UsernameTextField();
        OpenFileButtonClass openFileButton = new OpenFileButtonClass();
        filenameField = new client.view.FilenameTextFieldClass();
        startField = new client.view.RangeTextField();
        endField = new client.view.RangeTextField();
        LockButtonClass lockButton = new LockButtonClass();
        UnlockSaveButtonClass unlockButton = new UnlockSaveButtonClass();
        UnlockSaveButtonClass saveButton = new UnlockSaveButtonClass();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        LockedArea1Class lockedArea1Class1 = new LockedArea1Class();
        javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        LockedArea2Class lockedArea2Class1 = new LockedArea2Class();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileListButton.setText("Get list of files");
        fileListButton.setEnabled(false);
        fileListButton.addActionListener(this::fileListButtonActionPerformed);

        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idLabel.setText("Identifier");

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Status");

        prevFilenameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevFilenameLabel.setText("Previous filename");

        unlockedArea.setEditable(false);
        unlockedArea.setColumns(20);
        unlockedArea.setRows(5);
        jScrollPane4.setViewportView(unlockedArea);

        usernameButton.addActionListener(this::usernameButtonActionPerformed);

        usernameField.setText("Name");

        openFileButton.setText("Open");
        openFileButton.setEnabled(false);
        openFileButton.addActionListener(this::openFileButtonActionPerformed);

        filenameField.setText("Filename");
        filenameField.setEnabled(false);

        startField.setText("Start");
        startField.setEnabled(false);

        endField.setText("End");
        endField.setEnabled(false);

        lockButton.setText("Lock");
        lockButton.setEnabled(false);
        lockButton.addActionListener(this::lockButtonActionPerformed);

        unlockButton.setText("Unlock");
        unlockButton.setEnabled(false);
        unlockButton.addActionListener(this::unlockButtonActionPerformed);

        saveButton.setText("Save");
        saveButton.setEnabled(false);
        saveButton.addActionListener(this::SaveButtonActionPerformed);

        lockedArea1Class1.setEditable(false);
        lockedArea1Class1.setColumns(20);
        lockedArea1Class1.setRows(5);
        jScrollPane2.setViewportView(lockedArea1Class1);

        lockedArea2Class1.setEditable(false);
        lockedArea2Class1.setColumns(20);
        lockedArea2Class1.setRows(5);
        jScrollPane5.setViewportView(lockedArea2Class1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(prevFilenameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane2)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(fileListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(filenameField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(openFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(endField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(unlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(usernameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(usernameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(fileListButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(openFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(filenameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(endField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lockButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(unlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(prevFilenameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }

    //получить список доступных файлов
    private void fileListButtonActionPerformed(java.awt.event.ActionEvent evt) {
        IClientController clientController = BClientController.build();
        clientController.sendFileListRequest();
    }

    //изменить имя клиента
    private void usernameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        IClientController clientController = BClientController.build();
        clientController.sendName(usernameField.getText());
    }

    //открыть файл
    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        IClientController clientController = BClientController.build();
        clientController.sendFileContentRequest(filenameField.getText());
    }

    //залочить
    private void lockButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String start = startField.getText();
        String end = endField.getText();

        IClientController clientController = BClientController.build();
        clientController.sendRangesAndLock(start, end);
    }

    //раздлочить
    private void unlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
        IClientController clientController = BClientController.build();
        clientController.sendUnlocking();
    }

    //сохранить изменения
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        IClientController clientController = BClientController.build();
        clientController.sendSaveRequest(unlockedArea.getText());
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClientFormMVC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainClientFormMVC().setVisible(true));
    }

    private client.view.RangeTextField endField;
    private client.view.FilenameTextFieldClass filenameField;
    private client.view.RangeTextField startField;
    private client.view.UnlockedAreaClass unlockedArea;
    private client.view.UsernameTextField usernameField;
}
