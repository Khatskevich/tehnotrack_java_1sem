package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.MessageStorageLocal;

public class ServerCommandFind implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        StringBuilder result = new StringBuilder();
        int number = Integer.MAX_VALUE;
        String regex = command.getText().replaceFirst("\\\\find ", "");
        result.append("Looking for " + "\"" + regex + "\"");
        try {
            MessageStorage msgStore = new MessageStorageLocal();
            for (Message message : msgStore.getLastMessagesWithRegex(number, regex)) {
                result.append(message.getTimestamp() + ":" + message.getText());
            }
            connection.getConnectionHandler().send(new InfoMessage(result.toString()));
        } catch (Exception e) {
            System.out.println("Error ocured during performing command.");
        }
        return null;
    }
}
