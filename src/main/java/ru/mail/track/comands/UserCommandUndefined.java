package ru.mail.track.comands;

import ru.mail.track.connection.ConnectionHandler;

//FIXME(arhangeldim): Это можно обработать выше без написания такого странного класса
// Мне показалось что обрабатывать разные случаи по разному- это расставление костылей
// мне хотелось унифицировать жизненный цикл вводимого пользователем, чтобы
// сделать программу прозрачной для дальнейших изменений
public class UserCommandUndefined implements UserBaseCommand {
    @Override
    public Result perform(ConnectionHandler connectionHandler, CommandsData command) {
        if (connectionHandler == null) {
            return null;
        }
        System.out.println("Unrecognised command");
        System.out.println("Use \\help to get list of available commands");
        return null;
    }

    public String getDescription() {
        return "all undefined comands ( start with \\ but undefined) do nothond";
    }
}
