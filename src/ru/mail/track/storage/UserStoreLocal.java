package ru.mail.track.storage;

import java.util.ArrayList;

//FIXME(arhangeldim): что значит static?
//что этот класс работает локально.
public class UserStoreLocal implements UserStore {
    public static ArrayList<User> users = new ArrayList<>();

    public UserStoreLocal() {
        users.add(new User("valera", "23"));
        users.add(new User("vasya", "23"));
        users.add(new User("lera", "23"));
        users.add(new User("tanya", "123"));
        users.add(new User("sveta", "23"));
    }

    public boolean isUserExist(User userForCheck) {
        String name = userForCheck.getName();
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Добавить пользователя в хранилище
    public void addUser(User user) throws Exception {
        if (user != null) {
            if (!isUserExist(user)) {
                users.add(user);
            } else {
                //FIXME(arhangeldim): то есть просто Exception, который пролетит наверх?
                // И как себя поведет приложение? Что подумает пользователь?
                // Я не знаю как по другому.
                // Я предполагал, что это вверху обработается.
                throw new Exception("User exists");
            }
        }
    }

    // Получить пользователя по имени и паролю
    public User getUser(User user_) {
        String name = user_.getName();
        String pass = user_.getPass();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPass().equals(pass)) {
                return user;
            }
        }
        return null;
    }

    public void update(User user) throws Exception {
        throw new Exception("This method is unimplemented!");
    }
}
