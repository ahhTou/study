package com.ahhTou.bean;

import javax.xml.crypto.Data;

public class MyAnime {

    /* 对应动画id */
    private Integer id;

    /* 对应用户名 */
    private String username;

    /* 看完时的时间 */
    private Data watchedTime;

    /* 追番时间 */
    private Data watchingTime;

    /* 是追番列表吗？ */
    private Boolean isWatching;

    /* 更新周期 */
    private Integer updateCycle;

    /* 外表,动画的信息 */
    private Anime animeById;

    public MyAnime(Integer id, String username, Data watchedTime, Data watchingTime, Boolean isWatching, Integer updateCycle, Anime animeById) {
        this.id = id;
        this.username = username;
        this.watchedTime = watchedTime;
        this.watchingTime = watchingTime;
        this.isWatching = isWatching;
        this.updateCycle = updateCycle;
        this.animeById = animeById;
    }

    public MyAnime() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Data getWatchedTime() {
        return watchedTime;
    }

    public void setWatchedTime(Data watchedTime) {
        this.watchedTime = watchedTime;
    }

    public Data getWatchingTime() {
        return watchingTime;
    }

    public void setWatchingTime(Data watchingTime) {
        this.watchingTime = watchingTime;
    }

    public Boolean getWatching() {
        return isWatching;
    }

    public void setWatching(Boolean watching) {
        isWatching = watching;
    }

    public Integer getUpdateCycle() {
        return updateCycle;
    }

    public void setUpdateCycle(Integer updateCycle) {
        this.updateCycle = updateCycle;
    }

    public Anime getAnimeById() {
        return animeById;
    }

    public void setAnimeById(Anime animeById) {
        this.animeById = animeById;
    }
}
