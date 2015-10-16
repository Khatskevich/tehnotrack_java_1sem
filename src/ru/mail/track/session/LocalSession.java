package ru.mail.track.session;

import ru.mail.track.comands.*;
import ru.mail.track.storage.*;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
public class LocalSession implements Session{
    Map<String, Command> availableCommands;
    private MessageStorage messageStorage = new MessageStorageLocal();
    private User user = null;
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);

    public Map<String, Command> getAvailableCommands() {
        return availableCommands;
    }

    public LocalSession(){

        availableCommands = new HashMap<>();
        availableCommands.put("\\find", new CommandFind());
        availableCommands.put("\\help", new CommandHelp());
        availableCommands.put("\\history", new CommandHistory());
        availableCommands.put("\\login", new CommandLogin());
        availableCommands.put("\\user", new CommandUser());
        availableCommands.put("\\undefined", new CommandUndefined());

    }
    @Override
    public MessageStorage getMessageStorage() {
        return messageStorage;
    }

    @Override
    public void startSession(User user) throws Exception {
        if ( user == null){
            throw new Exception("user == null!");
        }
        this.user = new UserStoreStatic().getUser(user);
        if ( this.user == null ){
            throw new Exception("Can not start session with that user!");
        }
    }

    @Override
    public User getUser(){
        return user;
    }

    @Override
    public PrintStream getStdOut(){
        return out;
    }

    @Override
    public Scanner getStdIn(){
        return in;
    }

    @Override
    public boolean isValid() {
        return user!=null;
    }

    private boolean isItCommand(Message msg) {
        return msg.getText().startsWith("\\");
    }
    private Command getCommand(String commandName){
        if (availableCommands.containsKey( commandName )){
            return  availableCommands.get( commandName);
        }
        return availableCommands.get("\\undefined");
    }

    @Override
    public void processInput(Message msg) {
        if (isItCommand(msg)) {
            getCommand(msg.getText().split(" ")[0]).perform(this, msg);
        } else {
            new SendMessage().perform(this, msg);
        }
    }

}
