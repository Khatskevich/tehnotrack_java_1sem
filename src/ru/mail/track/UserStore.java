package ru.mail.track;


import java.util.ArrayList;

public class UserStore {
    ArrayList<User>  users = new ArrayList<User>();

    UserStore(){
        users.add(new User("valera", "23"));
        users.add(new User("vasya", "23"));
        users.add(new User("lera", "23"));
        users.add(new User("tanya", "23"));
        users.add(new User("sveta", "23"));
    }

    boolean isUserExist(String name) {
        for( User user : users){
            if ( user.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    // Добавить пользователя в хранилище
    void addUser(User user) {
        if ( user != null) {
            users.add(user);
        }
    }

    // Получить пользователя по имени и паролю
    User getUser(String name, String pass) {
        for( User user : users){
            if ( user.getName().equals(name) && user.getPass().equals(pass)){
                return user;
            }
        }
        return null;
    }
}
