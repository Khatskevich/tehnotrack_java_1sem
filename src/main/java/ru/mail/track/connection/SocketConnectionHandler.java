package ru.mail.track.connection;

import ru.mail.track.protocol.ObjectBytesConverter;
import ru.mail.track.storage.ControlMessage;

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
    private static Logger LOGGER = null;
    // подписчики
    private List<MessageListener> listeners = new ArrayList<>();
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
/*    private ObjectInputStream in;
    private ObjectOutputStream out;*/

    public SocketConnectionHandler(Socket socket, Logger logger) throws IOException {
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
/*        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());*/
        this.LOGGER = logger;
    }

    @Override
    public void send(Serializable object) throws IOException {
        LOGGER.info("Sending message");
        byte[] bytesArray = ObjectBytesConverter.toBytes(object);
        out.writeInt(bytesArray.length);
        out.write(bytesArray);
        /*out.writeObject(object);*/
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
                notifyListeners(ObjectBytesConverter.toObject(buf));
/*                Object obj = in.readObject();
                notifyListeners((Serializable) obj);*/
            } catch (EOFException e) {
                Thread.currentThread().interrupt();
                ControlMessage msg = new ControlMessage();
                msg.status = msg.LASTMESSAGE;
                notifyListeners(msg);
                System.exit(0);
                //return;
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
