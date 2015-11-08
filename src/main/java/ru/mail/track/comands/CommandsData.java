package ru.mail.track.comands;

import java.io.Serializable;

public class CommandsData implements Serializable {
    private long userId;
    private long dialogId;
    private String text;

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public long getUserId() {

        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
