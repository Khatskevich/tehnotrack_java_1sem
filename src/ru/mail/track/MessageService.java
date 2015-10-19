package ru.mail.track;

//FIXME(arhangeldim): У вас импорты лишние IDEA->Code->OptimizeImports
import ru.mail.track.comands.Command;
import ru.mail.track.session.LocalSession;
import ru.mail.track.session.Session;
import ru.mail.track.storage.User;
import ru.mail.track.storage.*;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

/**
 * //FIXME(arhangeldim): ни о чем не говорит =(
 * нужно описание класса иначе не нужно коментов
 * Created by lesah_000 on 10/13/2015.
 */
public class MessageService {

    //FIXME(arhangeldim): нужны модификаторы доступа
    Session session = new LocalSession();

    //FIXME(arhangeldim): не используется
    MessageStorage msgStorage = new MessageStorageLocal();
    PrintStream out = System.out;
    Scanner in = new Scanner(System.in);

    private Message readMessage() {
        if ( session.getUser() !=null && session.getUser().getNickName() != null) {
            out.print(session.getUser().getNickName());
        }
        out.print(":");
        return new Message(in.nextLine());
    }

    void runMessageLoop() {
        //FIXME(arhangeldim): а как остановить?
        while (true) {
            Message msg = readMessage();
            session.processInput(msg);
        }
    }
}
