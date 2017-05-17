package client.model;

import client.view.IObserver;
import client.view.MainClientFrame;
import server.model.ClientThread;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientModel implements IClientModel {
    private InetAddress ip = null;
    private Socket cs;
    private DataInputStream dis;
    private DataOutputStream dos;
    private String id;
    private String fileList = "";
    private String fileContent = "";
    // String filename = "";
    private String prevFilename = "";
    private String start, end;
    private String lockedPart1, unlockedPart, lockedPart2;
    //  String message;//Строка, содержащая сообщение
    private boolean f = true;
    //   boolean flag = false;//Указывает на то, была передача данных, или нет

    private ArrayList<IObserver> observers = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getPrevFilename() {
        return prevFilename;
    }

    @Override
    public String getFileList() {
        return fileList;
    }

    @Override
    public String getFileContent() {
        return fileContent;
    }

    @Override
    public String getLockedPart1() {
        return lockedPart1;
    }

    @Override
    public String getUnlockedPart() {
        return unlockedPart;
    }

    @Override
    public String getLockedPart2() {
        return lockedPart2;
    }

    public void connect() {
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int port = 3124;
            cs = new Socket(ip, port);
            System.out.println("Client start \n");
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);

            //Получаем идентификатор
            id = dis.readUTF();
            updateIdObs(); // Обновляем view, подписанные на id
            System.out.println("Connected and ID loaded:" + id);

            //Создаем "обработчик" завершения сервера
            new Thread(() -> {
                while (f) {
                    try {
                        String s;
                        try {
                            s = dis.readUTF();
                        } catch (EOFException ex1) {
                            break;
                        }
                        if (s.equals("Server stopped")) {
                            f = false;
                            sendStop();
                            cs.close();
                            System.exit(1);
                        }


                        if (s.equals("Ok!Previous filename sending.")) {
                            getPrevFilenameFromServer();
                            updatePrevFilenameObs();
                        }

                        if (s.equals("Error! Failed filename sending!")) {
                            invalidUsername();
                        }


                        if (s.equals("File list sending")) {
                            getFileListFromServer();
                            updateFileListObs();
                        }

                        if (s.equals("File content sending.")) {
                            getFileContentFromServer();
                            updateFileContentObs();
                        }

                        if (s.equals("File saved successfully")) {
                            updateSavingStateObs();
                        }

                        if (s.equals("Ranges was set successfully")) {
                            loadFileSeparate();
                            updateRangesStateObs();
                        }
                        if (s.equals("Error with setting ranges")) {
                            invalidRange();
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

    @Override
    public void sendName(String name) {
        try {
            dos.writeUTF("Name sending");
            dos.writeUTF(name);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getPrevFilenameFromServer() {
        try {
            prevFilename = dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadFileSeparate() {
        int start_ = Integer.parseInt(start);
        int end_ = Integer.parseInt(end);
        String[] TextData = new String[3];
        String[] ArrayContent = "\n".split(fileContent);
        for (int i = 0; i < 3; i++) {
            TextData[i] = "";
        }
        int switcher = 0;
        for (int i = 0; i < ArrayContent.length; i++) {
            if (i == start_ - 1) {
                switcher++;
            }
            if (i == end_) {
                switcher++;
            }
            TextData[switcher] += ArrayContent[i] + "\n";
        }
        TextData[1] = TextData[1].substring(0, TextData[1].length() - 1);
        lockedPart1 = TextData[0];
        unlockedPart = TextData[1];
        lockedPart2 = TextData[2];
    }

    @Override
    public void sendFileListRequest() {
        try {
            dos.writeUTF("Get list of files.");
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendFileContentRequest(String filename) {
        try {
            dos.writeUTF("Get file content");
            dos.writeUTF(filename);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int countLines(String str) {
        String[] lines = ("\r\n" +
                "|\r|\n").split(str);
        return lines.length;
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Нумерация строк с единицы, ноль в стартовом диапазоне недопустим
    private boolean testRange() {
        int start_ = -1;
        int end_ = -1;
        if (tryParseInt(start) && tryParseInt(end)) {
            start_ = Integer.parseInt(start);
            end_ = Integer.parseInt(end);
        }

        int linesCount = countLines(fileContent);
        return !(start_ <= 0 || end_ <= 0 || start_ > end_ || start_ > linesCount || end_ > linesCount);
    }

    @Override
    public void sendRangesAndLock(String start, String end) {
        try {
            this.start = start;
            this.end = end;
            if (!testRange()) {
                invalidRange();
                return;
            }
            //Отправляем выбранный диапазон
            dos.writeUTF("Ranges sending");
            dos.writeUTF(start);
            dos.writeUTF(end);
        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    public void sendUnlocking() {
        try {
            dos.writeUTF("Unlocking");
        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendStop() {
        try {
            dos.writeUTF("end");
        } catch (IOException ex) {
            Logger.getLogger(MainClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendSaveRequest(String content) {
        try {
            dos.writeUTF("File saving");
            dos.writeUTF(content);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getFileListFromServer() {
        try {
            fileList = dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getFileContentFromServer() {
        try {
            fileContent = dis.readUTF();
            lockedPart1 = "";
            lockedPart2 = "";
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void invalidUsername() {
        for (IObserver o : observers) {
            o.invalidUsername();
        }
    }

    private void invalidRange() {
        for (IObserver o : observers) {
            o.invalidRange();
        }
    }

    private void updateIdObs() {
        for (IObserver o : observers) {
            o.updateId();
        }
    }

    private void updatePrevFilenameObs() {
        for (IObserver o : observers) {
            o.updatePrevFilename();
        }
    }

    private void updateFileListObs() {
        for (IObserver o : observers) {
            o.updateFileList();
        }
    }

    private void updateFileContentObs() {
        for (IObserver o : observers) {
            o.updateFileContent();
        }
    }

    private void updateSavingStateObs() {
        for (IObserver o : observers) {
            o.updateSavingState();
        }
    }

    private void updateRangesStateObs() {
        for (IObserver o : observers) {
            o.updateRangesState();
        }
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }
}
