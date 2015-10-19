package ru.mail.track;

public class Main {

    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        messageService.runMessageLoop();
    }
}
