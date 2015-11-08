package ru.mail.track.storage;

public interface UserStore {

    boolean isUserExist(User user);

    // Добавить пользователя в хранилище
    User addUser(User user) throws Exception;

    // Получить пользователя по имени и паролю
    User getUser(User user);

    User update(User user) throws Exception;
}
