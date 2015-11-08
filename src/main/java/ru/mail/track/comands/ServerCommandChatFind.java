package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;

public class ServerCommandChatFind implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            StringBuilder result = new StringBuilder();
            int number = Integer.MAX_VALUE;
            String regex = command.getText(). replaceFirst("^\\\\find \\d+\\s", "");
            Long chatId = 0l;
            String[] args = command.getText().split(" ");
            if (args.length > 1) {
                chatId = Long.decode(args[1]);
            } else {
                connection.getConnectionHandler().send(new InfoMessage("Wrong input"));
                return null;
            }

            result.append("Looking for " + "\"" + regex + "\"\n");

            MessageStorage msgStore = connection.getThreadedServer().getMessageStorage();
            for (Message message : msgStore.getLastMessagesWithRegex(number, chatId, regex)) {
                result.append(message.getTimestamp() + ":" + message.getText()+"\n");
            }
            connection.getConnectionHandler().send(new InfoMessage(result.toString()));
        } catch (Exception e) {
            System.out.println("Error ocured during performing command.");
        }
        return null;
    }
}
