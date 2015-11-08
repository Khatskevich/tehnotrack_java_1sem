package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.storage.Message;

import java.io.IOException;

public class UserCommandChatSend implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        String[] args = command.getText().split(" ");
        if (args.length < 2) {
            return null;
        }
        String text = command.getText().replaceFirst("^\\\\chat_send \\d+\\s", "");
        Message msg = new Message(text);
        msg.setDialogId(Long.decode(args[1]));
        msg.setSenderId(0l);
        try {
            connectionHandler.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return "sending message to specified chat usage \\chat_send chatId message";
    }
}