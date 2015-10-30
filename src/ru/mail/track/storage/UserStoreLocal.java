package ru.mail.track.storage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//FIXME(arhangeldim): что значит static?
//что этот класс работает локально.
public class UserStoreLocal implements UserStore {
    public static ArrayList<User> users = new ArrayList<>();
    private static AtomicLong internalCounter = new AtomicLong(1);
    public UserStoreLocal() {
        users.add(new User("valera", "23", 100));
        users.add(new User("vasya", "23", 101));
        users.add(new User("lera", "23", 102));
        users.add(new User("tanya", "123", 103));
        users.add(new User("sveta", "23", 104));
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
    public User addUser(User user) throws Exception {
        if (user != null) {
            if (!isUserExist(user)) {
                user.setUserId(internalCounter.incrementAndGet());
                users.add(user);
                return  user;
            } else {
                return null;
            }
        }
        return null;
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

    public User update(User newUser) throws Exception {
        for (User user : users) {
            if (user.getUserId() == newUser.getUserId() ) {
                users.remove(user);
                users.add(newUser);
                return newUser;
            }
        }
        return null;
    }

    public User getUserWithId(Long id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }
}
