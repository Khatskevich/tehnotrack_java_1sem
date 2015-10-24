package ru.mail.track;

import ru.mail.track.comands.Result;
import ru.mail.track.session.LocalSession;
import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Created by lesah_000 on 10/13/2015.
 * This class is responsible for getting messages from user
 * and pass them to session...
 */
public class MessageService {

    private Session session = new LocalSession();
    OutputStream out;
    BufferedReader in;

    private MessageService() {
    }

    ;

    MessageService(Socket socket) {
        try {
            out = socket.getOutputStream();
            in = new BufferedReader((new InputStreamReader(socket.getInputStream())));
            session = new LocalSession(out, in);
        } catch (Exception e) {
        }
    }

    private Message readMessage() {
        try {
            if (session.getUser() != null && session.getUser().getNickName() != null) {
                out.write(session.getUser().getNickName().getBytes());
            }
            out.write(":".getBytes());
            String newMessage = in.readLine();
            if (newMessage == null) {
                return null;
            }
            return new Message(newMessage);
        } catch (Exception e) {
            System.out.println("Can not obtain next message");
        }
        return null;
    }

    void runMessageLoop() {
        while (true) {
            Message msg = readMessage();
            if (msg == null) {
                break;
            }
            Result res = session.processInput(msg);
            if (res == null) {
                break;
            } else if (res.getStatus() == Result.Status.LastMessage) {
                break;
            }
        }
    }
}
