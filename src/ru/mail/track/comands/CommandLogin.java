package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CommandLogin implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (session == null) {
            return null;
        }
        Scanner in = session.getStdIn();
        PrintStream out = session.getStdOut();
        String[] arguments = msg.getText().split(" ");
        if (arguments.length == 3) { // loginning in
            out.println("Logging in...");
            User user = new User(arguments[1], arguments[2]);
            try {
                session.startSession(user);
            } catch (Exception e) {
                out.println("Can not log in." + e.getMessage());
            }
        } else if (arguments.length == 1) { // registration
            out.println("Registration...");
            UserStore userStorage = new UserStoreLocal();
            out.println("Enter your name please:");
            String name = in.nextLine();
            out.println("Enter your password please:");
            String password = in.nextLine();
            User user = new User(name, password);
            try {
                userStorage.addUser(user);
                out.println("Success!  Now you can log in");
                out.println("Username = " + name);
                out.println("Password = " + password);
            } catch (Exception e) {
                out.println("Can not create user. " + e.getMessage());
            }
        }
        return null;
    }

    public String getDescription() {
        return "press \\login <username> <password> to log in and \\login to register";
    }
}
