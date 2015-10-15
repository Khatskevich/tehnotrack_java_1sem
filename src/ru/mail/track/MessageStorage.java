package ru.mail.track;

import java.util.ArrayList;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageStorage {
    ArrayList<Message> messages = new ArrayList<>();

    ArrayList<Message> getLastMessages( int num){
        int count = num < messages.size() ? num : messages.size();
        ArrayList<Message> messages_temp = new ArrayList<>();
        for ( int i = messages.size() - count ; i < messages.size() ; i++){
            messages_temp.add( messages.get(i));
        }
        return messages_temp;
    }
    void setNewMessage( Message msg){
        messages.add(msg);
    }
}
