package ru.mail.track.session;

import ru.mail.track.comands.*;
import ru.mail.track.storage.*;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class LocalSession implements Session {
    private Map<String, Command> availableCommands;
    private MessageStorage messageStorage = new MessageStorageLocal();
    private User user = null;
    public OutputStream out;
    public BufferedReader in;

    public Map<String, Command> getAvailableCommands() {
        return availableCommands;
    }

    private void fillAvailableCommandsStructure() {
        availableCommands = new HashMap<>();
        availableCommands.put("\\find", new CommandFind());
        availableCommands.put("\\help", new CommandHelp());
        availableCommands.put("\\history", new CommandHistory());
        availableCommands.put("\\login", new CommandLogin());
        availableCommands.put("\\user", new CommandUser());
        availableCommands.put("\\undefined", new CommandUndefined());
        availableCommands.put("\\exit", new CommandExit());
    }

    public LocalSession() {
        fillAvailableCommandsStructure();
    }

    public LocalSession(OutputStream out,
                        BufferedReader in) {
        fillAvailableCommandsStructure();
        this.in = in;
        this.out = out;
    }


    @Override
    public MessageStorage getMessageStorage() {
        return messageStorage;
    }

    @Override
    public void startSession(User user) throws Exception {
        if (user == null) {
            throw new Exception("user == null!");
        }
        this.user = new UserStoreLocal().getUser(user);
        if (this.user == null) {
            throw new Exception("Can not start session with that user!");
        }
    }

    @Override
    public void stopSession() {
        this.user = null;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public OutputStream getStdOut() {
        return out;
    }

    @Override
    public BufferedReader getStdIn() {
        return in;
    }

    @Override
    public boolean isLogined() {
        return user != null;
    }

    private boolean isItCommand(Message msg) {
        return msg.getText().startsWith("\\");
    }

    private Command getCommand(String commandName) {
        if (availableCommands.containsKey(commandName)) {
            return availableCommands.get(commandName);
        }
        return availableCommands.get("\\undefined");
    }

    @Override
    public Result processInput(Message msg) {
        if (isItCommand(msg)) {
            return getCommand(msg.getText().split(" ")[0]).perform(this, msg);
        } else {
            return new SendMessage().perform(this, msg);
        }
    }

}
