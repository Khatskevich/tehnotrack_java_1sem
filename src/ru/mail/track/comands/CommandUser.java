package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CommandUser implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (!session.isLogined()) {
            return null;
        }
        BufferedReader in = session.getStdIn();
        PrintWriter out = new PrintWriter(session.getStdOut(), true);
        User user = session.getUser();
        out.println("Enter your nick please ( without spaces):");
        String nick = null;
        try {
            nick = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nick.split(" ").length == 1) {
            user.setNickName(nick);
            UserStore msgStorage = new UserStoreLocal();
            try {
                msgStorage.update(user);
            } catch (Exception e) {
                out.println("Internal error: " + e.getMessage());
            }
        } else {
            out.println("Wrong nick");
        }
        return null;
    }

    public String getDescription() {
        return "change user nick name";
    }
}
