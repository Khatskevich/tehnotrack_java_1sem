package ru.mail.track.comands;


import ru.mail.track.session.Session;
import ru.mail.track.storage.Message;

public class CommandExit implements Command {
    @Override
    public Result perform(Session session, Message msg) {
        if (session.isLogined()) {
            session.stopSession();
            return null;
        } else {
            Result res = new Result();
            res.setStatus(Result.Status.LastMessage);
            return res;
        }
    }

    public String getDescription() {
        return "log out or exit from application";
    }
}
