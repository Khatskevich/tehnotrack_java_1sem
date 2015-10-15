package ru.mail.track;

import java.util.ArrayList;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageStorageStatic implements MessageStorage {
    ArrayList<Message> messages = new ArrayList<>();

    @Override
    public ArrayList<Message> getLastMessages(int num){
        int count = num < messages.size() ? num : messages.size();
        ArrayList<Message> messages_temp = new ArrayList<>();
        for ( int i = messages.size() - count ; i < messages.size() ; i++){
            messages_temp.add( messages.get(i));
        }
        return messages_temp;
    }
    @Override
    public void setNewMessage( Message msg){
        messages.add(msg);
    }
}
