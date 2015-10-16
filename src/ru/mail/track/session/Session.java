package ru.mail.track.session;

import ru.mail.track.comands.Command;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.User;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
public interface Session {
    MessageStorage getMessageStorage();
    void startSession(User user)throws Exception;
    User getUser();
    PrintStream getStdOut();
    Scanner getStdIn();
    boolean isValid();
    void processInput(Message msg);
    Map<String, Command> getAvailableCommands();
}
