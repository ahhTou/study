package com.xhr.bean;

import java.sql.Date;

public class UserSeenList {

    private Integer movieID;
    private String movieName;
    private String movieType;
    private int movieScore;
    private Date seenTime;
    private String username;




    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public int getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(int movieScore) {
        this.movieScore = movieScore;
    }

    public Date getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(Date seenTime) {
        this.seenTime = seenTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserSeenList() {

    }

    public UserSeenList(Integer movieID, String movieName, String movieType, int movieScore, Date seenTime, String username) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieType = movieType;
        this.movieScore = movieScore;
        this.seenTime = seenTime;
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserSeenList{" +
                "movieID='" + movieID + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieType='" + movieType + '\'' +
                ", movieScore='" + movieScore + '\'' +
                ", seenTime=" + seenTime +
                ", username='" + username + '\'' +
                '}';
    }
}
