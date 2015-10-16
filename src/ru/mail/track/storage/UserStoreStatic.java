package ru.mail.track.storage;

import java.util.ArrayList;

//FIXME(arhangeldim): что значит static?
public class UserStoreStatic implements UserStore{
    //FIXME(arhangeldim): модификатор доступа?
    static ArrayList<User> users = new ArrayList<>();

    public UserStoreStatic() {
        users.add(new User("valera", "23"));
        users.add(new User("vasya", "23"));
        users.add(new User("lera", "23"));
        users.add(new User("tanya", "123"));
        users.add(new User("sveta", "23"));
    }

    //FIXME(arhangeldim): не используем подчеркивание в именах

    public boolean isUserExist( User user_) {
        String name = user_.getName();
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
            if (!isUserExist(user)){
                users.add(user);
            } else {
                //FIXME(arhangeldim): то есть просто Exception, который пролетит наверх?
                // И как себя поведет приложение? Что подумает пользователь?
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

    public void editUser( User user) throws Exception{
        throw new Exception("This method is unimplemented!");
    }
}
