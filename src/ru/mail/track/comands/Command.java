package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

/**
 * This interface is for unification of performing different possible operations by
 * Session class.
 */
public interface Command {
    Result perform(Session session, Message message);

    String getDescription();
}
