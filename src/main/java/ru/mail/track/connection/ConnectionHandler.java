package ru.mail.track.connection;

import java.io.IOException;
import java.io.Serializable;

/**
 * Обработчик сокета
 */
public interface ConnectionHandler extends Runnable {

    void send( Serializable object) throws IOException;

    void addListener(MessageListener listener);

    void stop();
}