package ru.mail.track.storage;

import java.util.LinkedList;

/**
 * Created by lesaha on 10/16/15.
 */
public interface MessageStorage{
    LinkedList<Message> getLastMessagesWithRegex( int num, String regex)throws Exception;
    void setNewMessage( Message msg);
}
