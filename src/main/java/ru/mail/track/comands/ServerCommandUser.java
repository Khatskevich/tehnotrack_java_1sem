package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStoreLocal;

import java.io.IOException;

public class ServerCommandUser implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        String nick;
        String[] arguments = command.getText().split(" ");
        try {
            if (arguments.length == 2 && connection.getUserId() != 0) { // loginning in
                nick = arguments[1];

                UserStoreLocal usrStore = new UserStoreLocal();
                User user = usrStore.getUserWithId(connection.getUserId());
                user.setNickName(nick);
                usrStore.update(user);
                connection.getConnectionHandler().send(new InfoMessage("Nick was set\n"));
            } else {
                connection.getConnectionHandler().send(new InfoMessage("Can not perform command\n"));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
