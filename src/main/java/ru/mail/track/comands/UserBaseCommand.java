package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

/**
 * This interface is for unification of performing different possible operations by
 * Session class.
 */
public interface UserBaseCommand {
    Result perform(ConnectionHandler connectionHandler, CommandsData command);

    String getDescription();
}
