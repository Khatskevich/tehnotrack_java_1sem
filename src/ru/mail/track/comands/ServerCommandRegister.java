package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.User;
import ru.mail.track.storage.UserStore;
import ru.mail.track.storage.UserStoreLocal;

public class ServerCommandRegister implements ServerBaseCommand{
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        String[] arguments = command.getText().split(" ");
        if (arguments.length == 3) { // registration
            try {
                System.out.println("Registration...");
                UserStore userStorage = new UserStoreLocal();
                String name = arguments[1];
                String password = arguments[2];
                User user = new User(name, password);
                userStorage.addUser(user);
                connection.getConnectionHandler().send(new InfoMessage(
                                        "Success!  Now you can log in\n"+
                                        "Username = " + name+"\n"+
                                        "Password = " + password+"\n"
                ));
            } catch (Exception e) {
            }
        }
        return null;
    }
}
