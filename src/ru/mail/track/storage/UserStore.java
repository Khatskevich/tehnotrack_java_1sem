package ru.mail.track.storage;

/**
 * Created by lesaha on 10/16/15.
 */
public interface UserStore {

    boolean isUserExist(User user) ;

    // Добавить пользователя в хранилище
    void addUser(User user) throws Exception ;

    // Получить пользователя по имени и паролю
    User getUser(User user) ;

    void editUser(User user) throws Exception;
}
