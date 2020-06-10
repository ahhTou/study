package com.ahhTou.bean;



import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Anime implements Serializable {

    private Integer id;

    private String title;

    private Date endTime;

    private String imgUrl;

    private List<String> types;


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", endTime=" + endTime +
                ", imgUrl='" + imgUrl + '\'' +
                ", types=" + types +
                '}';
    }
}
