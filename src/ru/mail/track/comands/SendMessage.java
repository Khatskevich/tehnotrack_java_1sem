package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

import java.io.PrintStream;
import java.util.Scanner;

public class SendMessage implements Command {
    @Override
    public Result perform(Session session, Message message) {
        if (!session.isLogined() || message == null) {
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        session.getMessageStorage().addMessage(message);
        return null;
    }

    public String getDescription() {
        return "send message";
    }
}
