package ru.mail.track.storage;

import java.util.LinkedList;

//FIXME(arhangeldim): бесполезный комент
/**
 * Created by lesaha on 10/16/15.
 */
public interface MessageStorage{
    //FIXME(arhangeldim): форматирование (пробелы)
    //FIXME(arhangeldim): не указывайте конкретную реализацию, здесь нужно написать List<Message>
    // к тому же мы говорили, что LinkedList не самая эффективная структура
    LinkedList<Message> getLastMessagesWithRegex( int num, String regex)throws Exception;

    //FIXME(arhangeldim): rename to addMessage()
    void setNewMessage( Message msg);
}
