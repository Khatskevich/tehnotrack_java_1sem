package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

public class UserCommandUndefined implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        if (connectionHandler == null) {
            return null;
        }
        System.out.println("Unrecognised command");
        System.out.println("Use \\help to get list of available commands");
        return null;
    }

    public String getDescription() {
        return "all undefined comands ( start with \\ but undefined) do nothond";
    }
}
