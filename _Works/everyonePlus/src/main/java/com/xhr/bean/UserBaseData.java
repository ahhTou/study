package com.xhr.bean;

import java.io.Serializable;

public class UserBaseData implements Serializable {
    private String nickname;
    private String wantListID;
    private String seenListID;

    public UserBaseData() {

    }

    public UserBaseData(String nickname, String wantListID, String seenListID) {
        this.nickname = nickname;
        this.wantListID = wantListID;
        this.seenListID = seenListID;

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

    @Override
    public String toString() {
        return "UserBaseData{" +
                "nickname='" + nickname + '\'' +
                ", wantListID='" + wantListID + '\'' +
                ", seenListID='" + seenListID + '\'' +
                '}';
    }
}
