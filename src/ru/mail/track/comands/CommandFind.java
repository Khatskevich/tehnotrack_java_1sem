package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
public class CommandFind implements Command {
    @Override
    public Result perform(Session session, Message msg){
        if (!session.isValid()){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        int number = Integer.MAX_VALUE;
        String regex = msg.getText().replaceFirst("\\\\find ", "");
        out.println("Looking for " + "\"" + regex + "\"");
        try {
            for (Message message : session.getMessageStorage().getLastMessagesWithRegex(number, regex)) {
                out.println(message.getTimestamp() +":"+ message.getText());
            }
        } catch (Exception e) {
            out.println("Error ocured during performing command.");
        }
        return null;
    }
    public String getDescription(){
        return "find messages in history using regex";
    }
}
