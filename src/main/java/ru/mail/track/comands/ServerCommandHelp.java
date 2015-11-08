package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;

public class ServerCommandHelp implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        return null;
    }
}
