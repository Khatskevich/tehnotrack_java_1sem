package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

/**
 * Created by lesaha on 16.10.15.
 */
public interface Command {
    Result perform(Session session, Message message);
    String getDescription();
}
