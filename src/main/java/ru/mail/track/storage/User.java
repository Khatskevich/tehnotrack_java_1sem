package ru.mail.track.storage;


public class User {
    private long userId;
    private String name;
    private String pass;
    private String nickName;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
        this.nickName = null;
    }

    public User(String name, String pass, long userId) {
        this.name = name;
        this.pass = pass;
        this.nickName = null;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User " + name;
    }

}