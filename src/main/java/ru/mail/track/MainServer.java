package ru.mail.track;

public class MainServer {
    public static void main(String[] args) throws Exception {
        ThreadedServer myServer = new ThreadedServer();
        myServer.main(args);
    }
}
