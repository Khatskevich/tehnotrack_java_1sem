package ru.mail.track;

/**
 * Created by lesah_000 on 10/13/2015.
 */
public class Message {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String text;
    Message(String text){
        this.text = text;
    }
}
