package com.xhr.bean;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private String wantListID;
    private String seenListID;

    public User() {

    }

    public User(String username, String password, String nickname, String wantListID, String seenListID) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.wantListID = wantListID;
        this.seenListID = seenListID;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", wantListID='" + wantListID + '\'' +
                ", seenListID='" + seenListID + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWantListID() {
        return wantListID;
    }

    public void setWantListID(String wantListID) {
        this.wantListID = wantListID;
    }

    public String getSeenListID() {
        return seenListID;
    }

    public void setSeenListID(String seenListID) {
        this.seenListID = seenListID;
    }
}
