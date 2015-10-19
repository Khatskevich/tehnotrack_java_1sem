package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreStatic;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by lesaha on 16.10.15.
 */
public class CommandUser implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (!session.isValid()){
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        User user = session.getUser();
        out.println("Enter your nick please ( without spaces):");
        String nick = in.nextLine();
        if (nick.split(" ").length == 1) {
            user.setNickName(nick);
            UserStore msgStorage = new UserStoreStatic();
            try {
                msgStorage.editUser(user);
            } catch (Exception e) {
                out.println("Internal error: " + e.getMessage());
            }
        } else {
            out.println("Wrong nick");
        }
        return null;
    }
    public String getDescription(){
        return "change user nick name";
    }
}
