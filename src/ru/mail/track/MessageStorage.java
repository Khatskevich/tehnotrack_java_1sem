package ru.mail.track;

import java.util.ArrayList;

/**
 * Created by lesaha on 10/16/15.
 */
public interface MessageStorage {
    ArrayList<Message> getLastMessagesWithRegex( int num, String regex);
    void setNewMessage( Message msg);
}
