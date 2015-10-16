package ru.mail.track;

import java.util.ArrayList;

/**
 * Created by lesaha on 10/16/15.
 */
public interface UserStore {

    boolean isUserExist(String name) ;

    // Добавить пользователя в хранилище
    void addUser(User user) throws Exception ;

    // Получить пользователя по имени и паролю
    User getUser(String name, String pass) ;

    void editUser(User user) throws Exception;
}
