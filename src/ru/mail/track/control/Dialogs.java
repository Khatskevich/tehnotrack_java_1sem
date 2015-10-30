package ru.mail.track.control;

import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;
import ru.mail.track.storage.MessageStorageLocal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dialogs {
    private MessageStorage msgStorage;
    private ActiveConnections activeConnections;

    private Dialogs(){}
    public Dialogs(MessageStorage msgStorage, ActiveConnections activeConnections){
        this.msgStorage = msgStorage;
        this.activeConnections = activeConnections;
    }

    public List<Long> getDialogParticipantsIDs(Long dialogId) {
        ArrayList<Long> arr = new ArrayList<>();
        arr.add(1l);
        arr.add(0l);
        arr.add(2l);
        return arr;
    }

    public List<Message> getMessagesFromDialog(Long dialogId, Long userId) {
        return new ArrayList<>();
    }

    public int putMessageToDialog(Message msg) {
        msgStorage.addMessage(msg);
        //List<Long> participants = getDialogParticipantsIDs(msg.getDialogId());
        Collection<Long> participants = activeConnections.handlersForLoginnedUsers.keySet();
        for (Long participant : participants) {
            if ( !participant.equals(msg.getUserId())) {
                activeConnections.sendSerialisableToUser(participant, msg);
            }
        }
        return 0;
    }
}
