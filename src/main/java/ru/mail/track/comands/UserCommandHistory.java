package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.storage.Message;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class UserCommandHistory implements UserBaseCommand {

    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        try {
                connectionHandler.send(command);
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return "return last messages [count]";
    }
}
