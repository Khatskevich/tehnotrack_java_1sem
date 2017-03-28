package ru.mail.track;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.connection.SocketConnectionHandler;
import ru.mail.track.control.ActiveConnections;
import ru.mail.track.control.Dialogs;
import ru.mail.track.storage.DBMessageStorage;
import ru.mail.track.storage.DBUserStorage;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.UserStore;

import java.io.IOException;
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

    public MessageStorage getMessageStorage() {
        return messageStorage;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    private UserStore userStore = null;

    public ComboPooledDataSource getConnectionPool() {
        return connectionPool;
    }

    private ComboPooledDataSource connectionPool;


    public ThreadedServer() {

        activeConnections = new ActiveConnections();

        try {
            Class.forName("org.postgresql.Driver");
            connectionPool = new ComboPooledDataSource();
            connectionPool.setDriverClass("org.postgresql.Driver"); //loads the jdbc driver
            connectionPool.setJdbcUrl("jdbc:postgresql://178.62.140.149:5432/eivae2iz");
            connectionPool.setUser("senthil");
            connectionPool.setPassword("ubuntu");
            // the settings below are optional -- c3p0 can work with defaults
            connectionPool.setMinPoolSize(5);
            connectionPool.setAcquireIncrement(5);
            connectionPool.setMaxPoolSize(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userStore = new DBUserStorage(this);
        messageStorage = new DBMessageStorage(this);
        dialogs = new Dialogs(this, messageStorage, activeConnections);
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
            ConnectionHandler handler = new SocketConnectionHandler(socket, LOGGER);
            SingleUserConnection userConnection = new SingleUserConnection(handler, this, dialogs, activeConnections, messageStorage, internalCounter.incrementAndGet(), LOGGER);
        }
    }

    public void stopServer() {
        isRunning = false;
        //for (ConnectionHandler handler : handlers.values()) {
        //    handler.stop();
        //}
    }
}
