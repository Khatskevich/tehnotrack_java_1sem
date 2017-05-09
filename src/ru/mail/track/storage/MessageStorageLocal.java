package ru.mail.track.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MessageStorageLocal implements MessageStorage {
    //Здесь линкед лист - для безопастной и более быстрой работы с итераторами при поиске с конца.
    //Если появятся другие команды ( не история и поиск) - то возможно придется менять.
    private LinkedList<Message> messages = new LinkedList<>();

    @Override
    public ArrayList<Message> getLastMessagesWithRegex(int num, String regex) throws Exception {

        ArrayList<Message> resultMessages = new ArrayList<>();
        for (Message tmpMsg : messages) {
            //FIXME(arhangeldim): лучше на null проверить перед циклом
            // не хочется дублировать код
            // regex = null значит, что нам подходит любой патерн, остальная логика - такая же.
            if (regex == null || tmpMsg.getText().matches(regex)) {
                resultMessages.add(tmpMsg);
            }
            if (resultMessages.size() == num) {
                Collections.reverse(resultMessages);
                return resultMessages;
            }
        }
        Collections.reverse(resultMessages);
        return resultMessages;
    }

    @Override
    public void addMessage(Message msg) {
        messages.addFirst(msg);
    }
}
