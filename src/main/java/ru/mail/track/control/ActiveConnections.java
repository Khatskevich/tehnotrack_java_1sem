package ru.mail.track.control;

import ru.mail.track.connection.SingleUserConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActiveConnections {
    public Map<Long, SingleUserConnection>handlersForLoginnedUsers = new HashMap<>();
    private Map<Long, SingleUserConnection>handlersForUnloginnedUsers = new HashMap<>();

    public int addToLogined(Long userId, SingleUserConnection connection){
        if (handlersForLoginnedUsers.put(userId,connection) == null) {
            return 0;
        }
        else return -1;
    }
    public int deleteFromLogined(Long userId){
        if ( handlersForLoginnedUsers.remove(userId) == null){
            return -1;
        }else return 0;
    }
    public int addToUnlogined(Long userId, SingleUserConnection connection){
        if (handlersForUnloginnedUsers.put(userId,connection) == null) {
            return 0;
        }
        else return -1;
    }
    public int deleteFromUnlogined(Long userId){
        if ( handlersForUnloginnedUsers.remove(userId) == null){
            return -1;
        }else return 0;
    }
    public int sendSerialisableToUser(Long userId, Serializable object){
        SingleUserConnection connection;
        if ( (connection = handlersForLoginnedUsers.get(userId)) == null){
            return -1;
        }else{
            try {
                connection.getConnectionHandler().send(object);
                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        }
    }
}
