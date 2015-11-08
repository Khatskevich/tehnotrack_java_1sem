package ru.mail.track.comands;

import java.util.HashMap;

public class UserCommandsDecoder {
    static final HashMap<String, UserBaseCommand> availableCommands;
    static
    {
        availableCommands = new HashMap<>();
        availableCommands.put("\\chat_find", new UserCommandChatFind());
        availableCommands.put("\\help", new UserCommandHelp());
        availableCommands.put("\\chat_history", new UserCommandChatHistory());
        availableCommands.put("\\login", new UserCommandLogin());
        availableCommands.put("\\register", new UserCommandRegister());
        availableCommands.put("\\undefined", new UserCommandUndefined());
        availableCommands.put("\\exit", new UserCommandExit());
        availableCommands.put("\\chat_create", new UserCommandChatCreate());
        availableCommands.put("\\chat_send", new UserCommandChatSend());
        availableCommands.put("\\user", new UserCommandUser());
        availableCommands.put("\\user_info", new UserCommandUserInfo());
        availableCommands.put("\\user_pass", new UserCommandUserPass());
        availableCommands.put("\\chat_list", new UserCommandChatList());
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
