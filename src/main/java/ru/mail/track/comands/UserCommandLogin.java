package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

import java.io.IOException;

public class UserCommandLogin implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        if (connectionHandler == null) {
            return null;
        }
        String[] arguments = command.getText().split(" ");
        if (arguments.length == 3) { // loginning in
            System.out.println("Logging in...");
            try {
                connectionHandler.send(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getDescription() {
        return "press \\login <username> <password> to log in";
    }
}