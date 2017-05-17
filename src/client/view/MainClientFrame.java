/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import server.model.ClientThread;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainClientFrame extends javax.swing.JFrame {
    private InetAddress ip = null;
    private Socket cs;                  //сокет
    private DataInputStream dis;        //входной поток данных
    private DataOutputStream dos;       //выходной поток данных
    private String FileContent;         //содержимое всего файла
    // String filename = "";       //имя файла
    // String short_filename = ""; //?????
    private String short_prev_filename = "";
    private String start, end;
    // String message;//Строка, содержащая сообщение
    boolean flag = true;//Указывает на то, была передача данных, или нет

    //вызов initComponents()
    private MainClientFrame() {
        initComponents();
    }

    //начальная инициализация переменных
    private void initComponents() {

        javax.swing.JMenu jMenu1 = new javax.swing.JMenu();
        javax.swing.JMenu jMenu2 = new javax.swing.JMenu();
        javax.swing.JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu jMenu3 = new javax.swing.JMenu();
        javax.swing.JMenu jMenu4 = new javax.swing.JMenu();
        FilenameField = new javax.swing.JTextField();
        LockButton = new javax.swing.JButton();
        StartField = new javax.swing.JTextField();
        EndField = new javax.swing.JTextField();
        IdLabel = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        LockedArea1 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        LockedArea2 = new javax.swing.JTextArea();
        javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        UnlockedArea = new javax.swing.JTextArea();
        FileOpenButton = new javax.swing.JButton();
        StatusLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        NameField = new javax.swing.JTextField();
        NameButton = new javax.swing.JButton();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        PreviousFileLabel = new javax.swing.JLabel();
        UnlockButton = new javax.swing.JButton();
        GetFileListButton = new javax.swing.JButton();

        jMenu1.setText("jMenu1");
        jMenu2.setText("jMenu2");
        jMenu3.setText("File");
        jMenuBar1.add(jMenu3);
        jMenu4.setText("Edit");
        jMenuBar1.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }

            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        FilenameField.setEditable(false);
        FilenameField.setText("Path to file");
        FilenameField.addActionListener(this::FilenameFieldActionPerformed);

        LockButton.setText("Lock");
        LockButton.setEnabled(false);
        LockButton.addActionListener(this::LockButtonActionPerformed);

        StartField.setEditable(false);
        StartField.setText("Start");

        EndField.setEditable(false);
        EndField.setText("End");
        EndField.addActionListener(this::EndFieldActionPerformed);

        IdLabel.setText("Идентификатор");

        LockedArea1.setEditable(false);
        LockedArea1.setColumns(20);
        LockedArea1.setRows(5);
        LockedArea1.setSelectionEnd(1);
        LockedArea1.setSelectionStart(3);
        jScrollPane2.setViewportView(LockedArea1);

        LockedArea2.setEditable(false);
        LockedArea2.setColumns(20);
        LockedArea2.setRows(5);
        jScrollPane3.setViewportView(LockedArea2);

        UnlockedArea.setEditable(false);
        UnlockedArea.setColumns(20);
        UnlockedArea.setRows(5);
        jScrollPane5.setViewportView(UnlockedArea);

        FileOpenButton.setText("Open");
        FileOpenButton.setEnabled(false);
        FileOpenButton.addActionListener(this::FileOpenButtonActionPerformed);

        StatusLabel.setText("Idle");

        SaveButton.setText("Save");
        SaveButton.setEnabled(false);
        SaveButton.addActionListener(this::SaveButtonActionPerformed);

        NameField.setText("Name");

        NameButton.addActionListener(this::NameButtonActionPerformed);

        jLabel1.setText("Previous file:");

        PreviousFileLabel.setText("Unknown");

        UnlockButton.setText("Unlock");
        UnlockButton.setEnabled(false);
        UnlockButton.addActionListener(this::UnlockButtonActionPerformed);

        GetFileListButton.setText("Get list of files");
        GetFileListButton.setEnabled(false);
        GetFileListButton.addActionListener(this::GetFileListButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(IdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(StatusLabel)
                                .addGap(123, 123, 123)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(PreviousFileLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                                                        .addComponent(jScrollPane3)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(GetFileListButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(FilenameField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(FileOpenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(StartField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(EndField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LockButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(UnlockButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SaveButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(NameButton)))
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                                        .addContainerGap()))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(FilenameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(LockButton)
                                                .addComponent(EndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(StartField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(FileOpenButton)
                                                .addComponent(SaveButton)
                                                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(UnlockButton)
                                                .addComponent(GetFileListButton))
                                        .addComponent(NameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(126, 126, 126)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(IdLabel)
                                        .addComponent(StatusLabel)
                                        .addComponent(jLabel1)
                                        .addComponent(PreviousFileLabel)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(256, Short.MAX_VALUE)))
        );

        pack();
    } //initComponents

    //обработка встречи конца файла
    private void EndFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }

    //обработка кнопки "Lock"
    private void LockButtonActionPerformed(java.awt.event.ActionEvent evt) {
        start = StartField.getText(); //откуда начинаем блочить
        end = EndField.getText();       //докуда лочим
        int start_ = Integer.parseInt(start);
        int end_ = Integer.parseInt(end);

        //если за диапазоном, то fail.
        if (start_ < 0 || start_ > end_) {
            StatusLabel.setText("Error! Please choose other ranges!");
            return;
        }
        try {
            //Отправляем выбранный диапазон
            dos.writeUTF("Ranges sending");
            dos.writeUTF(start);
            dos.writeUTF(end);
        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }


    //    разбиваем полученный от сервера текст на 3 части:
    //    LockedArea1 - от начала и до заблокированной части
    //    UnlockedArea - нами заблокировано - нам доступно для редактирования
    //    LockedArea2  - после заблокированной части и до конца
    private void LoadFileSeparate() {
        int start_ = Integer.parseInt(start);
        int end_ = Integer.parseInt(end);
        String[] TextData = new String[3]; //состоит из 3 частей (доблокированной, блокированной и послеблокированной
        String[] ArrayContent = FileContent.split("\n"); //добавляем к концу возврат каретки
        for (int i = 0; i < 3; i++)
            TextData[i] = "";
        int switcher = 0; //переключает в какой массив распарсить текст
        for (int i = 0; i < ArrayContent.length; i++) {
            if (i == start_ - 1)
                switcher++;
            if (i == end_)
                switcher++;
            TextData[switcher] += ArrayContent[i] + "\n";
        }
        TextData[1] = TextData[1].substring(0, TextData[1].length() - 1);
        LockedArea1.setText(TextData[0]);
        UnlockedArea.setText(TextData[1]);
        LockedArea2.setText(TextData[2]);
    }

    //обработка клавиши выбора файла
    private void FileOpenButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            dos.writeUTF("Get file content");// говорим, что хотим получить от сервера файл
            dos.writeUTF(FilenameField.getText());// передаём название файла, который мы хотим получить
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //отправляем на сервер изменённый фрагмент
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int start_ = Integer.parseInt(start);
        int end_ = Integer.parseInt(end);
        if (UnlockedArea.getLineCount() != end_ - start_ + 1) { //если не изменилось количество строк
            StatusLabel.setText("Saving Error! Invalid lines count.");
            return;
        }
        try {
            dos.writeUTF("File saving"); //говорим, что будем сохранять файл
            dos.writeUTF(UnlockedArea.getText()); // текст для сохранения
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // отправка имени
    private void NameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            dos.writeUTF("Name sending");
            dos.writeUTF(NameField.getText());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //для чего это?)
    private void GetPrevFilename() {
        try {
            short_prev_filename = dis.readUTF();            // получаем имя файла
            PreviousFileLabel.setText(short_prev_filename); //
            NameField.setEditable(false);                   //
            NameButton.setEnabled(false);
            StartField.setEditable(true);
            EndField.setEditable(true);
            LockButton.setEnabled(true);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // получение списка файлов
    private void GetFileList() {
        try {
            String FileList = dis.readUTF(); //получаем от сервера список файлов доступных для редактирования
            UnlockedArea.setText(FileList);
            FileOpenButton.setEnabled(true); //можно открывать файл.
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //получаем содержимое файла
    private void GetFileContent() {
        try {
            FileContent = dis.readUTF();
            UnlockedArea.setText(FileContent); //смотрим файл, но не лочим??????
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Connect to server and get UUID onCreate
    //обрабатываем команды, пришедшие от сервера
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        try {
            ip = InetAddress.getLocalHost(); //получаем IP
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            int port = 3124;
            cs = new Socket(ip, port);
            System.out.println("Client start \n"); //зашёл клиент
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);

            //Получаем идентификатор
            String id = dis.readUTF();
            IdLabel.setText(id);
            //подключились и получили id.
            System.out.println("Connected and ID loaded:" + id);

            //Создаем "обработчик" сообщений, пришедших с сервера
            new Thread(() -> {

                boolean f = true;
                while (f) {
                    try {
                        String s = dis.readUTF();
                        //  в зависимости от того, что нам пришло в "s" от сервера, будем делать следущее:

                        //сворачиваемся
                        if (s.equals("Disconnected")) {
                            f = false;
                            cs.close();
                            IdLabel.setText("Server stopped");
                            LockButton.setEnabled(false);
                            GetFileListButton.setEnabled(false);
                            FileOpenButton.setEnabled(false);
                            SaveButton.setEnabled(false);
                            UnlockButton.setEnabled(false);
                            LockedArea1.setText("");
                            LockedArea2.setText("");
                            UnlockedArea.setText("Server stopped!!!!");
                            //System.exit(1);
                        }

                        //хз че делается
                        if (s.equals("Ok!Previous filename sending.")) {
                            GetPrevFilename();
                            GetFileListButton.setEnabled(true);
                            StatusLabel.setText("Previous filename loaded successfully");
                            PreviousFileLabel.setText(short_prev_filename);
                        }

                        //нет такого файла
                        if (s.equals("Error! Failed filename sending!")) {
                            StatusLabel.setText("Please choose other name!");
                        }

                        // открыть список файлов
                        if (s.equals("File list sending")) {
                            GetFileList();
                            FilenameField.setEditable(true);
                            FileOpenButton.setEnabled(true);
                            StatusLabel.setText("File list loaded");
                        }

                        // отправляем текст файла
                        if (s.equals("File content sending.")) {
                            GetFileContent();
                            FileOpenButton.setEnabled(false);
                            LockButton.setEnabled(true);
                            StartField.setEditable(true);
                            EndField.setEditable(true);
                        }

                        //успешно сохранённый файл
                        if (s.equals("File saved successfully")) {
                            UnlockedArea.setEditable(false);
                            UnlockButton.setEnabled(false);
                            SaveButton.setEnabled(false);
                            LockButton.setEnabled(true);
                            FileOpenButton.setEnabled(true);
                            GetFileListButton.setEnabled(true);
                            LockedArea1.setText("");
                            LockedArea2.setText("");
                        }

                        // диапазон выбран успешно
                        if (s.equals("Ranges was set successfully")) {
                            LoadFileSeparate();
                            FileOpenButton.setEnabled(false);
                            GetFileListButton.setEnabled(false);
                            LockButton.setEnabled(false);
                            UnlockButton.setEnabled(true);
                            SaveButton.setEnabled(true);
                            UnlockedArea.setEditable(true);
                            StatusLabel.setText("Ranges was set successfully");
                        }
                        //диапазон выбран не успешно
                        if (s.equals("Error with setting ranges")) {
                            StatusLabel.setText("Error with setting ranges");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void FilenameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilenameFieldActionPerformed
    }

    //обработка клавиши Unlock
    private void UnlockButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            UnlockedArea.setEditable(false); //больше не редактируем
            UnlockButton.setEnabled(false);  // не можем дважды разлочить
            SaveButton.setEnabled(false);   // не можем сохранить
            LockButton.setEnabled(true);    // можем снова залочить
            FileOpenButton.setEnabled(true);   //можем открыть новый файл
            GetFileListButton.setEnabled(true);// можем попросить заново список файлов
            dos.writeUTF("Unlocking");
            //      LoadFile();
            LockedArea1.setText(""); //опустошаем заблокированную область
            LockedArea2.setText(""); // опустошаем заблокированную область

        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //закрытие формы (+ проверка: редактируется ли текст сейчас)
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        try {
            if (UnlockButton.isEnabled()) {
                dos.writeUTF("Unlocking");
            }
            dos.writeUTF("Remove name");
            dos.writeUTF("end");
        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //просим у сервера список всех доступных для редактирования файлов
    private void GetFileListButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            dos.writeUTF("Get list of files.");
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MainClientFrame().setVisible(true));
    }

    private javax.swing.JTextField EndField;
    private javax.swing.JButton FileOpenButton;
    private javax.swing.JTextField FilenameField;
    private javax.swing.JButton GetFileListButton;
    private javax.swing.JLabel IdLabel;
    private javax.swing.JButton LockButton;
    private javax.swing.JTextArea LockedArea1;
    private javax.swing.JTextArea LockedArea2;
    private javax.swing.JButton NameButton;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel PreviousFileLabel;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField StartField;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JButton UnlockButton;
    private javax.swing.JTextArea UnlockedArea;

}
