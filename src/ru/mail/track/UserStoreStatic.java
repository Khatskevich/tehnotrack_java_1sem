package ru.mail.track;


import java.util.ArrayList;

public class UserStoreStatic implements UserStore{
    ArrayList<User> users = new ArrayList<>();

    UserStoreStatic() {
        users.add(new User("valera", "23"));
        users.add(new User("vasya", "23"));
        users.add(new User("lera", "23"));
        users.add(new User("tanya", "123"));
        users.add(new User("sveta", "23"));
    }

    public boolean isUserExist(String name) {
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
            if (!isUserExist(user.getName() )){
                users.add(user);
            }else{
                throw new Exception("User exists");
            }
        }
    }

    // Получить пользователя по имени и паролю
    public User getUser(String name, String pass) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPass().equals(pass)) {
                return user;
            }
        }
        return null;
    }
}
