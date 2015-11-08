package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;

import java.io.IOException;

public class ServerCommandUndefined implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            connection.getConnectionHandler().send(new InfoMessage("Wrong command"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
