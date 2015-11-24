package ru.mail.track.storage;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private Long senderId;
    private Long dialogId;
    private long timestamp = System.currentTimeMillis() / 1000L;

    public Message(String text) {
        this.text = text;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getDialogId() {
        return dialogId;
    }

    public void setDialogId(Long dialogId) {
        this.dialogId = dialogId;
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

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!this.getText().equals(other.getText())
                || !this.getDialogId().equals(other.getDialogId())
                || !this.getSenderId().equals(other.getSenderId())
                || !(this.getTimestamp() == other.getTimestamp())
                ) {
            return false;
        }
        return true;
    }
}
