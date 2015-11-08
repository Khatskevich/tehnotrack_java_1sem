package ru.mail.track.connection;

import ru.mail.track.comands.CommandsData;
import ru.mail.track.comands.ServerBaseCommand;
import ru.mail.track.comands.ServerCommandsDecoder;
import ru.mail.track.control.ActiveConnections;
import ru.mail.track.control.Dialogs;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;

import java.io.Serializable;
import java.util.logging.Logger;

public class SingleUserConnection implements MessageListener {
    private long userId = 0;
    private long connectionId = 0;
    private ConnectionHandler connectionHandler;
    private Dialogs dialogs = null;
    private ActiveConnections activeConnections = null;
    private MessageStorage messageStorage = null;
    private Logger LOGGER;
    public SingleUserConnection(ConnectionHandler connectionHandler, Dialogs dialogs, ActiveConnections activeConnections, MessageStorage messageStorage, long connectionId, Logger LOGGER) {
        this.dialogs = dialogs;
        this.activeConnections = activeConnections;
        this.messageStorage = messageStorage;
        this.connectionHandler = connectionHandler;
        this.LOGGER = LOGGER;
        this.connectionId = connectionId;
        connectionHandler.addListener(this);
        activeConnections.addToUnlogined(connectionId, this);
        Thread thread = new Thread(connectionHandler);
        thread.start();
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public void onMessage(Serializable object) {
        try {
            if (object instanceof Message) {
                Message msg = (Message) object;
                LOGGER.info("got message: " + msg.getText());
                dialogs.putMessageToDialog(msg);
            } else if (object instanceof CommandsData) {
                CommandsData cmdData = (CommandsData) object;
                ServerBaseCommand cmd = ServerCommandsDecoder.getCommand(cmdData);
                cmd.perform(this, cmdData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public void setConnectionHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public Dialogs getDialogs() {
        return dialogs;
    }

    public void setDialogs(Dialogs dialogs) {
        this.dialogs = dialogs;
    }

    public ActiveConnections getActiveConnections() {
        return activeConnections;
    }

    public void setActiveConnections(ActiveConnections activeConnections) {
        this.activeConnections = activeConnections;
    }

    public MessageStorage getMessageStorage() {
        return messageStorage;
    }

    public void setMessageStorage(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

}
