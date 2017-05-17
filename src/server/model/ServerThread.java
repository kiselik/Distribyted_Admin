package server.model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread implements IServerThread {
    private boolean f = true;
    private int port = 3124;
    private InetAddress ip = null;

    private ServerSocket ss;
    private Hashtable<UUID, ClientThread> allClient = new Hashtable<>();//таблица всех подключенных клиентов
    private Hashtable<String, ArrayList<FileElement>> allFiles = new Hashtable<>();//список всех залоченных файлов
    private FileElement lastFileElement;
    private String lastFilename;

    ArrayList Names = null;
    //список из объектов - обработчиков запросов клиента ?????????????7
    private ArrayList<IObserver> observers = new ArrayList<>();


    @Override
    public FileElement getLastFileElement() {
        return lastFileElement;
    }

    @Override
    public String getLastFilename() {
        return lastFilename;
    }

    //синхронизированная отправка сообщений от сервера всем клиентам
    synchronized public void sendToAll() {
        for (Map.Entry<UUID, ClientThread> entrySet :
                allClient.entrySet()) {
            //UUID key = entrySet.getKey();
            //пройти по всем клиентам взять ClientThread иии
            ClientThread value = entrySet.getValue();
            String s = "Disconnected";
            //отправить сообщеньку
            value.sendMes(s);
        }
    }

    synchronized public void stopServer() {
        f = false;
        //все клиенты отключаются
        sendToAll();
        Stop();
    }

    //отсоединяем подключенного клиента, путём закрытия сокета
    //
    private void Stop() {
        try {
            Socket s = new Socket(ip, port);
            s.getOutputStream().flush();
            s.close();
        } catch (IOException ignored) {
            //очень плохо,что игнорим
        }
    }

    //конструктор по умолчанию, получем ip и сокет
    ServerThread() {
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ss = new ServerSocket(port, 0, ip);
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    //запуск сервера и вечное ожидание клиентов
    public void run() {
        while (f) {
            try {
                //.accept() -  заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                //После успешного подключения клиентом, создается нормальный Socket объект,
                // который вы можете использовать для выполнения все существующий операций с сокетом.
                Socket cs = ss.accept();
                ClientThread ct = new ClientThread(this, cs);
                System.out.println("connect" + ct.getUUID().toString()); //сообщаем, кто подключился
                allClient.put(ct.getUUID(), ct); //засовываем в таблицу подключенных клиентов
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    //ложим в мапу залоченных файлов новый элемент с самим клиентом
    //кладем*
    void putFileElement(String filename, FileElement element) {
        //ищем в мапе со всеми залоченными файлами
        ArrayList<FileElement> clients = allFiles.get(filename); //копируем из мапы имена файлов в массив
        //причем тут клиенты???????? если мы копируем из мапы с файлами
        boolean f = true;
        if (clients == null) {
            clients = new ArrayList<>();
            //если это первый клиент, добавляем его в список
            clients.add(element);
            allFiles.put(filename, clients);
        } else {
            int start = element.getStart();
            int end = element.getEnd();

            //по всем клиентам
            for (Object client : clients) {
                FileElement tmp = (FileElement) client;
                //если мы  полностью до залоченной части или полность после залоченной части то всё норм, иначе - braek;
                if (!((start < tmp.getStart() && end < tmp.getStart()) || (start > tmp.getEnd() && end > tmp.getEnd()))) {
                    f = false;
                    break;
                }
            }
            if (f) {
                clients.add(element);
            }
        }
        if (f) {
            System.out.println("range is:" + element.getStart() + "-" + element.getEnd());
            element.getCT().sendMes("Ranges was set successfully");
            lastFileElement = element;
            lastFilename = filename;
            updateLastConnected();
//            this.addToTable(filename, element);
        } else {
            System.out.println("Error with setting ranges");
            element.getCT().sendMes("Error with setting ranges");
        }
    }


    // извлекаем из мапы элемент в случае, если клиент залочил его
    void extractFileElement(String filename, FileElement element) {
        ArrayList<FileElement> clients = allFiles.get(filename);
        int index = clients.indexOf(element);
        clients.remove(index);//works
        if (clients.size() == 0) {
            allFiles.remove(filename);
        }
        lastFileElement = element;
        lastFilename = filename;
        updateLastDisconnected();
    }

    private void updateLastConnected() {
        //обновляем того, кто подключился последний
        for (IObserver o : observers)
            o.updateLastConnected();
    }

    private void updateLastDisconnected() {
        //обновляем того, кто отключился последним
        for (IObserver o : observers)
            o.updateLastDisconnected();
    }

    @Override
    //добавляем новый поток у сервера, который будет работать с клиентом
    public void addObserver(IObserver o) {
        observers.add(o);
    }
}
