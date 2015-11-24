package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;

import static java.lang.Integer.parseInt;

public class ServerCommandChatHistory implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            String[] arguments = command.getText().split(" ");
            int number = Integer.MAX_VALUE;
            long chatId;
            if (arguments.length == 2) {
                chatId = parseInt(arguments[1]);
            } else if (arguments.length == 3) {
                number = parseInt(arguments[2]);
                chatId = parseInt(arguments[1]);
            } else {
                connection.getConnectionHandler().send(new InfoMessage("Wrong input"));
                return null;
            }
            StringBuilder result = new StringBuilder();

            MessageStorage msgStore = connection.getThreadedServer().getMessageStorage();
            for (Message message : msgStore.getLastMessagesWithRegex(number, chatId, null)) {
                result.append(message.getTimestamp()).append(":").append(message.getText()).append("\n");
            }
            connection.getConnectionHandler().send(new InfoMessage(result.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
