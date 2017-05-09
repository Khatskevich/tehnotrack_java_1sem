package ru.mail.track.comands;

import java.util.HashMap;

public class UserCommandsDecoder {
    static final HashMap<String, UserBaseCommand> availableCommands;
    static
    {
        availableCommands = new HashMap<>();
        availableCommands.put("\\find", new UserCommandFind());
        availableCommands.put("\\help", new UserCommandHelp());
        availableCommands.put("\\history", new UserCommandHistory());
        availableCommands.put("\\login", new UserCommandLogin());
        availableCommands.put("\\register", new UserCommandRegister());
        availableCommands.put("\\user", new UserCommandFind());
        availableCommands.put("\\undefined", new UserCommandUndefined());
        availableCommands.put("\\exit", new UserCommandFind());
    }

    private static UserBaseCommand getCommand(String commandName) {
        if (availableCommands.containsKey(commandName)) {
            return availableCommands.get(commandName);
        }
        return availableCommands.get("\\undefined");
    }

    public static UserBaseCommand getCommand(CommandsData commandsData) {
        return getCommand(commandsData.getText().split(" ")[0]);
    }
}
