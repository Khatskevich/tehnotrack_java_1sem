package ru.mail.track.session;

import ru.mail.track.comands.Command;
import ru.mail.track.comands.Result;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.User;

import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

//FIXME(arhangeldim): лишний комент

/**
 * This interface is responsible for all communication and commands performing.
 */
public interface Session {
    //FIXME(arhangeldim): что-то много методов для интерфейса, какое отношение они имеют к сессии?
    //FIXME(arhangeldim): мне кажется, что многие из сущностей сессии больше относятся к отдельным Командам, и
    // должны передаваться в конструктор команд
    // Возможно вы правы, я руководствовался тем что сообщения отсылаются от имени тукущего пользователя,
    // следовательно пользователь должен храниться в сессии. (те сессия - обьект, полностью отвечающий за обмен данными)
    MessageStorage getMessageStorage();

    void startSession(User user) throws Exception;

    void stopSession();

    User getUser();

    PrintStream getStdOut();

    Scanner getStdIn();

    boolean isLogined();

    Result processInput(Message msg);

    Map<String, Command> getAvailableCommands();
}
