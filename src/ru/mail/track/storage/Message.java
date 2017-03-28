package ru.mail.track.storage;

public class Message {
    private String text;

    private long timestamp = System.currentTimeMillis() / 1000L;

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message(String text) {
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
