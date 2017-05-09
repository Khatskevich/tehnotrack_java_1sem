package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

import java.io.PrintWriter;

public class CommandFind implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (!session.isLogined()) {
            return null;
        }
        PrintWriter out = new PrintWriter(session.getStdOut(), true);
        int number = Integer.MAX_VALUE;
        String regex = msg.getText().replaceFirst("\\\\find ", "");
        out.println("Looking for " + "\"" + regex + "\"");
        try {
            for (Message message : session.getMessageStorage().getLastMessagesWithRegex(number, regex)) {
                out.println(message.getTimestamp() + ":" + message.getText());
            }
        } catch (Exception e) {
            //FIXME(arhangeldim): ну в логи вы написали, а пользователю что делать?
            // session.getStdOut(); - по моей идее - возвращает поток, который выводит сообщения пользователю.
            // Наверное лучше его переименовать?
            out.println("Error ocured during performing command.");
        }
        return null;
    }

    public String getDescription() {
        return "find messages in history using regex";
    }
}