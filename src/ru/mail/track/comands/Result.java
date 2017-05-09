package ru.mail.track.comands;

public class Result {
    public enum Status {
        Sending,
        WaitingForSend,
        Error,
        Sent,
        LastMessage
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;
}
