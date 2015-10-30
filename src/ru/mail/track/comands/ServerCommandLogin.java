package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

public class ServerCommandLogin implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        String[] arguments = command.getText().split(" ");
        if (arguments.length == 3) { // loginning in
            System.out.println("Logging in...");
            User user = new User(arguments[1], arguments[2]);
            try {
                UserStore userStore = new UserStoreLocal();
                user = userStore.getUser(user);
                if (user != null) {
                    if (connection.getUserId() == 0) {
                        connection.getActiveConnections().deleteFromUnlogined(connection.getConnectionId());
                        connection.setUserId(user.getUserId());
                        connection.getActiveConnections().addToLogined(user.getUserId(), connection);
                    } else {
                        connection.getActiveConnections().deleteFromLogined(connection.getUserId());
                        connection.setUserId(user.getUserId());
                        connection.getActiveConnections().addToLogined(user.getUserId(), connection);
                    }
                    connection.getConnectionHandler().send(new InfoMessage("Now you loggined as " + user.getName() + "\n"));
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
}
