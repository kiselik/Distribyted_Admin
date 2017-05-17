package server.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread {
    private ServerThread st;
    private UUID id = UUID.randomUUID();
    private DataInputStream dis;
    private DataOutputStream dos;
    // ArrayList file_elements;
    private String username;
    private String filename;
    private FileElement element; //FileElement состоит из UUID, потока клиента, диапазона и имени пользователя

    private boolean f = true;

    //получаем id обрабатываемого клиента
    UUID getUUID() {
        return id;
    }


    // получаем имя файла с которым работает клиент
    public String getFilename() {
        return filename;
    }


    //получаем диапазон, который залочил клиент
    //видимо, просто считываем с клмента, что же он залочил
    private void GetRanges() {
        try {
            String start = dis.readUTF();
            String end = dis.readUTF();
            Range tmp_range = new Range(Integer.parseInt(start), Integer.parseInt(end));
            element = new FileElement(id, this, username, tmp_range);
            st.putFileElement(filename, element);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //лочим файл для потока, который запросил ????????????
    private void Unlock() {
        st.extractFileElement(filename, element);
    }

    //если запрос был "Name sending" понять, что такое Names
    private void GetName() {
        try {
            username = dis.readUTF();

            if (st.Names == null) {
                st.Names = new ArrayList();
                st.Names.add(username);
                dos.writeUTF("Ok!Previous filename sending.");
                String prev_filename = GetFromDB(username);
                dos.writeUTF(prev_filename);

                //если не было до этого запроса имени в списке, до добавляем в список
            } else if (st.Names.indexOf(username) == -1) {
                st.Names.add(username);
                dos.writeUTF("Ok!Previous filename sending.");
                String prev_filename = GetFromDB(username);
                dos.writeUTF(prev_filename);
            }
                // если имя уже есть в списке
            else
                dos.writeUTF("Error! Failed filename sending!");
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //открываем каталог
    private String listFilesForFolder(final File folder) {
        String result = "";
        for (final File fileEntry : folder.listFiles()) {
            //если это ещё одна папка, то рекурсивно вызываем показать содержимое папки
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                //если обычный файл, то просто добавляем в список имя файла
                result += fileEntry.getName() + "\n";
            }
        }
        return result;
    }

    private String FileOpen(String FileName) {
        filename = FileName;
        String fullname = "Shared/" + FileName;
        FileReader fr = null;
        BufferedReader textReader = null;
        String result = "";
        try {
            fr = new FileReader(fullname);
            textReader = new BufferedReader(fr);
            String str;
            while ((str = textReader.readLine()) != null)
                result = result + str + "\n";
            result = result.substring(0, result.length() - 1);
        } catch (IOException ignored) {
        } finally {
            try {
                assert textReader != null;
                textReader.close();
                fr.close();
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    //пуляем весь текст файла
    private void SendFileContent(String FileName) {
        try {
            String Content = FileOpen(FileName);
            dos.writeUTF("File content sending.");
            dos.writeUTF(Content);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //если клиент хочет сохранить, то
    private void Save(String LockedContent) {
        String fullname = "Shared/" + filename;
        String FileContent = FileOpen(filename); //открываем весь файл
        String[] ArrayContent = FileContent.split("\n");
        String[] TextData = new String[2]; //делим на 3 части
        for (int i = 0; i < 2; i++)
            TextData[i] = ""; //дозалоченная, залоченная и постзалоченная части сначала пустые
        int switcher = 0; //переходник с одной части на другую

        for (int i = 0; i < ArrayContent.length; i++) {//вдоль всего текста
            if (i == element.getStart() - 1) { //если это последний до заблоченной части, то
                switcher++;
                i += element.getEnd() - element.getStart() + 1;
            }//дописываем начало/концовку
            if (i < ArrayContent.length)
                TextData[switcher] += ArrayContent[i] + "\n";
        }

        if (TextData[1].length() != 0)
            TextData[1] = "\n" + TextData[1].substring(0, TextData[1].length() - 1);
        String outContent = TextData[0] + LockedContent + TextData[1];
        try {
            PrintWriter printer = new PrintWriter(fullname);
            printer.print(outContent);
            printer.close();
        } catch (IOException ignored) {
        }
    }

    //конструктор, задающий поток + сокет
    ClientThread(ServerThread st, Socket cs) {
        this.st = st;
        try {
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    //обработка того, что нам пришло от клиента
    public void run() {
        sendMes(id.toString());
        System.out.println("send ID");

        while (f) {
            try {
                String s;
                try {
                    s = dis.readUTF();
                } catch (EOFException ex1) {
                    break;
                }
                //если запрросили диапазон
                if (s.equals("Ranges sending")) {
                    GetRanges();
                    SendToDB(element.username);
                }

                // если залочили
                if (s.equals("Unlocking")) {
                    Unlock();
                    SendFileContent(filename);
                }

                //если хочет сохранить
                if (s.equals("File saving")) {
                    Unlock();
                    String LockedContent = dis.readUTF();
                    Save(LockedContent);
                    SendFileContent(filename);
                    dos.writeUTF("File saved successfully");
                }

                //
                if (s.equals("Name sending"))
                    GetName();

                //если клиент хочет список файлов
                if (s.equals("Get list of files.")) {
                    final File folder = new File("Shared");
                    dos.writeUTF("File list sending");
                    dos.writeUTF(listFilesForFolder(folder));
                }

                //если клиент просит текст файла
                if (s.equals("Get file content")) {
                    String FileName = dis.readUTF();
                    SendFileContent(FileName);
                }

                //если удаляется клиент//
                if (s.equals("Remove name"))
                    st.Names.remove(username);

                //если отключается клиент
                if (s.equals("end"))
                    f = false;
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //отправляем клиенту сообщение
    void sendMes(String s) {
        if (dos != null) {
            new Thread(() -> {
                try {
                    dos.writeUTF(s);
                } catch (IOException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();

        }
    }

    private String GetFromDB(String Name) {
        Connection c;
        PreparedStatement stmt;
        String short_filename = "Unknown";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dist_admin","root","root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("SELECT * FROM Filename WHERE Username=?");
            stmt.setString(1,Name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                short_filename = rs.getString("Filename");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return short_filename;
    }

    private void SendToDB(String Name) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dist_admin","root","root");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.prepareStatement("INSERT INTO Filename (Username, Filename) " +
                    "VALUES (?,?)");
            stmt.setString(1,Name);
            stmt.setString(2,filename);
            stmt.executeUpdate();

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created or updated successfully");
    }
}
