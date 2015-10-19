package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
//FIXME(arhangeldim): Это можно обработать выше без написания такого странного класса
public class CommandUndefined implements Command {
    @Override
    public Result perform(Session session, Message message) {
        if ( session==null){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        out.println("Unrecognised command");
        out.println("Use \\help to get list of available commands");
        return null;
    }
    public String getDescription(){
        return "all undefined comands ( start with \\ but undefined) do nothond";
    }
}
