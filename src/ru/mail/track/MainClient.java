package ru.mail.track;

public class MainClient {
    public static void main(String[] args) throws Exception {
        ThreadedClient myClient = new ThreadedClient();
        myClient.messageLoop();
    }
}
