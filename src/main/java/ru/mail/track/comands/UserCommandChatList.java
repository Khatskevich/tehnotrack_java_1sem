package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

import java.io.IOException;

public class UserCommandChatList implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        try {
            connectionHandler.send(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return "list all chats of this user usage \\chat_list";
    }
}
