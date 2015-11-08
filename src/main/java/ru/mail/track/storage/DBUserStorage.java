package ru.mail.track.storage;

import ru.mail.track.ThreadedServer;

import java.sql.*;

/**
 * Created by lesaha on 08.11.15.
 */
public class DBUserStorage implements UserStore {
    private ThreadedServer threadedServer;

    public DBUserStorage(ThreadedServer threadedServer) {
        this.threadedServer = threadedServer;
    }

    @Override
    public boolean isUserExist(User user) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();
            boolean result = false;
            String sql ="SELECT COUNT* FROM USERS WHERE NAME=? ;" ;//'" + user.getName() + "','" + user.getName() + "','" + user.getPass() + "');";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getName());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            rs.close();
            stmt.close();
            c.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User addUser(User user) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();
            String sql = "insert into users (NAME,NICK,PASS) " + "values (  ?, ? , ? );";//'" + user.getName() + "','" + user.getName() + "','" + user.getPass() + "');";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPass());
            System.out.println(stmt);
            stmt.executeUpdate();
            stmt.close();
            c.close();

/*            Statement stmt;
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(ID             SERIAL  NOT NULL," +
                    " NAME           TEXT    NOT NULL UNIQUE, " +
                    " NICK            TEXT   , " +
                    " PASS        TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();*/

        } catch (SQLException e){
            if ( e.getSQLState().equals("23505")){//duplicate name
                return null;
            }
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public User getUser(User user) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();
            User newUser = null;
            String sql ="SELECT * FROM USERS WHERE NAME=? AND PASS=?;";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPass());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                newUser = new User(rs.getString("NAME"), rs.getString("PASS"));
                newUser.setNickName(rs.getString("NICK"));
                newUser.setUserId(rs.getLong("ID"));
            }
            rs.close();
            stmt.close();
            c.close();
            return newUser;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User update(User user) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();
            String sql ="UPDATE USERS SET NAME=?, NICK=?, PASS=? WHERE ID=?;";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getNickName());
            stmt.setString(3, user.getPass());
            stmt.setLong(4, user.getUserId());
            stmt.executeUpdate();
            stmt.close();
            c.close();
            return user;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User getUserWithId(long id) {
        try {
            Connection c = threadedServer.getConnectionPool().getConnection();
            User newUser = null;
            String sql ="SELECT * FROM USERS WHERE ID=?;";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                newUser = new User(rs.getString("NAME"), rs.getString("PASS"));
                newUser.setUserId(rs.getLong("ID"));
                newUser.setNickName(rs.getString("NICK"));
            }
            rs.close();
            stmt.close();
            c.close();
            return newUser;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
