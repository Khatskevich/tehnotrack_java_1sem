package ru.mail.track.connection;

import java.io.Serializable;

/**
 * Слушает сообщения
 */
public interface MessageListener {

    void onMessage(Serializable object);
}