package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class SendMessage implements Command {
    @Override
    public Result perform(Session session, Message message) {
        if (!session.isLogined() || message == null) {
            return null;
        }
       // InputStream in = session.getStdIn();
       // PrintWriter out = new PrintWriter(session.getStdOut(), true);
        session.getMessageStorage().addMessage(message);
        return null;
    }

    public String getDescription() {
        return "send message";
    }
}
