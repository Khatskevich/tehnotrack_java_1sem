package ru.mail.track.storage;

import ru.mail.track.ThreadedServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class DBMessageStorage implements MessageStorage {
    private ThreadedServer threadedServer;

    public DBMessageStorage(ThreadedServer threadedServer) {
        this.threadedServer = threadedServer;
    }

    @Override
    public ArrayList<Message> getLastMessagesWithRegex(int num, long chatId, String regex) throws Exception {

        try {
            ArrayList<Message> result = new ArrayList<>();
            Connection c = threadedServer.getConnectionPool().getConnection();
            String sql = "SELECT * FROM MESSAGE WHERE chatId=? ORDER BY time DESC;";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1, chatId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (regex == null || rs.getString("text").matches(regex)) {
                    Message tmpMsg = new Message(rs.getString("text"));
                    tmpMsg.setDialogId(rs.getLong("chatId"));
                    tmpMsg.setSenderId(rs.getLong("senderId"));
                    tmpMsg.setTimestamp(rs.getLong("time"));
                    result.add(tmpMsg);
                    if (result.size() >= num) {
                        break;
                    }
                }

            }
            Collections.reverse(result);
            rs.close();
            stmt.close();
            c.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void addMessage(Message msg) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();

/*            Statement stmt;
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS message" +
                    "(id             SERIAL  NOT NULL," +
                    " chatid           integer    NOT NULL, " +
                    " senderid           integer    NOT NULL, " +
                    " text            TEXT  NOT NULL , " +
                    " time            integer NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();*/

            String sql = "insert into message (chatId,senderId,text,time) " + "values (  ?, ? , ?, ? );";//'" + user.getName() + "','" + user.getName() + "','" + user.getPass() + "');";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1, msg.getDialogId());
            stmt.setLong(2, msg.getSenderId());
            stmt.setString(3, msg.getText());
            stmt.setLong(4, msg.getTimestamp());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }
    }


}
