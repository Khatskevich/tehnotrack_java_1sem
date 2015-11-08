package ru.mail.track.comands;

import java.util.HashMap;

public class ServerCommandsDecoder {
    static final HashMap<String, ServerBaseCommand> availableCommands;
    static
    {
        availableCommands = new HashMap<>();
        availableCommands.put("\\chat_find", new ServerCommandChatFind());
        availableCommands.put("\\chat_history", new ServerCommandChatHistory());
        availableCommands.put("\\chat_list", new ServerCommandChatList());
        availableCommands.put("\\login", new ServerCommandLogin());
        availableCommands.put("\\register", new ServerCommandRegister());
        availableCommands.put("\\user", new ServerCommandUser());
        availableCommands.put("\\undefined", new ServerCommandUndefined());
        availableCommands.put("\\exit", new ServerCommandExit());
        availableCommands.put("\\chat_create", new ServerCommandChatCreate());
        availableCommands.put("\\user_info", new ServerCommandUserInfo());
        availableCommands.put("\\user_pass", new ServerCommandUserPass());
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
