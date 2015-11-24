package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;

public class ServerCommandUserPass implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            if (connection.getUserId() == 0) {
                connection.getConnectionHandler().send(new InfoMessage("Log in please..."));
                return null;
            }
            String[] arguments = command.getText().split(" ");
            if (arguments.length == 3) {

                connection.getConnectionHandler().send(new InfoMessage("Password changing..."));
                UserStore userStorage = connection.getThreadedServer().getUserStore();
                User user = userStorage.getUserWithId(connection.getUserId());
                if (!user.getPass().equals(arguments[1])) {
                    return null;
                }
                user.setPass(arguments[2]);
                userStorage.update(user);

            }
        } catch (Exception e) {
        }
        return null;
    }
}
