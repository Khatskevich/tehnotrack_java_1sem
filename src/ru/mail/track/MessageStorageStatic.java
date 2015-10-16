package ru.mail.track;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageStorageStatic implements MessageStorage {
    LinkedList<Message> messages = new LinkedList<>();

    @Override
    public ArrayList<Message> getLastMessagesWithRegex(int num, String regex) {
        int count = num < messages.size() ? num : messages.size();
        ArrayList<Message> messages_temp = new ArrayList<>();
        Iterator<Message> messageIterator = messages.iterator();
        while ( messageIterator.hasNext() ){
            Message tmpMsg = messageIterator.next();
            if ( regex == null || tmpMsg.text.matches(regex)){
                messages_temp.add(tmpMsg);
            }
            if ( messages_temp.size() == num){
                return messages_temp;
            }
        }
        return messages_temp;
    }

    @Override
    public void setNewMessage(Message msg) {
        messages.addLast(msg);
    }
}
