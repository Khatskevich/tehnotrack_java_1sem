package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

import java.io.PrintWriter;
import java.util.Map;

public class CommandHelp implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (session == null) {
            return null;
        }
        PrintWriter out = new PrintWriter(session.getStdOut(), true);
        for (Map.Entry<String, Command> entry : session.getAvailableCommands().entrySet()) {
            out.println(entry.getKey() + " - " + entry.getValue().getDescription());
        }
        return null;
    }

    public String getDescription() {
        return "show this message";
    }
}