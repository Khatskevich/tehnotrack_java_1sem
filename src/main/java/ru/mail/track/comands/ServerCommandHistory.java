package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.MessageStorageLocal;

import static java.lang.Integer.parseInt;

public class ServerCommandHistory implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        String[] arguments = command.getText().split(" ");
        int number = Integer.MAX_VALUE;
        if (arguments.length > 1) {
            number = parseInt(arguments[1]);
        }
        StringBuilder result = new StringBuilder();
        try {
            MessageStorage msgStore = new MessageStorageLocal();
            for (Message message : msgStore.getLastMessagesWithRegex(number, null)) {
                result.append(message.getTimestamp() + ":" + message.getText()+ "\n");
            }
            connection.getConnectionHandler().send( new InfoMessage(result.toString()));
        } catch (Exception e) {
        }
        return null;
    }
}
