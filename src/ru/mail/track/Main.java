package ru.mail.track;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<ListenerProcess> threads = new HashSet<>();

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    MessageService messageService = new MessageService(clientSocket);
                    ListenerProcess newProcess = new ListenerProcess(messageService, threads);
                    threads.add(newProcess);
                    newProcess.start();
                } catch (Exception e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ListenerProcess thread : threads) {
            try {
                thread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
