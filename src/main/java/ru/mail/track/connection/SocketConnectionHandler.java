package ru.mail.track.connection;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Класс работающий с сокетом, умеет отправлять данные в сокет
 * Также слушает сокет и рассылает событие о сообщении всем подписчикам (асинхронность)
 */
public class SocketConnectionHandler implements ConnectionHandler {
    private final static Logger LOGGER = Logger.getLogger(SocketConnectionHandler.class.getName());
    // подписчики
    private List<MessageListener> listeners = new ArrayList<>();
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public SocketConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void send(Serializable object) throws IOException {
        // TODO: здесь должен быть встроен алгоритм кодирования/декодирования сообщений
        // то есть требуется описать протокол
        LOGGER.info("Sending message");
        ByteArrayOutputStream serializatorBAIS = new ByteArrayOutputStream();
        (new ObjectOutputStream(serializatorBAIS)).writeObject(object);
        byte[] bytesArray = serializatorBAIS.toByteArray();
        out.writeInt(bytesArray.length);
        out.write(bytesArray);
        out.flush();
    }

    // Добавить еще подписчика
    @Override
    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }


    // Разослать всем
    public void notifyListeners(final Serializable object) {
        listeners.forEach(it -> it.onMessage(object));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int read = 0;
                int shouldBeRead;
                shouldBeRead = in.readInt();
                LOGGER.info("New message, length = " + shouldBeRead);
                if (shouldBeRead == 0) {
                    break;
                }
                final byte[] buf = new byte[shouldBeRead];
                while (read < shouldBeRead) {
                    read += in.read(buf, read, shouldBeRead - read);
                }
                ByteArrayInputStream diserializatorBAIS = new ByteArrayInputStream(buf);
                ObjectInput disearilizatorOI = new ObjectInputStream(diserializatorBAIS);
                notifyListeners((Serializable) disearilizatorOI.readObject());
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void stop() {
        Thread.currentThread().interrupt();
    }
}
