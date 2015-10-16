package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by lesaha on 16.10.15.
 */
public class CommandHelp implements Command {
    @Override
    public Result perform(Session session, Message msg){
        if (session==null){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        for(Map.Entry< String, Command> entry : session.getAvailableCommands().entrySet() ){
            out.println( entry.getKey() + " - " + entry.getValue().getDescription());
        }
        return null;
    }
    public String getDescription(){
        return "show this message";
    }
}
