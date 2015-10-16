package ru.mail.track.storage;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageStorageLocal implements MessageStorage {
    LinkedList<Message> messages = new LinkedList<>();

    @Override
    public LinkedList<Message> getLastMessagesWithRegex(int num, String regex) throws Exception {
        int count = num < messages.size() ? num : messages.size();
        LinkedList<Message> messages_temp = new LinkedList<>();
        for (Message tmpMsg : messages) {
            if (regex == null || tmpMsg.getText().matches(regex)) {
                messages_temp.addFirst(tmpMsg);
            }
            if (messages_temp.size() == num) {
                return messages_temp;
            }
        }
        return messages_temp;
    }

    @Override
    public void setNewMessage(Message msg) {
        messages.addFirst(msg);
    }
}
