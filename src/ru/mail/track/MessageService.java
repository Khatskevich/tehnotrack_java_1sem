package ru.mail.track;

import ru.mail.track.comands.Result;
import ru.mail.track.session.LocalSession;
import ru.mail.track.session.Session;
import ru.mail.track.storage.*;

import java.io.PrintStream;
import java.util.Scanner;


/**
 * Created by lesah_000 on 10/13/2015.
 * This class is responsible for getting messages from user
 * and pass them to session...
 */
public class MessageService {

    private Session session = new LocalSession();
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);

    private Message readMessage() {
        if (session.getUser() != null && session.getUser().getNickName() != null) {
            out.print(session.getUser().getNickName());
        }
        out.print(":");
        return new Message(in.nextLine());
    }

    void runMessageLoop() {
        while (true) {
            Message msg = readMessage();
            Result res = session.processInput(msg);
            if (res != null && res.getStatus() == Result.Status.LastMessage) {
                break;
            }
        }
    }
}
