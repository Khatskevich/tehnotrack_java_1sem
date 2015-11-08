package ru.mail.track.storage;

import java.util.List;

public interface MessageStorage {
    List<Message> getLastMessagesWithRegex(int num, long chatId, String regex) throws Exception;

    void addMessage(Message msg);
}
