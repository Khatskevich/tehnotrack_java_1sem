package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;

public interface ServerBaseCommand {
    Result perform(SingleUserConnection connection, CommandsData command);
}
