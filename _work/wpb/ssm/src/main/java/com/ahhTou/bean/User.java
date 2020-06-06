package com.ahhTou.bean;

public class User {

    private String username;
    private String password;
    private String nickname;
    private String token;

    public User() {
    }

    public User(String username, String password, String nickname, String token) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
