package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

import java.io.IOException;

public class UserCommandUser implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        try {
            connectionHandler.send(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if (!connectionHandler.isLogined()) {
            return null;
        }
        User user = connectionHandler.getUser();
        System.out.println("Enter your nick please ( without spaces):");
        String nick = null;
        String[] arguments = command.getText().split(" ");
        if (arguments.length == 2) { // loginning in
            nick = arguments[1];
            user.setNickName(nick);
            UserStore msgStorage = new UserStoreLocal();
            try {
                msgStorage.update(user);
            } catch (Exception e) {
                System.out.println("Internal error: " + e.getMessage());
            }
        } else {
            System.out.println("Wrong nick");
        }*/
        return null;
    }

    public String getDescription() {
        return "change user nick name";
    }
}
