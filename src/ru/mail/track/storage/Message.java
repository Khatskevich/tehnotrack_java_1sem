package ru.mail.track.storage;

public class Message {
    private String text;

    //FIXME(arhangeldim): А где методы для устанвоки времени? Что за значение по-умолчанию?
    private long timestamp = System.currentTimeMillis() / 1000L;

    public Message(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }


    public long getTimestamp() {
        return timestamp;
    }
}
