package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreStatic;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
public class SendMessage implements Command {
    @Override
    public Result perform(Session session, Message message) {
        if (!session.isValid() || message == null){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        session.getMessageStorage().setNewMessage(message);
        return null;
    }
    public String getDescription(){
        return "send message";
    }
}
