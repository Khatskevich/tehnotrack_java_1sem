package ru.mail.track.comands;

import ru.mail.track.storage.Message;
import ru.mail.track.session.Session;

import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CommandHistory implements Command {

    @Override
    public Result perform(Session session, Message msg) {
        if (!session.isLogined()) {
            return null;
        }
        PrintWriter out = new PrintWriter(session.getStdOut(), true);
        //FIXME(arhangeldim): Как-то по смыслу странно получается, вроде это сообщение, а вы его как команду
        // обрабатываете
        // Мне кажется, что так достигается максимальная прозрачность кода.
        // К тому же sendMessage так же как и все по логике должно проходить через сессию
        // ( проверка авторизации, работа по сети, получение результата)
        // и поэтому я решил сделать один жизненный цикл для всего подобного.
        String[] arguments = msg.getText().split(" ");
        int number = Integer.MAX_VALUE;
        if (arguments.length > 1) {
            number = parseInt(arguments[1]);
        }
        try {
            for (Message message : session.getMessageStorage().getLastMessagesWithRegex(number, null)) {
                out.println(message.getTimestamp() + ":" + message.getText());
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getDescription() {
        return "return last messages [count]";
    }
}
