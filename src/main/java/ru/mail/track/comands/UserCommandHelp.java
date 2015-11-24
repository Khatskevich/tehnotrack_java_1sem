package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

import java.util.Map;

public class UserCommandHelp implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        for (Map.Entry<String, UserBaseCommand> entry : UserCommandsDecoder.availableCommands.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getDescription());
        }
        return null;
    }

    public String getDescription() {
        return "show this message";
    }
}