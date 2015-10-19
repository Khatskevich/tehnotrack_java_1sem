package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by lesaha on 16.10.15.
 */
public class CommandHistory implements Command {

    @Override
    public Result perform(Session session, Message msg) {
        if (!session.isValid()){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        //FIXME(arhangeldim): Как-то по смыслу странно получается, вроде это сообщение, а вы его как команду
        // обрабатываете
        String[] arguments = msg.getText().split(" ");
        int number = Integer.MAX_VALUE;
        if (arguments.length > 1) {
            number = parseInt(arguments[1]);
        }
        try {
            for (Message message : session.getMessageStorage().getLastMessagesWithRegex(number, null)) {
                out.println(message.getTimestamp() + ":" + message.getText());
            }
        } catch (Exception e) {
            out.println("Error ocured during performing command.");
        }
        return null;
    }
    public String getDescription(){
        return "return last messages [count]";
    }
}
