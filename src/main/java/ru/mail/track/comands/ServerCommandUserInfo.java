package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;

public class ServerCommandUserInfo implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            String[] arguments = command.getText().split(" ");
            Long userId;
            if (arguments.length == 2) {
                userId = Long.decode(arguments[1]);
            } else {
                userId = connection.getUserId();
            }
            if (userId == 0) {
                connection.getConnectionHandler().send(new InfoMessage("Wrong input"));
                return null;
            }
            UserStore userStorage = connection.getThreadedServer().getUserStore();
            User user = userStorage.getUserWithId(userId);
            String result = "";
            result += "Username  = "+ user.getName() + "\n";
            result += "User id   = "+ user.getUserId() + "\n";
            result += "User nick = "+ user.getNickName();
            connection.getConnectionHandler().send(new InfoMessage(result));

        } catch (Exception e) {
        }
        return null;
    }
}
