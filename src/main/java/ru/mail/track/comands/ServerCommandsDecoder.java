package ru.mail.track.comands;

import java.util.HashMap;

public class ServerCommandsDecoder {
    static final HashMap<String, ServerBaseCommand> availableCommands;
    static
    {
        availableCommands = new HashMap<>();
        availableCommands.put("\\find", new ServerCommandFind());
        // temprorary unavailable command
        //availableCommands.put("\\help", new ServerCommandHelp());
        availableCommands.put("\\history", new ServerCommandHistory());
        availableCommands.put("\\login", new ServerCommandLogin());
        availableCommands.put("\\register", new ServerCommandRegister());
        availableCommands.put("\\user", new ServerCommandFind());
        availableCommands.put("\\undefined", new ServerCommandUndefined());
        availableCommands.put("\\exit", new ServerCommandFind());
    }

    private static ServerBaseCommand getCommand(String commandName) {
        if (availableCommands.containsKey(commandName)) {
            return availableCommands.get(commandName);
        }
        return availableCommands.get("\\undefined");
    }

    public static ServerBaseCommand getCommand(CommandsData commandsData) {
        return getCommand(commandsData.getText().split(" ")[0]);
    }
}
