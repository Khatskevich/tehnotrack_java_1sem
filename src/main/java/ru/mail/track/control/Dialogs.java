package ru.mail.track.control;

import ru.mail.track.ThreadedServer;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Dialogs {
    private MessageStorage msgStorage;
    private ActiveConnections activeConnections;
    private ThreadedServer threadedServer;

    private Dialogs(){}
    public Dialogs(ThreadedServer threadedServer, MessageStorage msgStorage, ActiveConnections activeConnections){
        this.msgStorage = msgStorage;
        this.activeConnections = activeConnections;
        this.threadedServer = threadedServer;
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

        try {
            msgStorage.addMessage(msg);
            Connection c = threadedServer.getConnectionPool().getConnection();
            String sql = "SELECT * FROM userstochat WHERE chatId=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1, msg.getDialogId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                long participant = rs.getLong("senderid");
                if ( participant != msg.getSenderId() ) {
                    activeConnections.sendSerialisableToUser(participant, msg);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
