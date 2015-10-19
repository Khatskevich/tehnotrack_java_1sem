package ru.mail.track.storage;

import java.util.Iterator;
import java.util.LinkedList;

//FIXME(arhangeldim): комент лишний
/**
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageStorageLocal implements MessageStorage {
    //FIXME(arhangeldim): модификатор доступа
    LinkedList<Message> messages = new LinkedList<>();

    @Override
    public LinkedList<Message> getLastMessagesWithRegex(int num, String regex) throws Exception {
        //FIXME(arhangeldim): поле не используется
        int count = num < messages.size() ? num : messages.size();

        //FIXME(arhangeldim): слева должен быть интерфейс List<Message>
        //FIXME(arhangeldim): неправильное имя, используйте camelCase именование, а не under_score
        // Почему выбран LinkedList?
        // и лучше назвать осмысленно, например resultMessages
        LinkedList<Message> messages_temp = new LinkedList<>();
        for (Message tmpMsg : messages) {
            //FIXME(arhangeldim): лучше на null проверить перед циклом
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
