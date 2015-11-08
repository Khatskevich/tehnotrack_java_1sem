package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

import java.io.IOException;

public class UserCommandRegister implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        if (connectionHandler == null) {
            return null;
        }
        String[] arguments = command.getText().split(" ");
        if (arguments.length == 3) { // loginning in
            System.out.println("Registration...");
            try {
                connectionHandler.send(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getDescription() {
        return "registration. To register- type: \\register <username> <password>";
    }
}