package ru.mail.track.control;

import java.io.Serializable;

public class InfoMessage implements Serializable {
    String text;

    public InfoMessage(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
