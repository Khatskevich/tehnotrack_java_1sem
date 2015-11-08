package ru.mail.track.comands;

import ru.mail.track.connection.SingleUserConnection;
import ru.mail.track.control.InfoMessage;
import ru.mail.track.storage.Message;
import ru.mail.track.storage.MessageStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerCommandChatCreate implements ServerBaseCommand {
    @Override
    public Result perform(SingleUserConnection connection, CommandsData command) {
        try {
            if ( connection.getUserId() == 0){
                connection.getConnectionHandler().send(new InfoMessage("Log in please"));
                return null;
            }
            String[] args = command.getText().split(" ");
            Connection c = connection.getThreadedServer().getConnectionPool().getConnection();
            String sql = "insert into chat(creatorid) values(?)";
            PreparedStatement statement = c.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1,connection.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if ( !generatedKeys.next()){
                return null;
            }
            long newChatId = generatedKeys.getLong("id");
            statement.close();
            sql = "insert into userstochat (chatid,senderid) values (  ?, ? );";
            statement = c.prepareStatement(sql );
            for ( int i = 1; i < args.length; i++){
                statement.setLong(1,newChatId);
                statement.setLong(2,Long.decode(args[i]));
                statement.executeUpdate();
            }
            statement.close();
            c.close();
            connection.getConnectionHandler().send(new InfoMessage("Created chat with id = "+ newChatId));
        } catch (Exception e) {
            System.out.println("Error ocured during performing command: " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}