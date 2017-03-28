package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServerCommandChatList implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            if (connection.getUserId() == 0) {
                connection.getConnectionHandler().send(new InfoMessage("Log in please..."));
                return null;
            }
            Connection c = connection.getThreadedServer().getConnectionPool().getConnection();
            String sql = "SELECT * FROM userstochat WHERE senderid=?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1, connection.getUserId());
            ResultSet rs = stmt.executeQuery();
            StringBuilder result = new StringBuilder();
            result.append("chats:");
            while (rs.next()) {
                result.append(" ").append(rs.getLong("chatid"));
            }
            connection.getConnectionHandler().send(new InfoMessage(result.toString()));
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
