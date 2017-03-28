package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;

public class ServerCommandUser implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            if (connection.getUserId() == 0) {
                connection.getConnectionHandler().send(new InfoMessage("Log in please..."));
                return null;
            }
            String[] arguments = command.getText().split(" ");
            if (arguments.length == 2) {

                connection.getConnectionHandler().send(new InfoMessage("Nick name changing..."));
                UserStore userStorage = connection.getThreadedServer().getUserStore();
                User user = userStorage.getUserWithId(connection.getUserId());
                user.setNickName(arguments[1]);
                userStorage.update(user);

            }
        } catch (Exception e) {
        }
        return null;
    }
}
