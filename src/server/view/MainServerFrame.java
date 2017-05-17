package server.view;

import server.controller.BServerController;
import server.controller.IServerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainServerFrame extends javax.swing.JFrame {

    //запускаем сервер
    private synchronized void start() {
        IServerController serverController = BServerController.build();
        serverController.start();
        StartButton.setEnabled(false);
    }
    //вырубаем сервер
    private synchronized void stop() {
        IServerController serverController = BServerController.build();
        serverController.stop();
        StartButton.setEnabled(true);
    }

    private MainServerFrame() {
        initComponents();
    }

    //инициализация полей по умолчанию
    private void initComponents() {

        StartButton = new javax.swing.JButton();
        javax.swing.JButton stopButton = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        clientsTable = new server.view.ClientsTableClass();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        StartButton.setText("Start");
        StartButton.addActionListener(this::StartButtonActionPerformed);

        stopButton.setText("Stop");
        stopButton.addActionListener(this::StopButtonActionPerformed);

        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Filename", "Ranges", "Username", "UUID"
                }
        ));
        jScrollPane1.setViewportView(clientsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(StartButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(stopButton)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                )
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 9, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(StartButton)
                                        .addComponent(stopButton))
                                //.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    //обработка кнопки старт
    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {
        start();
    }

    //обработка кнопки стоп
    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        model.setRowCount(0);
        stop();
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
            java.util.logging.Logger.getLogger(MainServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainServerFrame().setVisible(true));
    }
    private javax.swing.JButton StartButton;
    private server.view.ClientsTableClass clientsTable;
}
