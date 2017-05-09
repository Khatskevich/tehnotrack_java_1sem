package ru.mail.track;

import java.util.Set;

/**
 * Created by lesaha on 24.10.15.
 */
public class ListenerProcess extends Thread {
    private MessageService msgService;
    private Set<ListenerProcess> threads;

    private ListenerProcess() {
    };

    ListenerProcess(MessageService msgService, Set<ListenerProcess> threads) {
        super();
        this.msgService = msgService;
        this.threads = threads;
    }

    @Override
    public void run() {
        msgService.runMessageLoop();
        threads.remove(this);
    }
}
