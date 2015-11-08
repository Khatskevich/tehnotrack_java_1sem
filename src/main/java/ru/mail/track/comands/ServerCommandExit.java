package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.UserStore;

public class ServerCommandExit implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        System.out.println("Logging in...");
        try {
            UserStore userStorage = connection.getThreadedServer().getUserStore();
            if (connection.getUserId() != 0) {
                connection.getActiveConnections().deleteFromLogined(connection.getUserId());
                connection.setUserId(0l);
            }
            connection.getConnectionHandler().send(new InfoMessage("Now you are not loggined"));
        } catch (Exception e) {
        }
        return null;
    }

}
