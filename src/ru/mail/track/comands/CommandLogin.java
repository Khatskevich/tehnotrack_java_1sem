package ru.mail.track.comands;

import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class CommandLogin implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (session == null) {
            return null;
        }
        BufferedReader in = session.getStdIn();
        PrintWriter out = new PrintWriter(session.getStdOut(), true);
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
            try {
                out.println("Registration...");
                UserStore userStorage = new UserStoreLocal();
                out.println("Enter your name please:");
                String name = in.readLine();
                out.println("Enter your password please:");
                String password = in.readLine();
                User user = new User(name, password);

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