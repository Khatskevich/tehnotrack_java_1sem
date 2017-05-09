package ru.mail.track;

import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.connection.MessageListener;
import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.connection.SocketConnectionHandler;
import ru.mail.track.control.ActiveConnections;
import ru.mail.track.control.Dialogs;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.MessageStorageLocal;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


/**
 *
 */
public class ThreadedServer {
    public static final int PORT = 19000;
    private final static Logger LOGGER = Logger.getLogger(ThreadedServer.class.getName());
    private volatile boolean isRunning;
    private Map<Long, ConnectionHandler> handlers = new HashMap<>();
    private AtomicLong internalCounter = new AtomicLong(0);
    private ServerSocket sSocket;
    private Dialogs dialogs = null;
    private ActiveConnections activeConnections = null;
    private MessageStorage messageStorage = null;


    public ThreadedServer() {
        messageStorage = new MessageStorageLocal();
        activeConnections = new ActiveConnections();
        dialogs = new Dialogs(messageStorage, activeConnections);
        try {
            sSocket = new ServerSocket(PORT);
            sSocket.setReuseAddress(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void main(String[] args) throws Exception {
        System.out.println("Server");
        this.startServer();
    }

    private void startServer() throws Exception {

        isRunning = true;
        while (isRunning) {
            Socket socket = sSocket.accept();
            System.out.println("New connection!");
            ConnectionHandler handler = new SocketConnectionHandler(socket);
            SingleUserConnection userConnection = new SingleUserConnection(handler, dialogs, activeConnections, messageStorage, internalCounter.incrementAndGet(), LOGGER);
        }
    }

    public void stopServer() {
        isRunning = false;
        //for (ConnectionHandler handler : handlers.values()) {
        //    handler.stop();
        //}
    }
}
